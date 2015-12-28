package net.klakegg.formats.palm.meta;

import net.klakegg.formats.common.util.ByteArrayReader;
import net.klakegg.formats.palm.util.PalmUtils;

import java.util.Date;

public class PalmDatabaseHeader {

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

    public PalmDatabaseHeader(byte[] bytes) {
        ByteArrayReader reader = new ByteArrayReader(bytes);

        this.name = reader.getStr(0, 31).trim();
        this.attributes = reader.getShort(32);
        this.version = reader.getShort(34);
        this.creationDate = PalmUtils.readDate(bytes, 36);
        this.modificationDate = PalmUtils.readDate(bytes, 40);
        this.lastBackupDate = PalmUtils.readDate(bytes, 44);
        this.modificationNumber = reader.getInt(48);
        this.appInfoID = reader.getInt(52);
        this.sortInfoID = reader.getInt(56);
        this.type = reader.getStr(60, 4);
        this.creator = reader.getStr(64, 4);
        this.uniqueIDSeed = reader.getStr(68, 4);
    }

    public String getName() {
        return name;
    }

    public short getAttributes() {
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

    public int getAppInfoID() {
        return appInfoID;
    }

    public int getSortInfoID() {
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

    @Override
    public String toString() {
        return "PalmDatabaseHeader{" +
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
