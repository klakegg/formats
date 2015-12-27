package net.klakegg.formats.mobi;

import net.klakegg.formats.mobi.code.Encryption;
import net.klakegg.formats.palm.DatabaseHeader;
import net.klakegg.formats.palm.PalmDatabaseReader;
import net.klakegg.formats.palm.PalmDocHeader;
import net.klakegg.formats.palm.PalmUtils;
import net.klakegg.formats.palm.compression.PalmDocCompression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class MobiReader {

    private static Logger logger = LoggerFactory.getLogger(MobiReader.class);

    private PalmDatabaseReader palmDatabaseReader;

    private PalmDocHeader palmDocHeader;
    private MobiHeader mobiHeader;
    private ExthHeader exthHeader;

    private String content;

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
            exthHeader = new ExthHeader(PalmUtils.readPart(header, 16 + mobiHeader.getHeaderLength(), header.length - 16 - mobiHeader.getHeaderLength()));
        }

        if (mobiHeader.getEncryption().equals(Encryption.NONE)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < mobiHeader.getFirstNonBookIndex() - 1; i++)
                if (palmDatabaseReader.hasNext())
                    stringBuilder.append(new String(PalmDocCompression.decompress(palmDatabaseReader.next().getBytes()), mobiHeader.getEncoding()));
            content = stringBuilder.toString();
        }

        // while (palmDatabaseReader.hasNext())
        //     logger.info("{}", new String(palmDatabaseReader.next().getBytes()));
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

    public String getContent() {
        if (content == null)
            throw new IllegalStateException("Encryption not supported.");

        return content;
    }
}
