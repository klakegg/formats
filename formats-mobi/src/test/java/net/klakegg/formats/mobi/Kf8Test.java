package net.klakegg.formats.mobi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class Kf8Test {

    private static Logger logger = LoggerFactory.getLogger(Kf8Test.class);

    @Test
    public void simple() throws IOException {
        MobiReader mobiReader = new MobiReader(getClass().getResourceAsStream("/kf8/KF8Sample.mobi"));

        logger.info("{}", mobiReader.getDatabaseHeader());
        logger.info("{}", mobiReader.getPalmDocHeader());
        logger.info("{}", mobiReader.getMobiHeader());
        logger.info("{}", mobiReader.getExthHeader());

        logger.info("{}", mobiReader.getDocument());

    }

}
