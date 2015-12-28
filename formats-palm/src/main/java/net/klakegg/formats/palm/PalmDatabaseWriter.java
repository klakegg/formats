package net.klakegg.formats.palm;

import net.klakegg.formats.common.util.ByteArrayWriter;
import net.klakegg.formats.palm.meta.PalmDatabaseHeader;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Deque;
import java.util.LinkedList;

public class PalmDatabaseWriter {

    protected PalmDatabaseHeader header;
    protected Deque<Record> entries = new LinkedList<>();

    public PalmDatabaseWriter(PalmDatabaseHeader header) {
        this.header = header;
    }

    public void add(Record record) {
        entries.add(record);
    }

    public void write(OutputStream outputStream) throws IOException {
        // Write header. (72 bytes)
        outputStream.write(header.toBytes());

        if (entries.size() == 0) {
            // Write empty database records. (8 bytes)
            ByteArrayWriter writer = new ByteArrayWriter(outputStream, 8);
            writer.setInt(0, 0);
            writer.setShort(4, 0);
            writer.setBytes(6, new byte[] {0, 0});
            writer.write();
        } else {
            // Write database records. (6 bytes)
            ByteArrayWriter writer = new ByteArrayWriter(outputStream, 6);
            writer.setInt(0, 0);
            writer.setShort(4, entries.size());
            writer.write();

            int dataOffset = 72 + 6 + (8 * entries.size()) + 2;

            for (Record record : entries) {
                // Write database record. (8 bytes)
                writer = new ByteArrayWriter(outputStream, 8);
                writer.setInt(0, dataOffset);
                writer.write();

                dataOffset += record.getBytes().length;
            }

            // Write gap. (2 bytes)
            outputStream.write(new byte[]{0, 0});

            // Write data blocks. (n bytes)
            while (entries.peek() != null)
                outputStream.write(entries.poll().getBytes());
        }
    }
}
