package net.klakegg.formats.palm;

import com.google.common.io.ByteStreams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PalmDatabaseReader extends AbstractPalmReader<Record> {

    public PalmDatabaseReader(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override
    public Record next() {
        // Throw exception if close() is called.
        if (entries == null)
            throw new IllegalStateException("Unable to read after close.");

        // Fetch next record entry.
        Record record = entries.poll();
        if (record == null)
            return null;

        try {
            // Align inputStream to read first entry in database.
            if (byteCounter < record.getDataOffset())
                readBytes(inputStream, record.getDataOffset() - byteCounter);

            if (entries.peek() == null) {
                // Read to end of file as there are no more entries after this.
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ByteStreams.copy(inputStream, byteArrayOutputStream);
                return record.setBytes(byteArrayOutputStream.toByteArray());
            } else {
                // Read next entry.
                return record.setBytes(readBytes(inputStream, entries.peek().getDataOffset() - record.getDataOffset()));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
