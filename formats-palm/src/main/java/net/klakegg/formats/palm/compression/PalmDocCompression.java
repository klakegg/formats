package net.klakegg.formats.palm.compression;

import java.io.ByteArrayOutputStream;

/**
 * https://en.wikibooks.org/wiki/Data_Compression/Dictionary_compression#PalmDoc
  */
public class PalmDocCompression {

    public static byte[] decompress(byte[] bytes) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bytes.length);

        int i = 0;
        while (i < bytes.length) {
            // Read a byte from the compressed stream.
            int b = bytes[i] & 0x00FF;

            // If the byte is

            // 0x00: "1 literal" copy that byte unmodified to the decompressed stream.
            if (b == 0x0) {
                outputStream.write(b);
                i++;
            }

            // 0x01 to 0x08: "literals": the byte is interpreted as a count from 1 to 8, and that many literals are
            // copied unmodified from the compressed stream to the decompressed stream.
            else if (b <= 0x08) {
                for (int j = 0; j < b; j++)
                    outputStream.write(bytes[i + j]);
                i += 1 + b;
            }

            // 0x09 to 0x7f: "1 literal" copy that byte unmodified to the decompressed stream.
            else if (b <= 0x7f) {
                outputStream.write(b);
                i++;
            }

            // 0x80 to 0xbf: "length, distance" pair: the 2 leftmost bits of this byte ('10') are discarded, and the
            // following 6 bits are combined with the 8 bits of the next byte to make a 14 bit "distance, length" item.
            // Those 14 bits are broken into 11 bits of distance backwards from the current location in the uncompressed
            // text, and 3 bits of length to copy from that point (copying n+3 bytes, 3 to 10 bytes).
            else if (b <= 0xbf) {
                // TODO
                i++;
                i++;
            }

            // 0xc0 to 0xff: "byte pair": this byte is decoded into 2 characters: a space character, and a letter formed
            // from this byte XORed with 0x80.
            else {
                outputStream.write(' ');
                outputStream.write(b ^ 0x80);
                i++;
            }
        }

        return outputStream.toByteArray();
    }
}