package net.klakegg.formats.mobi;

import net.klakegg.formats.mobi.content.DocumentContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class Kf8Test {

    private static Logger logger = LoggerFactory.getLogger(Kf8Test.class);

    @Test
    public void simple() throws IOException {
        MobiReader mobiReader = new MobiReader(getClass().getResourceAsStream("/mobi/kf8/KF8Sample.mobi"));

        logger.info("{}", mobiReader.getExthHeader());

        for (DocumentContent documentContent : mobiReader.getDocuments()) {
            logger.info("{}", documentContent.getPalmDocHeader());
            logger.info("{}", documentContent.getMobiHeader());

            logger.info("{}", new String(documentContent.getBytes()));
        }
    }
}
