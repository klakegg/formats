package net.klakegg.formats.mobi;

import net.klakegg.formats.mobi.content.DocumentContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GutenbergTest {

    private static Logger logger = LoggerFactory.getLogger(GutenbergTest.class);

    @Test
    public void simple() throws IOException {
        Mobi mobi = MobiReader.read(getClass().getResourceAsStream("/mobi/pg2852-images.mobi"));

        Assert.assertEquals(mobi.getDocuments().size(), 2);

        // Version 6
        DocumentContent documentContent = mobi.getDocuments().get(0);
        logger.info("{}", documentContent.getPalmDocHeader());
        logger.info("{}", documentContent.getMobiHeader());
        logger.info("{}", documentContent.getExthHeader());

        Assert.assertEquals(documentContent.getMobiHeader().getFileVersion(), 6);
        Assert.assertNotNull(documentContent.getExthHeader());

        // ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // ByteStreams.copy(getClass().getResourceAsStream("/mobi/pg2852-text-kf6.txt"), byteArrayOutputStream);
        // Assert.assertEquals(documentContent.getBytes(), byteArrayOutputStream.toByteArray());

        // Version 8
        documentContent = mobi.getDocuments().get(1);
        logger.info("{}", documentContent.getPalmDocHeader());
        logger.info("{}", documentContent.getMobiHeader());
        logger.info("{}", documentContent.getExthHeader());

        Assert.assertEquals(documentContent.getMobiHeader().getFileVersion(), 8);
        Assert.assertNotNull(documentContent.getExthHeader());

        // byteArrayOutputStream = new ByteArrayOutputStream();
        // ByteStreams.copy(getClass().getResourceAsStream("/mobi/pg2852-text-kf8.txt"), byteArrayOutputStream);
        // Assert.assertEquals(documentContent.getBytes(), byteArrayOutputStream.toByteArray());
    }
}
