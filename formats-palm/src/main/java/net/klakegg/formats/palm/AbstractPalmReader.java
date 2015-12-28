package net.klakegg.formats.palm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

abstract class AbstractPalmReader<T> implements Iterable<T>, Iterator<T>, Closeable {

    private static Logger logger = LoggerFactory.getLogger(AbstractPalmReader.class);

    protected InputStream inputStream;

    protected DatabaseHeader header;
    protected Deque<Record> entries = new LinkedList<>();

    /**
     * Initiating reading of database.
     *
     * @param inputStream Stream of data.
     * @throws IOException
     */
    public AbstractPalmReader(InputStream inputStream) throws IOException {
        if (!(inputStream instanceof BufferedInputStream))
            inputStream = new BufferedInputStream(inputStream);
        this.inputStream = inputStream;

        // Read meta
        this.header = new DatabaseHeader(readBytes(inputStream, 72));

        // Make sure to read files containing only one record list.
        byte[] bytes = readBytes(inputStream, 6);
        if (PalmUtils.readShort(bytes, 0) != 0)
            throw new RuntimeException("Multiple record lists not supported.");

        // Read record entries.
        for (int i = 1; i <= PalmUtils.readShort(bytes, 4); i++)
            entries.add(new Record(readBytes(inputStream, 8)));
        logger.debug("Found {} records.", entries.size());
    }

    /**
     * Returns database meta.
     *
     * @return Database meta.
     */
    public DatabaseHeader getHeader() {
        return header;
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return entries != null && entries.peek() != null;
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

    protected int byteCounter = 0;

    protected byte[] readBytes(InputStream inputStream, int size) throws IOException {
        byte[] bytes = new byte[size];
        inputStream.read(bytes);
        byteCounter += bytes.length;
        return bytes;
    }
}
