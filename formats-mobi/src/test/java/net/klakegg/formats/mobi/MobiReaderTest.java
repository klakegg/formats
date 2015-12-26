package net.klakegg.formats.mobi;

import org.testng.annotations.Test;

import java.io.IOException;

public class MobiReaderTest {

    @Test
    public void simple() throws IOException {
        MobiReader mobiReader = new MobiReader(getClass().getResourceAsStream("/mobi/dukkehjem.mobi"));
    }
}
