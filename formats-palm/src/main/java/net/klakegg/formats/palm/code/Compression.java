package net.klakegg.formats.palm.code;

import net.klakegg.formats.common.compression.CompressionAlgorithm;
import net.klakegg.formats.common.compression.NoCompression;
import net.klakegg.formats.palm.compression.PalmDocCompression;

public enum Compression implements CompressionAlgorithm {
    NONE(1, new NoCompression()),
    PalmDOC(2, new PalmDocCompression()),
    HUFF_CDIC(17480, null);

    private int identifier;
    private CompressionAlgorithm algorithm;

    Compression(int identifier, CompressionAlgorithm algorithm) {
        this.identifier = identifier;
        this.algorithm = algorithm;
    }

    public static Compression findByIdentifier(int identifier) {
        for (Compression compression : values())
            if (compression.identifier == identifier)
                return compression;

        return null;
    }

    @Override
    public byte[] decompress(byte[] bytes) {
        return algorithm.decompress(bytes);
    }
}
