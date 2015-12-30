package net.klakegg.formats.mobi.content.media;

import net.klakegg.formats.common.util.ByteArrayReader;
import net.klakegg.formats.mobi.content.MediaContent;

public class GifMediaContent extends MediaContent {

    private static final long serialVersionUID = -6418844130507535362L;

    public GifMediaContent(ByteArrayReader reader) {
        super(reader);
    }
}
