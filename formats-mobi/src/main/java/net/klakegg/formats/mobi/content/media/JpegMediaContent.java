package net.klakegg.formats.mobi.content.media;

import net.klakegg.formats.common.util.ByteArrayReader;
import net.klakegg.formats.mobi.content.MediaContent;

public class JpegMediaContent extends MediaContent {

    private static final long serialVersionUID = -8677005580711206946L;

    public JpegMediaContent(ByteArrayReader reader) {
        super(reader);
    }
}
