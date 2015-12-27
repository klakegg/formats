package net.klakegg.formats.palm.compression;

public interface CompressionAlgorithm {

    byte[] decompress(byte[] bytes);

}
