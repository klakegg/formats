package net.klakegg.palmformat;

import com.google.common.io.ByteStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Deque;
import java.util.LinkedList;

public class PalmReader {

    private static Logger logger = LoggerFactory.getLogger(PalmReader.class);

    private DatabaseHeader header;
    private Deque<RecordEntry> entries = new LinkedList<RecordEntry>();

    private int counter = 0;

    public PalmReader(InputStream inputStream) throws IOException {
        this.header = new DatabaseHeader(readBytes(inputStream, 72));

        byte[] bytes = readBytes(inputStream, 6);
        if (PalmUtils.readShort(bytes, 0) != 0)
            throw new RuntimeException("Multiple record lists not supported.");

        for (int i = 1; i <= PalmUtils.readShort(bytes, 4); i++)
            entries.add(new RecordEntry(readBytes(inputStream, 8)));

        logger.info("Counter: {}", counter);
        if (counter < entries.peek().getDataOffset())
            readBytes(inputStream, entries.peek().getDataOffset() - counter);
        logger.info("Counter: {}", counter);

        RecordEntry entry;
        while ((entry = entries.poll()) != null) {
            logger.info("{}", entry);

            byte[] data;
            if (entries.peek() == null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ByteStreams.copy(inputStream, byteArrayOutputStream);
                data = byteArrayOutputStream.toByteArray();
            } else {
                data = readBytes(inputStream, entries.peek().getDataOffset() - entry.getDataOffset());
            }
            logger.info("{}", new String(data));
        }
    }

    byte[] readBytes(InputStream inputStream, int size) throws IOException {
        byte[] bytes = new byte[size];
        inputStream.read(bytes);
        counter += bytes.length;
        return bytes;
    }

    DatabaseHeader getHeader() {
        return header;
    }
}
