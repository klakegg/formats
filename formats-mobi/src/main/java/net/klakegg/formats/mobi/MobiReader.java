package net.klakegg.formats.mobi;

import net.klakegg.formats.common.util.ByteArrayBuffer;
import net.klakegg.formats.common.util.ByteArrayReader;
import net.klakegg.formats.mobi.code.Encryption;
import net.klakegg.formats.mobi.content.DocumentContent;
import net.klakegg.formats.mobi.content.ExtraContent;
import net.klakegg.formats.mobi.content.MediaContent;
import net.klakegg.formats.mobi.content.extra.BuildInfoContent;
import net.klakegg.formats.mobi.content.extra.SourceContent;
import net.klakegg.formats.mobi.content.media.AudioMediaContent;
import net.klakegg.formats.mobi.content.media.GifMediaContent;
import net.klakegg.formats.mobi.content.media.JpegMediaContent;
import net.klakegg.formats.mobi.content.media.VideoMediaContent;
import net.klakegg.formats.mobi.meta.*;
import net.klakegg.formats.palm.PalmDatabaseReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MobiReader {

    private static Logger logger = LoggerFactory.getLogger(MobiReader.class);

    private static byte[] endOfFileRecord = new byte[] {-23, -114, 13, 10};

    public static Mobi read(InputStream inputStream) throws IOException {
        // Open content as Palm Database (PDB).
        PalmDatabaseReader palmDatabaseReader = new PalmDatabaseReader(inputStream);
        // logger.debug("{}", palmDatabaseReader.getHeader());

        // Certain values indicate the file to be a mobi file.
        if (!"BOOK".equals(palmDatabaseReader.getHeader().getType()) || !"MOBI".equals(palmDatabaseReader.getHeader().getCreator()))
            throw new IllegalStateException("Invalid metadata in database header for mobi format.");



        List<DocumentContent> documents = new ArrayList<>();
        List<MediaContent> media = new ArrayList<>();
        List<ExtraContent> extras = new ArrayList<>();

        // Mobi files must minimum contain header record.
        while (palmDatabaseReader.hasNext()) {

            // Fetch first record as this contains header.
            ByteArrayReader header = new ByteArrayReader(palmDatabaseReader.next().getBytes());

            // Create PalmDOC header.
            PalmDocHeader palmDocHeader = new PalmDocHeader(header);

            // Create MOBI header.
            MobiHeader mobiHeader = new MobiHeader(header);

            // Read EXTH header if found.
            ExthHeader exthHeader = null;
            if ("EXTH".equals(header.getStr(16 + mobiHeader.getHeaderLength(), 4)))
                exthHeader = new ExthHeader(header.getReader(16 + mobiHeader.getHeaderLength(), header.getInt(16 + mobiHeader.getHeaderLength() + 4)));

            // Fetch content
            DocumentContent documentContent = null;
            if (mobiHeader.getEncryption().equals(Encryption.NONE)) {
                ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer();
                for (int i = 0; i < palmDocHeader.getRecordCount(); i++)
                    byteArrayBuffer.write(palmDatabaseReader.next().getBytes());

                documentContent = new DocumentContent(
                        palmDocHeader,
                        mobiHeader,
                        exthHeader,
                        byteArrayBuffer
                                .getReader(mobiHeader.getEncoding())
                                .decompress(palmDocHeader.getCompression())
                                .trim()
                );
                documents.add(documentContent);
            }

            while (palmDatabaseReader.hasNext()) {
                ByteArrayReader reader = new ByteArrayReader(palmDatabaseReader.next().getBytes());

                if ("GIF89a".equals(reader.getStr(0, 6))) {
                    media.add(new GifMediaContent(reader));
                } else if ("INDX".equals(reader.getStr(0, 4))) {
                    new IndxHeader(reader);
                } else if ("FLIS".equals(reader.getStr(0, 4))) {
                    if (documentContent != null)
                        documentContent.getDocumentHeaders().add(new FlisHeader(reader));
                } else if ("FCIS".equals(reader.getStr(0, 4))) {
                    if (documentContent != null)
                        documentContent.getDocumentHeaders().add(new FcisHeader(reader));
                } else if ("FDST".equals(reader.getStr(0, 4))) {
                    if (documentContent != null)
                        documentContent.getDocumentHeaders().add(new FdstHeader(reader));
                } else if ("RESC".equals(reader.getStr(0, 4))) {
                    new RescHeader(reader);
                } else if ("SRCS".equals(reader.getStr(0, 4))) {
                    extras.add(new SourceContent(reader));
                } else if ("CMET".equals(reader.getStr(0, 4))) {
                    extras.add(new BuildInfoContent(reader));
                } else if ("AUDI".equals(reader.getStr(0, 4))) {
                    media.add(new AudioMediaContent(reader));
                } else if ("VIDE".equals(reader.getStr(0, 4))) {
                    media.add(new VideoMediaContent(reader));
                } else if ("BOUNDARY".equals(reader.getStr())) {
                    break;
                } else if (new String(reader.getBytes()).contains("JFIF")) {
                    media.add(new JpegMediaContent(reader));
                } else if (Arrays.equals(endOfFileRecord, reader.getBytes())) {
                    break;
                } else {
                    logger.info("{}", reader.getStr());
                    // logger.info("{}", reader.getBytes());
                }
            }
        }

        palmDatabaseReader.close();

        return new Mobi(documents, media, extras);
    }
}
