package net.klakegg.formats.mobi.code;

/**
 * http://wiki.mobileread.com/wiki/MOBI
 */
public enum Type {

    MOBIPOCKET_BOOK(2),
    PALMDOC_BOOK(3),
    AUDIO(4),
    MOBIPCOKET_KINDLEGEN_1_2(232),
    KF8_KINDLEGEN_2_0(248),
    NEWS(257),
    NEWS_FEED(258),
    NEWS_MAGAZINE(259),
    PICS(513),
    WORD(514),
    XLS(515),
    PPT(516),
    TEXT(517),
    HTML(518);

    private int identifier;

    Type(int identifier) {
        this.identifier = identifier;
    }

    public static Type findByIdentifier(int identifier) {
        for (Type type : values())
            if (type.identifier == identifier)
                return type;

        return null;
    }
}
