package net.klakegg.formats.palm;

import net.klakegg.formats.palm.meta.PalmDatabaseHeader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

public class PalmDatabaseWriterTest {

    @Test
    public void simple() throws IOException {
        PalmDatabaseHeader header = new PalmDatabaseHeader();
        header.setName("Palm Database Test");
        header.setType("KLAK");
        header.setCreator("EGG1");
        // header.setCreationDate(new Date());
        // header.setModificationDate(new Date());
        header.setModificationNumber(1);

        PalmDatabaseWriter palmDatabaseWriter = new PalmDatabaseWriter(header);
        palmDatabaseWriter.add(new Record(new byte[]{0, 1, 2, 3, 4}));
        palmDatabaseWriter.add(new Record(new byte[]{5, 6, 7, 8, 9}));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        palmDatabaseWriter.write(byteArrayOutputStream);

        PalmDatabaseReader palmDatabaseReader = new PalmDatabaseReader(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        Assert.assertEquals(palmDatabaseReader.getHeader(), header);

        int counter = 0;
        for (Record record : palmDatabaseReader) {
            Assert.assertEquals(record.getBytes().length, 5);
            counter++;
        }
        Assert.assertEquals(counter, 2);
    }
}
