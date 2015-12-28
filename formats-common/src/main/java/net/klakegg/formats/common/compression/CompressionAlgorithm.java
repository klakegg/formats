package net.klakegg.formats.common.compression;

public interface CompressionAlgorithm {

    byte[] decompress(byte[] bytes);

}
