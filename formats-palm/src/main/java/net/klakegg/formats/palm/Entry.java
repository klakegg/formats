package net.klakegg.formats.palm;

public class Entry {

    private byte[] bytes;

    public Entry(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
