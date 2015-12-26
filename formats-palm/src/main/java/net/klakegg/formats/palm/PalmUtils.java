package net.klakegg.formats.palm;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

class PalmUtils {

    private static byte[] emptyDate = new byte[]{0, 0, 0, 0};
    private static Calendar rootDate = new GregorianCalendar(1904, 0, 1, 0, 0);

    public static String readString(byte[] bytes, int start, int length) {
        return new String(readPart(bytes, start, length));
    }

    public static Date readDate(byte[] bytes, int start) {
        byte[] b = readPart(bytes, start, 4, false);

        if (Arrays.equals(b, emptyDate))
            return null;

        Calendar date = new GregorianCalendar();
        date.setTimeInMillis(rootDate.getTimeInMillis());
        date.add(Calendar.SECOND, Ints.fromByteArray(b));
        return date.getTime();
    }

    public static int readInt(byte[] bytes, int start) {
        return Ints.fromByteArray(readPart(bytes, start, 4, false));
    }

    public static short readShort(byte[] bytes, int start) {
        return Shorts.fromByteArray(readPart(bytes, start, 2, false));
    }

    public static byte[] readPart(byte[] bytes, int start, int length) {
        return readPart(bytes, start, length, false);
    }

    public static byte[] readPart(byte[] bytes, int start, int length, boolean reverse) {
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++)
            result[i] = bytes[reverse ? start + length - 1 - i : start + i];
        return result;
    }
}
