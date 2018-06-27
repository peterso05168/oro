package hk.oro.iefas.core.jpa;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class AuditingDateTimeProvider implements DateTimeProvider {

    @Override
    public Calendar getNow() {
        return new GregorianCalendar();
    }
}