package net.klakegg.formats.mobi.meta;

import net.klakegg.formats.common.util.ByteArrayReader;
import net.klakegg.formats.palm.PalmDatabaseReader;
import net.klakegg.formats.mobi.code.Compression;
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

        PalmDocHeader header = new PalmDocHeader(new ByteArrayReader(databaseReader.next().getBytes()));
        logger.debug(header.toString());

        Assert.assertEquals(header.getCompression(), Compression.PalmDOC);
    }

}
