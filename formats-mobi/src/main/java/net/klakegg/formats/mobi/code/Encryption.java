package net.klakegg.formats.mobi.code;

public enum Encryption {
    NONE(0),
    MOBIPOCKET_OLD(1),
    MOBIPOCKET(2);

    private int identifier;

    Encryption(int identifier) {
        this.identifier = identifier;
    }

    public static Encryption findByIdentifier(int identifier) {
        for (Encryption encryption : values())
            if (encryption.identifier == identifier)
                return encryption;

        return null;
    }
}
