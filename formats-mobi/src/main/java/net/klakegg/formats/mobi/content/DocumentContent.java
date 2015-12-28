package net.klakegg.formats.mobi.content;

import net.klakegg.formats.common.util.ByteArrayReader;
import net.klakegg.formats.mobi.meta.MobiHeader;
import net.klakegg.formats.palm.meta.PalmDocHeader;

public class DocumentContent extends AbstractContent {

    private PalmDocHeader palmDocHeader;
    private MobiHeader mobiHeader;

    public DocumentContent(PalmDocHeader palmDocHeader, MobiHeader mobiHeader, ByteArrayReader reader) {
        super(reader);
        this.palmDocHeader = palmDocHeader;
        this.mobiHeader = mobiHeader;
    }

    public PalmDocHeader getPalmDocHeader() {
        return palmDocHeader;
    }

    public MobiHeader getMobiHeader() {
        return mobiHeader;
    }
}
