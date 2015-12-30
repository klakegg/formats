package net.klakegg.formats.mobi.meta;

import net.klakegg.formats.common.util.ByteArrayReader;

public class FdstHeader implements DocumentHeader {

    private byte[] bytes;

    public FdstHeader(ByteArrayReader reader) {
        bytes = reader.getBytes();
    }

    public byte[] getBytes() {
        return bytes;
    }
}
