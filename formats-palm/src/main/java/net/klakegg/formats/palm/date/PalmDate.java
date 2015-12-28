package net.klakegg.formats.palm.date;

import com.google.common.primitives.Ints;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PalmDate {

    private static Calendar rootDate = new GregorianCalendar(1904, 0, 1, 0, 0);

    private Calendar ts;

    public PalmDate(Date date) {
        if (date == null)
            return;

        ts = new GregorianCalendar();
        ts.setTime(date);
    }

    public PalmDate(byte[] bytes) {
        this(Ints.fromByteArray(bytes));
    }

    public PalmDate(int seconds) {
        if (seconds == 0)
            return;

        ts = new GregorianCalendar();
        ts.setTimeInMillis(rootDate.getTimeInMillis());
        ts.add(Calendar.SECOND, seconds);
    }

    public Date getDate() {
        if (ts == null)
            return null;

        return ts.getTime();
    }

    public byte[] getBytes() {
        return Ints.toByteArray(getSeconds());
    }

    public int getSeconds() {
        if (ts == null)
            return 0;

        return (int) ((ts.getTimeInMillis() - rootDate.getTimeInMillis()) / 1000);
    }
}
