package hk.oro.iefas.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @version $Revision: 1918 $ $Date: 2018-04-03 14:35:09 +0800 (週二, 03 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class DateUtils {

    public static Date getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static boolean isSameDay(final Date date1, final Date date2) {
        return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
    }

    public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        return org.apache.commons.lang3.time.DateUtils.isSameDay(cal1, cal2);
    }

    public static Date getStartDate(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        }
        return null;
    }

    public static Date getEndDate(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            return calendar.getTime();
        }
        return null;
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date getDefaultSystemEndDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(2999, 11, 31, 23, 59, 59);
        instance.set(Calendar.MILLISECOND, 999);
        return instance.getTime();
    }

    public static Date addDays(final Date date, final int amount) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, amount);
    }

    public static Date addHours(final Date date, final int amount) {
        return org.apache.commons.lang3.time.DateUtils.addHours(date, amount);
    }

    public static Date addMinutes(final Date date, final int amount) {
        return org.apache.commons.lang3.time.DateUtils.addMinutes(date, amount);
    }

    public static Date getFormatedDate(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateStr);
    }

    public static String getFormatedDate(Date date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
