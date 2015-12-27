package net.klakegg.formats.palm.compression;

public class NoCompression implements CompressionAlgorithm {

    @Override
    public byte[] decompress(byte[] bytes) {
        return bytes;
    }
}
