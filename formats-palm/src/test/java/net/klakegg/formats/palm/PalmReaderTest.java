package net.klakegg.formats.palm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PalmReaderTest {

    private static Logger logger = LoggerFactory.getLogger(PalmReaderTest.class);

    @Test
    public void simple() throws IOException {
        PalmReader reader = new PalmReader(getClass().getResourceAsStream("/mobi/dukkehjem.mobi"));
        logger.info("{}", reader.getHeader());

        int counter = 0;
        for (Entry entry : reader) {
            logger.info("{}", entry.getBytes());
            counter++;
        }

        Assert.assertEquals(counter, 87);
    }
}
