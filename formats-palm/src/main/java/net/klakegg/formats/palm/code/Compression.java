package net.klakegg.formats.palm.code;

public enum Compression {
    NONE(1),
    PalmDOC(2),
    HUFF_CDIC(17480);

    private int identifier;

    Compression(int identifier) {
        this.identifier = identifier;
    }

    public static Compression findByIdentifier(int identifier) {
        for (Compression compression : values())
            if (compression.identifier == identifier)
                return compression;

        return null;
    }
}
