package net.klakegg.formats.mobi.meta;

import net.klakegg.formats.common.util.ByteArrayReader;

public class FlisHeader implements DocumentHeader {

    private static final long serialVersionUID = 3155913221557595131L;

    private byte[] bytes;

    public FlisHeader(ByteArrayReader reader) {
        this.bytes = reader.getBytes();
    }

    public byte[] getBytes() {
        return bytes;
    }
}
