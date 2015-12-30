package net.klakegg.formats.mobi.meta;

import net.klakegg.formats.mobi.code.Compression;
import net.klakegg.formats.common.util.ByteArrayReader;

import java.io.Serializable;

public class PalmDocHeader implements Serializable {

    private static final long serialVersionUID = -7314794139014097528L;

    private Compression compression;
    private int textLenght;
    private short recordCount;
    private short recordSize;
    private int currentPosition;

    public PalmDocHeader(ByteArrayReader reader) {
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
