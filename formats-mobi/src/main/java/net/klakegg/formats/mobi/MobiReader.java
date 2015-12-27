package net.klakegg.formats.mobi;

import net.klakegg.formats.mobi.code.Encryption;
import net.klakegg.formats.palm.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class MobiReader implements Iterable<Entry>, Iterator<Entry> {

    private PalmDatabaseReader palmDatabaseReader;

    private PalmDocHeader palmDocHeader;
    private MobiHeader mobiHeader;
    private ExthHeader exthHeader;

    public MobiReader(InputStream inputStream) throws IOException {
        this.palmDatabaseReader = new PalmDatabaseReader(inputStream);

        if (!"BOOK".equals(palmDatabaseReader.getHeader().getType()) || !"MOBI".equals(palmDatabaseReader.getHeader().getCreator()))
            throw new IllegalStateException("Invalid metadata in database header for mobi format.");

        if (!palmDatabaseReader.hasNext())
            throw new IllegalStateException("No records found.");

        byte[] header = palmDatabaseReader.next().getBytes();
        palmDocHeader = new PalmDocHeader(header);
        mobiHeader = new MobiHeader(header);

        if ("EXTH".equals(PalmUtils.readString(header, 16 + mobiHeader.getHeaderLength(), 4))) {
            exthHeader = new ExthHeader(header);
        }

        if (!mobiHeader.getEncryption().equals(Encryption.NONE))
            throw new IllegalStateException("Encryption not supported.");
    }

    public DatabaseHeader getDatabaseHeader() {
        return palmDatabaseReader.getHeader();
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

    @Override
    public Iterator<Entry> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return palmDatabaseReader.hasNext();
    }

    @Override
    public Entry next() {
        return palmDatabaseReader.next();
    }

    @Override
    public void remove() {
        // Not implemented.
    }
}
