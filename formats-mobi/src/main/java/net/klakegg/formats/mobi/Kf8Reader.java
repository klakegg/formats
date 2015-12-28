package net.klakegg.formats.mobi;

import net.klakegg.formats.mobi.meta.MobiHeader;
import net.klakegg.formats.palm.PalmDatabaseReader;
import net.klakegg.formats.palm.meta.PalmDocHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Kf8Reader {

    private static Logger logger = LoggerFactory.getLogger(Kf8Reader.class);

    private String content;

    public Kf8Reader(PalmDatabaseReader palmDatabaseReader) throws IOException {
        // Read first record.
        byte[] header = palmDatabaseReader.next().getBytes();
        logger.info(new String(header));

        // Read PalmDOC header.
        PalmDocHeader palmDocHeader = new PalmDocHeader(header);
        logger.info("{}", palmDocHeader);

        // Read MOBI header.
        MobiHeader mobiHeader = new MobiHeader(header);
        logger.info("{}", mobiHeader);

        // Fetch content.
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < palmDocHeader.getRecordCount(); i++)
            byteArrayOutputStream.write(palmDatabaseReader.next().getBytes());
        content = new String(palmDocHeader.getCompression().decompress(byteArrayOutputStream.toByteArray()), mobiHeader.getEncoding());

        while (palmDatabaseReader.hasNext())
            logger.info(new String(palmDatabaseReader.next().getBytes()));
    }

    public String getContent() {
        return content;
    }
}
