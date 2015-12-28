package net.klakegg.formats.mobi.content;

import net.klakegg.formats.common.util.ByteArrayReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

abstract class AbstractContent implements Content {

    protected byte[] bytes;

    public AbstractContent(ByteArrayReader reader) {
        this.bytes = reader.getBytes();
    }

    public byte[] getBytes() {
        return bytes;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(bytes);
    }
}
