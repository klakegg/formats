package net.klakegg.formats.palm;

import com.google.common.primitives.Ints;
import net.klakegg.formats.common.util.ByteArrayReader;

public class Record {

    private int dataOffset;
    private short attributes;
    private int uniqueId;
    private byte[] bytes;

    Record(byte[] bytes) {
        ByteArrayReader reader = new ByteArrayReader(bytes);

        this.dataOffset = reader.getInt(0);
        this.attributes = (short) bytes[4];
        this.uniqueId = Ints.fromBytes((byte) 0, bytes[5], bytes[6], bytes[7]);
    }

    int getDataOffset() {
        return dataOffset;
    }

    short getAttributes() {
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
