package net.klakegg.formats.common.util;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;

/**
 * Helper class to work with byte array.
 */
public class ByteArrayReader {

    private byte[] bytes;

    public ByteArrayReader(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getStr(int offset, int length) {
        return new String(getBytes(offset, length));
    }

    public int getInt(int offset) {
        return Ints.fromByteArray(getBytes(offset, 4));
    }

    public short getShort(int offset) {
        return Shorts.fromByteArray(getBytes(offset, 2));
    }

    public byte[] getBytes(int offset, int length) {
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++)
            result[i] = bytes[offset + i];
        return result;
    }

    public byte[] getBytes(int offset) {
        return getBytes(offset, bytes.length - offset);
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void discardBytes(int length) {
        bytes = getBytes(length);
    }
}