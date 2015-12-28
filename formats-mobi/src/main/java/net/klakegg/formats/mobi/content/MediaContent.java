package net.klakegg.formats.mobi.content;

import net.klakegg.formats.common.util.ByteArrayReader;

public abstract class MediaContent extends AbstractContent {
    public MediaContent(ByteArrayReader reader) {
        super(reader);
    }
}
