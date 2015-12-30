package net.klakegg.formats.palm.meta;

import net.klakegg.formats.common.util.ByteArrayReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class PalmDatabaseHeaderTest {

    private static Logger logger = LoggerFactory.getLogger(PalmDatabaseHeaderTest.class);

    @Test
    public void simple() throws IOException {
        byte[] bytes = new byte[72];
        getClass().getResourceAsStream("/mobi/dukkehjem.mobi").read(bytes);

        PalmDatabaseHeader original = new PalmDatabaseHeader(new ByteArrayReader(bytes));

        logger.info("{}", new String(bytes));
        logger.info("{}", new String(original.toBytes()));
    }
}
