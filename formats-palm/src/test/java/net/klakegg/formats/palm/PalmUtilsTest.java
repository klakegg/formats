package net.klakegg.formats.palm;

import net.klakegg.formats.palm.util.PalmUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Date;

public class PalmUtilsTest {

    private static Logger logger = LoggerFactory.getLogger(PalmUtilsTest.class);

    @Test
    public void simple() {
        // TODO Verify date
        Date date = PalmUtils.readDate(new byte[]{0, 0, 0, 1}, 0);
        logger.debug("{}", date);
    }

}
