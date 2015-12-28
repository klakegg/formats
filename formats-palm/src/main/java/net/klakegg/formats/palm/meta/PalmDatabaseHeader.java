package net.klakegg.formats.palm.meta;

import net.klakegg.formats.common.util.ByteArrayReader;
import net.klakegg.formats.common.util.ByteArrayWriter;
import net.klakegg.formats.palm.date.PalmDate;

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

    public PalmDatabaseHeader(ByteArrayReader reader) {
        setName(reader.getStr(0, 31).trim());
        setAttributes(reader.getShort(32));
        setVersion(reader.getShort(34));
        setCreationDate(new PalmDate(reader.getBytes(36, 4)).getDate());
        setModificationDate(new PalmDate(reader.getBytes(40, 4)).getDate());
        setLastBackupDate(new PalmDate(reader.getBytes(44, 4)).getDate());
        setModificationNumber(reader.getInt(48));
        setAppInfoID(reader.getInt(52));
        setSortInfoID(reader.getInt(56));
        setType(reader.getStr(60, 4));
        setCreator(reader.getStr(64, 4));
        setUniqueIDSeed(reader.getStr(68, 4));
    }

    public PalmDatabaseHeader() {
        setAppInfoID(0);
        setSortInfoID(0);
        setUniqueIDSeed("    ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAttributes() {
        return attributes;
    }

    public void setAttributes(short attributes) {
        this.attributes = attributes;
    }

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Date getLastBackupDate() {
        return lastBackupDate;
    }

    public void setLastBackupDate(Date lastBackupDate) {
        this.lastBackupDate = lastBackupDate;
    }

    public int getModificationNumber() {
        return modificationNumber;
    }

    public void setModificationNumber(int modificationNumber) {
        this.modificationNumber = modificationNumber;
    }

    private int getAppInfoID() {
        return appInfoID;
    }

    private void setAppInfoID(int appInfoID) {
        this.appInfoID = appInfoID;
    }

    private int getSortInfoID() {
        return sortInfoID;
    }

    private void setSortInfoID(int sortInfoID) {
        this.sortInfoID = sortInfoID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUniqueIDSeed() {
        return uniqueIDSeed;
    }

    public void setUniqueIDSeed(String uniqueIDSeed) {
        this.uniqueIDSeed = uniqueIDSeed;
    }

    public byte[] toBytes() {
        ByteArrayWriter writer = new ByteArrayWriter(72);

        writer.setStr(0, getName(), 31);
        writer.setShort(32, getAttributes());
        writer.setShort(34, getVersion());
        writer.setBytes(36, new PalmDate(getCreationDate()).getBytes());
        writer.setBytes(40, new PalmDate(getModificationDate()).getBytes());
        writer.setBytes(44, new PalmDate(getLastBackupDate()).getBytes());
        writer.setInt(48, getModificationNumber());
        writer.setInt(52, getAppInfoID());
        writer.setInt(56, getSortInfoID());
        writer.setStr(60, getType(), 4);
        writer.setStr(64, getCreator(), 4);
        writer.setStr(68, getUniqueIDSeed(), 4);

        return writer.getBytes();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PalmDatabaseHeader header = (PalmDatabaseHeader) o;

        if (attributes != header.attributes) return false;
        if (version != header.version) return false;
        if (modificationNumber != header.modificationNumber) return false;
        if (appInfoID != header.appInfoID) return false;
        if (sortInfoID != header.sortInfoID) return false;
        if (name != null ? !name.equals(header.name) : header.name != null) return false;
        if (creationDate != null ? !creationDate.equals(header.creationDate) : header.creationDate != null)
            return false;
        if (modificationDate != null ? !modificationDate.equals(header.modificationDate) : header.modificationDate != null)
            return false;
        if (lastBackupDate != null ? !lastBackupDate.equals(header.lastBackupDate) : header.lastBackupDate != null)
            return false;
        if (type != null ? !type.equals(header.type) : header.type != null) return false;
        if (creator != null ? !creator.equals(header.creator) : header.creator != null) return false;
        return !(uniqueIDSeed != null ? !uniqueIDSeed.equals(header.uniqueIDSeed) : header.uniqueIDSeed != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) attributes;
        result = 31 * result + (int) version;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        result = 31 * result + (lastBackupDate != null ? lastBackupDate.hashCode() : 0);
        result = 31 * result + modificationNumber;
        result = 31 * result + appInfoID;
        result = 31 * result + sortInfoID;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (uniqueIDSeed != null ? uniqueIDSeed.hashCode() : 0);
        return result;
    }
}
