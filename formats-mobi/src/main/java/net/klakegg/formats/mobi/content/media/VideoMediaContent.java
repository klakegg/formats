package net.klakegg.formats.mobi.content.media;

import net.klakegg.formats.common.util.ByteArrayReader;
import net.klakegg.formats.mobi.content.MediaContent;

public class VideoMediaContent extends MediaContent {

    private static final long serialVersionUID = -8860500590096257424L;

    public VideoMediaContent(ByteArrayReader reader) {
        super(reader);
    }
}
