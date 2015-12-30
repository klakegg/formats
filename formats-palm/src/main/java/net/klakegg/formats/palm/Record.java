package net.klakegg.formats.palm;

import com.google.common.primitives.Ints;
import net.klakegg.formats.common.util.ByteArrayReader;

import java.io.Serializable;

public class Record implements Serializable {

    private static final long serialVersionUID = 5112883869196658342L;

    private int dataOffset;
    private byte attributes;
    private int uniqueId;
    private byte[] bytes;

    Record(ByteArrayReader reader) {
        this.dataOffset = reader.getInt(0);
        this.attributes = reader.getByte(4);
        this.uniqueId = Ints.fromBytes((byte) 0, reader.getByte(5), reader.getByte(6), reader.getByte(7));
    }

    Record(byte[] bytes) {
        this.bytes = bytes;
    }

    int getDataOffset() {
        return dataOffset;
    }

    byte getAttributes() {
        return attributes;
    }

    int getUniqueId() {
        return uniqueId;
    }

    public byte[] getBytes() {
        return bytes;
    }

    Record setBytes(byte[] bytes) {
        this.bytes = bytes;
        return this;
    }

    @Override
    public String toString() {
        return "RecordEntry{" +
                "dataOffset=" + dataOffset +
                ", attributes=" + attributes +
                ", uniqueId=" + uniqueId +
                '}';
    }
}
