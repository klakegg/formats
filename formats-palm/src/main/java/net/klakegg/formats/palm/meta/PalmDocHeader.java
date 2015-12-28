package net.klakegg.formats.palm.meta;

import net.klakegg.formats.palm.code.Compression;
import net.klakegg.formats.common.util.ByteArrayReader;

public class PalmDocHeader {

    private Compression compression;
    private int textLenght;
    private short recordCount;
    private short recordSize;
    private int currentPosition;

    public PalmDocHeader(byte[] bytes) {
        ByteArrayReader reader = new ByteArrayReader(bytes);

        compression = Compression.findByIdentifier(reader.getShort(0));
        textLenght = reader.getInt(4);
        recordCount = reader.getShort(8);
        recordSize = reader.getShort(10);
        currentPosition = reader.getInt(12);
    }

    public Compression getCompression() {
        return compression;
    }

    public int getTextLenght() {
        return textLenght;
    }

    public short getRecordCount() {
        return recordCount;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public String toString() {
        return "PalmDocHeader{" +
                "compression=" + compression +
                ", textLenght=" + textLenght +
                ", recordCount=" + recordCount +
                ", recordSize=" + recordSize +
                ", currentPosition=" + currentPosition +
                '}';
    }
}
