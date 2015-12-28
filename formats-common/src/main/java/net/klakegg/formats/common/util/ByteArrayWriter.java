package net.klakegg.formats.common.util;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;

import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayWriter {

    private byte[] bytes;
    private OutputStream outputStream;

    public ByteArrayWriter(int length) {
        this(null, length);
    }

    public ByteArrayWriter(OutputStream outputStream, int length) {
        this.bytes = new byte[length];
        this.outputStream = outputStream;
    }

    public void setInt(int offset, int value) {
        setBytes(offset, Ints.toByteArray(value));
    }

    public void setShort(int offset, int value) {
        setShort(offset, (short) value);
    }

    public void setShort(int offset, short value) {
        setBytes(offset, Shorts.toByteArray(value));
    }

    public void setStr(int offset, String value, int max) {
        if (value == null)
            return;

        setBytes(offset, value.length() > max ? value.substring(0, max).getBytes() : value.getBytes());
    }

    public void setBytes(int offset, byte[] value) {
        for (int i = 0; i < value.length; i++)
            bytes[offset + i] = value[i];
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void write() throws IOException {
        outputStream.write(getBytes());
    }
}
