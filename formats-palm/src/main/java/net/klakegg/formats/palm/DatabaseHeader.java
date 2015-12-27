package net.klakegg.formats.palm;

import java.util.Date;

public class DatabaseHeader {

    private String name;
    private short attributes;
    private short version;
    private Date creationDate;
    private Date modificationDate;
    private Date lastBackupDate;
    private int modificationNumber;
    private int appInfoID;
    private int sortInfoID;
    private String type;
    private String creator;
    private String uniqueIDSeed;

    DatabaseHeader(byte[] bytes) {
        this.name = PalmUtils.readString(bytes, 0, 31).trim();
        this.attributes = PalmUtils.readShort(bytes, 32);
        this.version = PalmUtils.readShort(bytes, 34);
        this.creationDate = PalmUtils.readDate(bytes, 36);
        this.modificationDate = PalmUtils.readDate(bytes, 40);
        this.lastBackupDate = PalmUtils.readDate(bytes, 44);
        this.modificationNumber = PalmUtils.readInt(bytes, 48);
        this.appInfoID = PalmUtils.readInt(bytes, 52);
        this.sortInfoID = PalmUtils.readInt(bytes, 56);
        this.type = PalmUtils.readString(bytes, 60, 4);
        this.creator = PalmUtils.readString(bytes, 64, 4);
        this.uniqueIDSeed = PalmUtils.readString(bytes, 68, 4);
    }

    public String getName() {
        return name;
    }

    short getAttributes() {
        return attributes;
    }

    public short getVersion() {
        return version;
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

    int getAppInfoID() {
        return appInfoID;
    }

    int getSortInfoID() {
        return sortInfoID;
    }

    public String getType() {
        return type;
    }

    public String getCreator() {
        return creator;
    }

    String getUniqueIDSeed() {
        return uniqueIDSeed;
    }

    @Override
    public String toString() {
        return "DatabaseHeader{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                ", version=" + version +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", lastBackupDate=" + lastBackupDate +
                ", modificationNumber=" + modificationNumber +
                ", appInfoID=" + appInfoID +
                ", sortInfoID=" + sortInfoID +
                ", type='" + type + '\'' +
                ", creator='" + creator + '\'' +
                ", uniqueIDSeed='" + uniqueIDSeed + '\'' +
                '}';
    }
}
