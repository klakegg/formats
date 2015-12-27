package net.klakegg.formats.palm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PalmDatabaseReaderTest {

    private static Logger logger = LoggerFactory.getLogger(PalmDatabaseReaderTest.class);

    @Test
    public void simple() throws IOException {
        PalmDatabaseReader reader = new PalmDatabaseReader(getClass().getResourceAsStream("/mobi/dukkehjem.mobi"));
        logger.info("{}", reader.getHeader());

        int counter = 0;
        for (Entry entry : reader) {
            // logger.info("{}", entry.getBytes());
            counter++;
        }

        Assert.assertEquals(counter, 87);

        Assert.assertFalse(reader.hasNext());
        Assert.assertNull(reader.next());

        try {
            reader.remove();
            reader.remove();
        } catch (Exception e) {
            Assert.fail("remove() should always be silent.");
        }

        try {
            reader.close();
            reader.close();
        } catch (Exception e) {
            Assert.fail("close() should always be silent.");
        }

        Assert.assertFalse(reader.hasNext());

        try {
            reader.next();
            Assert.fail("Calling next() after close() should result in Exception.");
        } catch (Exception e) {
            // No action.
        }
    }
}
