package net.klakegg.formats.palm;

import com.google.common.primitives.Ints;

class RecordEntry {

    private int dataOffset;
    private short attributes;
    private int uniqueId;

    public RecordEntry(byte[] bytes) {
        this.dataOffset = PalmUtils.readInt(bytes, 0);
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

    @Override
    public String toString() {
        return "RecordEntry{" +
                "dataOffset=" + dataOffset +
                ", attributes=" + attributes +
                ", uniqueId=" + uniqueId +
                '}';
    }
}
