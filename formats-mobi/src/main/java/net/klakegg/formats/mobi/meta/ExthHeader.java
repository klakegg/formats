package net.klakegg.formats.mobi.meta;

import net.klakegg.formats.common.util.ByteArrayReader;

import java.util.HashMap;
import java.util.Map;

public class ExthHeader {

    private Map<Integer, String> values = new HashMap<>();

    public ExthHeader(ByteArrayReader reader) {
        int records = reader.getInt(8);

        reader = reader.getReader(12);
        for (int i = 0; i < records; i++) {
            int type = reader.getInt(0);
            int length = reader.getInt(4);
            String value = reader.getStr(8, length - 8);

            values.put(type, value);
            reader = reader.getReader(length);
        }
    }

    public String getValue(int key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        return "ExthHeader{" +
                "values=" + values +
                '}';
    }
}
