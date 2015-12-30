package net.klakegg.formats.mobi.meta;

import net.klakegg.formats.common.util.ByteArrayReader;

import java.io.Serializable;

public class HuffmanRecord implements Serializable {

    private static final long serialVersionUID = 2594398867696536485L;

    private int recordOffset;
    private int recordCount;
    private int tableOffset;
    private int tableLength;

    public HuffmanRecord(ByteArrayReader reader) {
        this(reader.getInt(0), reader.getInt(4), reader.getInt(8), reader.getInt(12));
    }

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
