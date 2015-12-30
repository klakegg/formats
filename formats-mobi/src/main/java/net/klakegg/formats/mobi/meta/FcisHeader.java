package net.klakegg.formats.mobi.meta;

import net.klakegg.formats.common.util.ByteArrayReader;

public class FcisHeader implements DocumentHeader {

    private static final long serialVersionUID = -4017593600691049325L;

    private byte[] bytes;

    public FcisHeader(ByteArrayReader reader) {
        this.bytes = reader.getBytes();
    }

    public byte[] getBytes() {
        return bytes;
    }
}
