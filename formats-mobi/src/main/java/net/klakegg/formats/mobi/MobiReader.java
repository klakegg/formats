package net.klakegg.formats.mobi;

import net.klakegg.formats.palm.PalmReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class MobiReader {

    private static Logger logger = LoggerFactory.getLogger(MobiReader.class);

    private PalmReader palmReader;

    public MobiReader(InputStream inputStream) throws IOException {
        this.palmReader = new PalmReader(inputStream);

        if (!"BOOK".equals(palmReader.getHeader().getType()) || !"MOBI".equals(palmReader.getHeader().getCreator()))
            throw new IllegalStateException("Invalid metadata in database header for mobi format.");

        if (!palmReader.hasNext())
            throw new IllegalStateException("No records found.");

        logger.info(new String(palmReader.next().getBytes()));
    }
}
