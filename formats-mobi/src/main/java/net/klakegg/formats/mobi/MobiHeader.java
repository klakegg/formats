package net.klakegg.formats.mobi;

import net.klakegg.formats.mobi.code.Encryption;
import net.klakegg.formats.mobi.code.Type;
import net.klakegg.formats.palm.PalmUtils;

import java.util.Arrays;

public class MobiHeader {

    private Encryption encryption;
    private int headerLength;
    private Type type;
    private int encoding;
    private int uniqueId;
    private int fileVersion;
    private int fileVersionMin;
    private String name;
    private HuffmanRecord huffmanRecord;
    private int firstImageIndex;
    private int firstNonBookIndex;
    private byte[] exthFlag;

    public MobiHeader(byte[] bytes) {
        if (!"MOBI".equals(PalmUtils.readString(bytes, 16, 4)))
            throw new IllegalStateException("Mobi header not found.");

        encryption = Encryption.findByIdentifier(PalmUtils.readShort(bytes, 12));
        headerLength = PalmUtils.readInt(bytes, 20);
        type = Type.findByIdentifier(PalmUtils.readInt(bytes, 24));
        encoding = PalmUtils.readInt(bytes, 28);
        uniqueId = PalmUtils.readInt(bytes, 32);
        fileVersion = PalmUtils.readInt(bytes, 36);
        fileVersionMin = PalmUtils.readInt(bytes, 104);
        name = PalmUtils.readString(bytes, PalmUtils.readInt(bytes, 84), PalmUtils.readInt(bytes, 88));
        huffmanRecord = new HuffmanRecord(PalmUtils.readInt(bytes, 112), PalmUtils.readInt(bytes, 116), PalmUtils.readInt(bytes, 120), PalmUtils.readInt(bytes, 124));
        firstImageIndex = PalmUtils.readInt(bytes, 108);
        firstNonBookIndex = PalmUtils.readInt(bytes, 80);
        exthFlag = PalmUtils.readPart(bytes, 128, 4);

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

    public int getEncoding() {
        return encoding;
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
                ", headerLength=" + headerLength +
                ", type=" + type +
                ", encoding=" + encoding +
                ", uniqueId=" + uniqueId +
                ", fileVersion=" + fileVersion +
                ", fileVersionMin=" + fileVersionMin +
                ", name='" + name + '\'' +
                ", huffmanRecord=" + huffmanRecord +
                ", firstImageIndex=" + firstImageIndex +
                ", firstNonBookIndex=" + firstNonBookIndex +
                ", exthFlag=" + Arrays.toString(exthFlag) +
                '}';
    }
}