package net.klakegg.formats.palm;

import net.klakegg.formats.palm.code.Compression;

public class PalmDocHeader {

    private Compression compression;
    private int textLenght;
    private short recordCount;
    private short recordSize;
    private int currentPosition;

    public PalmDocHeader(byte[] bytes) {
        compression = Compression.findByIdentifier(PalmUtils.readShort(bytes, 0));
        textLenght = PalmUtils.readInt(bytes, 4);
        recordCount = PalmUtils.readShort(bytes, 8);
        recordSize = PalmUtils.readShort(bytes, 10);
        currentPosition = PalmUtils.readInt(bytes, 12);
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
