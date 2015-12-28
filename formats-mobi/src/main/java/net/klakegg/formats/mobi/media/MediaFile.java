package net.klakegg.formats.mobi.media;

public class MediaFile {

    private byte[] bytes;
    private String type;

    public MediaFile(byte[] bytes, String type) {
        this.bytes = bytes;
        this.type = type;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MediaFile{" +
                "type='" + type + '\'' +
                '}';
    }
}
