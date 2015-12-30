package net.klakegg.formats.mobi.content.extra;

import net.klakegg.formats.common.util.ByteArrayReader;
import net.klakegg.formats.mobi.content.ExtraContent;

public class BuildInfoContent extends ExtraContent {

    private static final long serialVersionUID = -6724056367001682176L;

    public BuildInfoContent(ByteArrayReader reader) {
        super(reader);
    }
}
