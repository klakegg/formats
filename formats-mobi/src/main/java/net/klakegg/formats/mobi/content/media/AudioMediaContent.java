package net.klakegg.formats.mobi.content.media;

import net.klakegg.formats.common.util.ByteArrayReader;
import net.klakegg.formats.mobi.content.MediaContent;

public class AudioMediaContent extends MediaContent {

    private static final long serialVersionUID = -8860500590096257424L;

    public AudioMediaContent(ByteArrayReader reader) {
        super(reader);
    }
}
