package net.klakegg.formats.common.compression;

public class NoCompression implements CompressionAlgorithm {

    @Override
    public byte[] decompress(byte[] bytes) {
        return bytes;
    }
}
