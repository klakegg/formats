package net.klakegg.formats.mobi.content;

import net.klakegg.formats.common.util.ByteArrayReader;

public abstract class ExtraContent extends AbstractContent {
    public ExtraContent(ByteArrayReader reader) {
        super(reader);
    }
}
