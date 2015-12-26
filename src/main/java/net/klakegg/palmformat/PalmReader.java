package net.klakegg.palmformat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class PalmReader {

    private static Logger logger = LoggerFactory.getLogger(PalmReader.class);

    private PalmHeader header;

    public PalmReader(InputStream inputStream) throws IOException {
        this.header = new PalmHeader(readBytes(inputStream, 76));
    }

    public byte[] readBytes(InputStream inputStream, int size) throws IOException {
        byte[] bytes = new byte[size];
        inputStream.read(bytes);
        return bytes;
    }

    public PalmHeader getHeader() {
        return header;
    }
}
