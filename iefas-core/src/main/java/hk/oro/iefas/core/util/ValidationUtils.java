package hk.oro.iefas.core.util;

import java.util.Calendar;
import java.util.Date;

public class ValidationUtils {

    public static boolean validateDatePeriod(Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            startDate = calendar.getTime();

            calendar.setTime(endDate);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);

            return startDate.before(calendar.getTime());
        }
        return false;
    }

}
