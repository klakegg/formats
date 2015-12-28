package net.klakegg.formats.mobi.media;

public class SourceFile {

    private byte[] bytes;

    public SourceFile(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
