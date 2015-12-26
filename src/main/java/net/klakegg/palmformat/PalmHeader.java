package net.klakegg.palmformat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class PalmHeader {

    private static Logger logger = LoggerFactory.getLogger(PalmHeader.class);

    private String name;
    private String attributes;
    private Date creationDate;
    private Date modificationDate;
    private Date lastBackupDate;
    private int modificationNumber;
    private String appInfoID;
    private String sortInfoID;
    private String type;
    private String creator;
    private String uniqueIDSeed;
    private String recordList;

    public PalmHeader(byte[] bytes) {
        if (bytes.length != 76)
            throw new RuntimeException("Invalid amount of bytes.");

        logger.debug("{}", new String(bytes));

        this.name = PalmUtils.readString(bytes, 0, 31).trim();
        this.attributes = PalmUtils.readString(bytes, 32, 4);
        this.creationDate = PalmUtils.readDate(bytes, 36);
        this.modificationDate = PalmUtils.readDate(bytes, 40);
        this.lastBackupDate = PalmUtils.readDate(bytes, 44);
        this.modificationNumber = PalmUtils.readInt(bytes, 48);
        this.appInfoID = PalmUtils.readString(bytes, 52, 4);
        this.sortInfoID = PalmUtils.readString(bytes, 56, 4);
        this.type = PalmUtils.readString(bytes, 60, 4);
        this.creator = PalmUtils.readString(bytes, 64, 4);
        this.uniqueIDSeed = PalmUtils.readString(bytes, 68, 4);
        this.recordList = PalmUtils.readString(bytes, 72, 4);
    }

    public String getName() {
        return name;
    }

    public String getAttributes() {
        return attributes;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public Date getLastBackupDate() {
        return lastBackupDate;
    }

    public int getModificationNumber() {
        return modificationNumber;
    }

    public String getAppInfoID() {
        return appInfoID;
    }

    public String getSortInfoID() {
        return sortInfoID;
    }

    public String getType() {
        return type;
    }

    public String getCreator() {
        return creator;
    }

    public String getUniqueIDSeed() {
        return uniqueIDSeed;
    }

    public String getRecordList() {
        return recordList;
    }

    @Override
    public String toString() {
        return "PalmHeader{" +
                "name='" + name + '\'' +
                ", attributes='" + attributes + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", lastBackupDate=" + lastBackupDate +
                ", modificationNumber=" + modificationNumber +
                ", appInfoID='" + appInfoID + '\'' +
                ", sortInfoID='" + sortInfoID + '\'' +
                ", type='" + type + '\'' +
                ", creator='" + creator + '\'' +
                ", uniqueIDSeed='" + uniqueIDSeed + '\'' +
                ", recordList='" + recordList + '\'' +
                '}';
    }
}
