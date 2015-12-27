package net.klakegg.formats.mobi;

public class HuffmanRecord {

    private int recordOffset;
    private int recordCount;
    private int tableOffset;
    private int tableLength;

    public HuffmanRecord(int recordOffset, int recordCount, int tableOffset, int tableLength) {
        this.recordOffset = recordOffset;
        this.recordCount = recordCount;
        this.tableOffset = tableOffset;
        this.tableLength = tableLength;
    }

    public int getRecordOffset() {
        return recordOffset;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public int getTableOffset() {
        return tableOffset;
    }

    public int getTableLength() {
        return tableLength;
    }

    @Override
    public String toString() {
        return "HuffmanRecord{" +
                "recordOffset=" + recordOffset +
                ", recordCount=" + recordCount +
                ", tableOffset=" + tableOffset +
                ", tableLength=" + tableLength +
                '}';
    }
}
