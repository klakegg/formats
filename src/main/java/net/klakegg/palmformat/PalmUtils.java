package net.klakegg.palmformat;

import com.google.common.primitives.Ints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PalmUtils {

    private static Logger logger = LoggerFactory.getLogger(PalmUtils.class);

    private static byte[] emptyDate = new byte[]{0, 0, 0, 0};
    private static Calendar rootDate = new GregorianCalendar(1904, 0, 1, 12, 0);

    static {
        logger.debug("{}", rootDate.getTime());
    }

    public static String readString(byte[] bytes, int start, int length) {
        return new String(read(bytes, start, length));
    }

    public static Date readDate(byte[] bytes, int start) {
        byte[] b = read(bytes, start, 4);

        if (Arrays.equals(b, emptyDate))
            return null;

        Calendar date = new GregorianCalendar();
        date.setTimeInMillis(rootDate.getTimeInMillis());
        date.add(Calendar.SECOND, Ints.fromByteArray(b));
        return date.getTime();
    }

    public static int readInt(byte[] bytes, int start) {
        return Ints.fromByteArray(read(bytes, start, 4));
    }

    private static byte[] read(byte[] bytes, int start, int length) {
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++)
            result[i] = bytes[start + i];
        return result;
    }
}
