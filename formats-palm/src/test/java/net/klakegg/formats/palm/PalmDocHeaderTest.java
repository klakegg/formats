package net.klakegg.formats.palm;

import net.klakegg.formats.palm.code.Compression;
import net.klakegg.formats.palm.meta.PalmDocHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PalmDocHeaderTest {

    private static Logger logger = LoggerFactory.getLogger(PalmDocHeaderTest.class);

    @Test
    public void simple() throws IOException {
        PalmDatabaseReader databaseReader = new PalmDatabaseReader(getClass().getResourceAsStream("/mobi/dukkehjem.mobi"));

        Assert.assertTrue(databaseReader.hasNext());

        PalmDocHeader header = new PalmDocHeader(databaseReader.next().getBytes());
        logger.debug(header.toString());

        Assert.assertEquals(header.getCompression(), Compression.PalmDOC);
    }

}
