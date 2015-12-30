package net.klakegg.formats.mobi.content;

import java.io.InputStream;
import java.io.Serializable;

public interface Content extends Serializable {

    byte[] getBytes();

    InputStream getInputStream();

}
