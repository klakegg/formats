package net.klakegg.formats.palm;

import com.google.common.io.ByteStreams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PalmDatabaseReader extends AbstractPalmReader<Entry> {

    public PalmDatabaseReader(InputStream inputStream) throws IOException {
        super(inputStream);
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
            // Align inputStream to read first entry in database.
            if (counter < record.getDataOffset())
                readBytes(inputStream, record.getDataOffset() - counter);

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
}
