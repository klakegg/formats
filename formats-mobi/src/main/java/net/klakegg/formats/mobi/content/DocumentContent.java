package net.klakegg.formats.mobi.content;

import net.klakegg.formats.common.util.ByteArrayReader;
import net.klakegg.formats.mobi.meta.DocumentHeader;
import net.klakegg.formats.mobi.meta.ExthHeader;
import net.klakegg.formats.mobi.meta.MobiHeader;
import net.klakegg.formats.mobi.meta.PalmDocHeader;

import java.util.ArrayList;
import java.util.List;

public class DocumentContent extends AbstractContent {

    private static final long serialVersionUID = 1074230234069317984L;

    private PalmDocHeader palmDocHeader;
    private MobiHeader mobiHeader;
    private ExthHeader exthHeader;

    private List<DocumentHeader> documentHeaders = new ArrayList<>();

    public DocumentContent(PalmDocHeader palmDocHeader, MobiHeader mobiHeader, ExthHeader exthHeader, ByteArrayReader reader) {
        super(reader);
        this.palmDocHeader = palmDocHeader;
        this.mobiHeader = mobiHeader;
        this.exthHeader = exthHeader;
    }

    public PalmDocHeader getPalmDocHeader() {
        return palmDocHeader;
    }

    public MobiHeader getMobiHeader() {
        return mobiHeader;
    }

    public ExthHeader getExthHeader() {
        return exthHeader;
    }

    public List<DocumentHeader> getDocumentHeaders() {
        return documentHeaders;
    }
}
