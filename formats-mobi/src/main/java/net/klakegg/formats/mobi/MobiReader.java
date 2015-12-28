package net.klakegg.formats.mobi;

import net.klakegg.formats.mobi.code.Encryption;
import net.klakegg.formats.mobi.media.BuildLog;
import net.klakegg.formats.mobi.media.MediaFile;
import net.klakegg.formats.mobi.media.SourceFile;
import net.klakegg.formats.mobi.meta.*;
import net.klakegg.formats.palm.meta.PalmDatabaseHeader;
import net.klakegg.formats.palm.PalmDatabaseReader;
import net.klakegg.formats.common.util.ByteArrayReader;
import net.klakegg.formats.palm.meta.PalmDocHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MobiReader {

    private static Logger logger = LoggerFactory.getLogger(MobiReader.class);

    private PalmDatabaseReader palmDatabaseReader;

    private PalmDocHeader palmDocHeader;
    private MobiHeader mobiHeader;
    private ExthHeader exthHeader;

    private String content;
    private List<MediaFile> mediaFiles = new ArrayList<>();
    private List<Object> extras = new ArrayList<>();

    public MobiReader(InputStream inputStream) throws IOException {
        // Open content as Palm Database (PDB).
        this.palmDatabaseReader = new PalmDatabaseReader(inputStream);

        // Certain values indicate the file to be a mobi file.
        if (!"BOOK".equals(palmDatabaseReader.getHeader().getType()) || !"MOBI".equals(palmDatabaseReader.getHeader().getCreator()))
            throw new IllegalStateException("Invalid metadata in database header for mobi format.");

        // Mobi files must minimum contain meta record.
        if (!palmDatabaseReader.hasNext())
            throw new IllegalStateException("No records found.");

        // Fetch first record as this contains meta.
        ByteArrayReader header = new ByteArrayReader(palmDatabaseReader.next().getBytes());

        // Create PalmDOC meta.
        palmDocHeader = new PalmDocHeader(header.getBytes());

        // Create MOBI meta.
        mobiHeader = new MobiHeader(header.getBytes());

        // Read EXTH meta if found
        if ("EXTH".equals(header.getStr(16 + mobiHeader.getHeaderLength(), 4)))
            exthHeader = new ExthHeader(header.getBytes(16 + mobiHeader.getHeaderLength(), header.getInt(16 + mobiHeader.getHeaderLength() + 4)));

        // Fetch content
        if (mobiHeader.getEncryption().equals(Encryption.NONE)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i = 0; i < palmDocHeader.getRecordCount(); i++)
                byteArrayOutputStream.write(palmDatabaseReader.next().getBytes());
            content = new String(palmDocHeader.getCompression().decompress(byteArrayOutputStream.toByteArray()), mobiHeader.getEncoding()).trim();
        }

        while (palmDatabaseReader.hasNext()) {
            byte[] bytes = palmDatabaseReader.next().getBytes();

            if (new String(bytes).contains("JFIF")) {
                mediaFiles.add(new MediaFile(bytes, "JPEG"));
            } else if (new String(bytes).startsWith("GIF89a")) {
                mediaFiles.add(new MediaFile(bytes, "GIF"));
            } else if (new String(bytes).startsWith("INDX")) {
                new IndxHeader(bytes);
            } else if (new String(bytes).startsWith("FLIS")) {
                new FlisHeader(bytes);
            } else if (new String(bytes).startsWith("FCIS")) {
                new FcisHeader(bytes);
            } else if (new String(bytes).startsWith("SRCS")) {
                extras.add(new SourceFile(bytes));
            } else if (new String(bytes).startsWith("CMET")) {
                extras.add(new BuildLog(bytes));
            } else if (new String(bytes).equals("BOUNDARY")) {
                new Kf8Reader(palmDatabaseReader);
            } else {
                logger.info("{}", new String(bytes));
            }
        }
    }

    public PalmDatabaseHeader getDatabaseHeader() {
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
            throw new IllegalStateException("Encrypted content is not supported.");

        return content;
    }

    public List<MediaFile> getMediaFiles() {
        return mediaFiles;
    }

    public List<Object> getExtras() {
        return extras;
    }
}
