package net.klakegg.formats.mobi.meta;

import net.klakegg.formats.mobi.code.Encryption;
import net.klakegg.formats.mobi.code.Type;
import net.klakegg.formats.common.util.ByteArrayReader;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MobiHeader {

    private Encryption encryption;
    private int headerLength;
    private Type type;
    private Charset encoding;
    private int uniqueId;
    private int fileVersion;
    private int fileVersionMin;
    private String name;
    private HuffmanRecord huffmanRecord;
    private int firstImageIndex;
    private int firstNonBookIndex;
    private byte[] exthFlag;

    public MobiHeader(byte[] bytes) {
        ByteArrayReader reader = new ByteArrayReader(bytes);

        if (!"MOBI".equals(reader.getStr(16, 4)))
            throw new IllegalStateException("Mobi header not found.");

        encryption = Encryption.findByIdentifier(reader.getShort(12));
        headerLength = reader.getInt(20);
        type = Type.findByIdentifier(reader.getInt(24));
        setEncoding(reader.getInt(28));
        uniqueId = reader.getInt(32);
        fileVersion = reader.getInt(36);
        fileVersionMin = reader.getInt(104);
        name = reader.getStr(reader.getInt(84), reader.getInt(88));
        huffmanRecord = new HuffmanRecord(reader.getInt(112), reader.getInt(116), reader.getInt(120), reader.getInt(124));
        firstImageIndex = reader.getInt(108);
        firstNonBookIndex = reader.getInt(80);
        exthFlag = reader.getBytes(128, 4);

//        logger.info("{}", PalmUtils.readInt(bytes, 40)); // Ortographic index
//        logger.info("{}", PalmUtils.readInt(bytes, 44)); // Inflection index
//        logger.info("{}", PalmUtils.readInt(bytes, 48)); // Index names
//        logger.info("{}", PalmUtils.readInt(bytes, 52)); // Index keys
//        logger.info("{}", PalmUtils.readInt(bytes, 56)); // Extra index 0
//        logger.info("{}", PalmUtils.readInt(bytes, 60)); // Extra index 1
//        logger.info("{}", PalmUtils.readInt(bytes, 64)); // Extra index 2
//        logger.info("{}", PalmUtils.readInt(bytes, 68)); // Extra index 3
//        logger.info("{}", PalmUtils.readInt(bytes, 72)); // Extra index 4
//        logger.info("{}", PalmUtils.readInt(bytes, 76)); // Extra index 5

//        logger.info("{}", PalmUtils.readPart(bytes, 92, 4)); // Locale
//        logger.info("{}", PalmUtils.readInt(bytes, 96)); // Input Language
//        logger.info("{}", PalmUtils.readInt(bytes, 100)); // Output Language
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public int getHeaderLength() {
        return headerLength;
    }

    public Type getType() {
        return type;
    }

    public Charset getEncoding() {
        return encoding;
    }

    private void setEncoding(int value) {
        switch (value) {
            case 1252:
                // Set CP1252
                encoding = StandardCharsets.US_ASCII;
                break;
            case 65001:
                encoding = StandardCharsets.UTF_8;
                break;
        }
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public int getFileVersion() {
        return fileVersion;
    }

    public int getFileVersionMin() {
        return fileVersionMin;
    }

    public String getName() {
        return name;
    }

    public HuffmanRecord getHuffmanRecord() {
        return huffmanRecord;
    }

    public int getFirstImageIndex() {
        return firstImageIndex;
    }

    public int getFirstNonBookIndex() {
        return firstNonBookIndex;
    }

    public byte[] getExthFlag() {
        return exthFlag;
    }

    @Override
    public String toString() {
        return "MobiHeader{" +
                "encryption=" + encryption +
                ", type=" + type +
                ", encoding=" + encoding +
                ", uniqueId=" + uniqueId +
                ", fileVersion=" + fileVersion +
                ", fileVersionMin=" + fileVersionMin +
                ", name='" + name + '\'' +
                '}';
    }
}
