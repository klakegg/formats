package net.klakegg.formats.mobi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class MobiReaderTest {

    private static Logger logger = LoggerFactory.getLogger(MobiReaderTest.class);

    @Test
    public void simple() throws IOException {
        MobiReader mobiReader = new MobiReader(getClass().getResourceAsStream("/mobi/dukkehjem.mobi"));

        logger.info("{}", mobiReader.getDatabaseHeader());
        logger.info("{}", mobiReader.getPalmDocHeader());
        logger.info("{}", mobiReader.getMobiHeader());
        logger.info("{}", mobiReader.getExthHeader());

        if (mobiReader.hasNext())
            logger.info(new String(mobiReader.next().getBytes()));

    }
}
