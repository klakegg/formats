package net.klakegg.formats.mobi;

public class ExthHeader {

    byte[] bytes;

    public ExthHeader(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "ExthHeader{" +
                "bytes=" + new String(bytes) +
                '}';
    }
}
