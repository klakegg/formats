package net.klakegg.palmformat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class PalmReaderTest {

    private static Logger logger = LoggerFactory.getLogger(PalmReaderTest.class);

    @Test
    public void simple() throws IOException {
        PalmReader reader = new PalmReader(getClass().getResourceAsStream("/dukkehjem.mobi"));
        logger.info("{}", reader.getHeader());
    }
}
