package net.klakegg.formats.mobi.content;

import java.io.InputStream;

public interface Content {

    byte[] getBytes();

    InputStream getInputStream();

}
