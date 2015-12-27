package net.klakegg.formats.mobi;

import net.klakegg.formats.mobi.code.Encryption;
import net.klakegg.formats.palm.DatabaseHeader;
import net.klakegg.formats.palm.PalmDatabaseReader;
import net.klakegg.formats.palm.PalmDocHeader;
import net.klakegg.formats.palm.PalmUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MobiReader {

    private PalmDatabaseReader palmDatabaseReader;

    private PalmDocHeader palmDocHeader;
    private MobiHeader mobiHeader;
    private ExthHeader exthHeader;

    private String content;

    public MobiReader(InputStream inputStream) throws IOException {
        // Open content as Palm Database (PDB).
        this.palmDatabaseReader = new PalmDatabaseReader(inputStream);

        // Certain values indicate the file to be a mobi file.
        if (!"BOOK".equals(palmDatabaseReader.getHeader().getType()) || !"MOBI".equals(palmDatabaseReader.getHeader().getCreator()))
            throw new IllegalStateException("Invalid metadata in database header for mobi format.");

        // Mobi files must minimum contain header record.
        if (!palmDatabaseReader.hasNext())
            throw new IllegalStateException("No records found.");

        // Fetch first record as this contains header.
        byte[] header = palmDatabaseReader.next().getBytes();

        // Create PalmDOC header.
        palmDocHeader = new PalmDocHeader(header);

        // Create MOBI header.
        mobiHeader = new MobiHeader(header);

        // Read EXTH header if found
        if ("EXTH".equals(PalmUtils.readString(header, 16 + mobiHeader.getHeaderLength(), 4)))
            exthHeader = new ExthHeader(PalmUtils.readPart(header, 16 + mobiHeader.getHeaderLength(), PalmUtils.readInt(header, 16 + mobiHeader.getHeaderLength() + 4)));

        // Fetch content
        if (mobiHeader.getEncryption().equals(Encryption.NONE)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i = 0; i < mobiHeader.getFirstNonBookIndex() - 2; i++)
                if (palmDatabaseReader.hasNext())
                    byteArrayOutputStream.write(palmDatabaseReader.next().getBytes());
            content = new String(palmDocHeader.getCompression().decompress(byteArrayOutputStream.toByteArray()), mobiHeader.getEncoding()).trim();
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
            throw new IllegalStateException("Encrypted content is not supported.");

        return content;
    }
}
