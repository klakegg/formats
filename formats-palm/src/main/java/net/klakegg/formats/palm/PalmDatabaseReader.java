package net.klakegg.formats.palm;

import com.google.common.io.ByteStreams;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class PalmDatabaseReader implements Iterable<Entry>, Iterator<Entry>, Closeable {

    private InputStream inputStream;

    private DatabaseHeader header;
    private Deque<Record> entries = new LinkedList<>();

    private int counter = 0;

    /**
     * Initiating reading of database.
     *
     * @param inputStream Stream of data.
     * @throws IOException
     */
    public PalmDatabaseReader(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;

        // Read header
        this.header = new DatabaseHeader(readBytes(inputStream, 72));

        // Make sure to read files containing only one record list.
        byte[] bytes = readBytes(inputStream, 6);
        if (PalmUtils.readShort(bytes, 0) != 0)
            throw new RuntimeException("Multiple record lists not supported.");

        // Read record entries.
        for (int i = 1; i <= PalmUtils.readShort(bytes, 4); i++)
            entries.add(new Record(readBytes(inputStream, 8)));

        // Align inputStream to read first entry in database.
        if (counter < entries.peek().getDataOffset())
            readBytes(inputStream, entries.peek().getDataOffset() - counter);
    }

    /**
     * Returns database header.
     *
     * @return Database header.
     */
    public DatabaseHeader getHeader() {
        return header;
    }

    @Override
    public Iterator<Entry> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return entries != null && entries.peek() != null;
    }

    @Override
    public Entry next() {
        // Throw exception if close() is called.
        if (entries == null)
            throw new IllegalStateException("Unable to read after close.");

        // Fetch next record entry.
        Record record = entries.poll();
        if (record == null)
            return null;

        try {
            if (entries.peek() == null) {
                // Read to end of file as there are no more entries after this.
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ByteStreams.copy(inputStream, byteArrayOutputStream);
                return new Entry(record, byteArrayOutputStream.toByteArray());
            } else {
                // Read next entry.
                return new Entry(record, readBytes(inputStream, entries.peek().getDataOffset() - record.getDataOffset()));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public void remove() {
        // Not implemented.
    }

    @Override
    public void close() throws IOException {
        // Simply remove pointers.
        inputStream = null;
        header = null;
        entries = null;
    }

    byte[] readBytes(InputStream inputStream, int size) throws IOException {
        byte[] bytes = new byte[size];
        inputStream.read(bytes);
        counter += bytes.length;
        return bytes;
    }
}
