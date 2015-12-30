package net.klakegg.formats.mobi;

import net.klakegg.formats.mobi.content.DocumentContent;
import net.klakegg.formats.mobi.content.ExtraContent;
import net.klakegg.formats.mobi.content.MediaContent;
import net.klakegg.formats.mobi.meta.ExthHeader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mobi implements Serializable {

    private static final long serialVersionUID = -2086510672985631726L;

    private ExthHeader exthHeader;

    private List<DocumentContent> documents = new ArrayList<>();
    private List<MediaContent> media = new ArrayList<>();
    private List<ExtraContent> extras = new ArrayList<>();

    public Mobi(ExthHeader exthHeader, List<DocumentContent> documents, List<MediaContent> media, List<ExtraContent> extras) {
        this.exthHeader = exthHeader;
        this.documents = documents;
        this.media = media;
        this.extras = extras;
    }

    public ExthHeader getExthHeader() {
        return exthHeader;
    }

    public List<DocumentContent> getDocuments() {
        return documents;
    }

    public List<MediaContent> getMedia() {
        return media;
    }

    public List<ExtraContent> getExtras() {
        return extras;
    }
}
