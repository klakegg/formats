package net.klakegg.formats.common.util;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;
import net.klakegg.formats.common.compression.CompressionAlgorithm;

import java.nio.charset.Charset;

/**
 * Immutable helper class to work with byte array.
 */
public class ByteArrayReader {

    private byte[] bytes;
    private Charset charset;

    public ByteArrayReader(byte[] bytes) {
        this(bytes, Charset.defaultCharset());
    }

    public ByteArrayReader(byte[] bytes, Charset charset) {
        this.bytes = bytes;
        this.charset = charset;
    }

    public ByteArrayReader(byte[] bytes, int offset, int length) {
        this(bytes, offset, length, Charset.defaultCharset());
    }

    public ByteArrayReader(byte[] bytes, int offset, int length, Charset charset) {
        this.bytes = getBytes(bytes, offset, length);
        this.charset = charset;
    }

    public String getStr(int offset, int length) {
        return new String(getBytes(offset, length), charset);
    }

    public String getStr() {
        return new String(bytes, charset);
    }

    public int getInt(int offset) {
        return Ints.fromByteArray(getBytes(offset, 4));
    }

    public short getShort(int offset) {
        return Shorts.fromByteArray(getBytes(offset, 2));
    }

    public byte[] getBytes(int offset, int length) {
        return getBytes(bytes, offset, length);
    }

    public byte[] getBytes(int offset) {
        if (offset > 0)
            return getBytes(offset, bytes.length - offset);
        else
            return getBytes(bytes.length + offset);
    }

    public byte[] getBytes() {
        return bytes;
    }

    public ByteArrayReader getReader(int offset, int length) {
        return new ByteArrayReader(getBytes(offset, length), charset);
    }

    public ByteArrayReader getReader(int offset) {
        return new ByteArrayReader(getBytes(offset), charset);
    }

    public ByteArrayReader decompress(CompressionAlgorithm algorithm) {
        return new ByteArrayReader(algorithm.decompress(bytes), charset);
    }

    public ByteArrayReader trim() {
        return new ByteArrayReader(new String(bytes).trim().getBytes(), charset);

        /*
        int offset = 0;
        for (; offset < bytes.length; offset++)
            if (bytes[offset + 1] != 0)
                break;

        int length = bytes.length - offset;
        for (; length > 0; length++)
            if (bytes[offset + length - 2] != 0)
                break;

        return getReader(offset, length);
        */
    }

    private byte[] getBytes(byte[] bytes, int offset, int length) {
        if (offset == 0 && bytes.length == length)
            return bytes;

        byte[] result = new byte[Math.min(length, bytes.length - offset)];
        for (int i = 0; i < result.length; i++)
            result[i] = bytes[offset + i];
        return result;
    }
}