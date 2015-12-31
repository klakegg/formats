package net.klakegg.formats.mobi;

import com.google.common.io.ByteStreams;
import net.klakegg.formats.mobi.content.DocumentContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MobiReaderTest {

    private static Logger logger = LoggerFactory.getLogger(MobiReaderTest.class);

    @Test
    public void simple() throws IOException {
        Mobi mobi = MobiReader.read(getClass().getResourceAsStream("/mobi/dukkehjem.mobi"));

        logger.info("{}", mobi.getExthHeader());

        Assert.assertEquals(mobi.getDocuments().size(), 1);
        Assert.assertNotNull(mobi.getExthHeader());

        // Version 6
        DocumentContent documentContent = mobi.getDocuments().get(0);
        logger.info("{}", documentContent.getPalmDocHeader());
        logger.info("{}", documentContent.getMobiHeader());

        Assert.assertEquals(documentContent.getMobiHeader().getFileVersion(), 6);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteStreams.copy(getClass().getResourceAsStream("/mobi/dukkehjem-text-kf6.txt"), byteArrayOutputStream);
        Assert.assertEquals(documentContent.getBytes(), byteArrayOutputStream.toByteArray());
    }
}
