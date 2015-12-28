package net.klakegg.formats.palm.util;

import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PalmUtils {

    private static byte[] emptyDate = new byte[]{0, 0, 0, 0};
    private static Calendar rootDate = new GregorianCalendar(1904, 0, 1, 0, 0);

    public static Date readDate(byte[] bytes, int start) {
        byte[] b = readPart(bytes, start, 4);

        if (Arrays.equals(b, emptyDate))
            return null;

        Calendar date = new GregorianCalendar();
        date.setTimeInMillis(rootDate.getTimeInMillis());
        date.add(Calendar.SECOND, Ints.fromByteArray(b));
        return date.getTime();
    }

    public static byte[] readPart(byte[] bytes, int start, int length) {
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++)
            result[i] = bytes[start + i];
        return result;
    }
}
