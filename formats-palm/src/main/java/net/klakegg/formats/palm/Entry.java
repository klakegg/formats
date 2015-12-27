package net.klakegg.formats.palm;

public class Entry {

    private Record record;
    private byte[] bytes;

    Entry(Record record, byte[] bytes) {
        this.record = record;
        this.bytes = bytes;
    }

    public Record getRecord() {
        return record;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
