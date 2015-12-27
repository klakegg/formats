package net.klakegg.formats.mobi;

import net.klakegg.formats.palm.PalmUtils;

import java.util.HashMap;
import java.util.Map;

public class ExthHeader {

    private Map<Integer, String> values = new HashMap<>();

    public ExthHeader(byte[] bytes) {
        int counter = 12;
        for (int i = 0; i < PalmUtils.readInt(bytes, 8); i++) {
            int type = PalmUtils.readInt(bytes, counter);
            int length = PalmUtils.readInt(bytes, counter + 4);
            String value = PalmUtils.readString(bytes, counter + 8, length - 8);

            values.put(type, value);
            counter += length;
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
