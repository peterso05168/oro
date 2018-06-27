package hk.oro.iefas.ws.system.repository.predicate;

import java.util.Date;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.security.entity.QRole;
import hk.oro.iefas.domain.system.dto.HolidayDTO;
import hk.oro.iefas.domain.system.dto.SearchHolidayDTO;
import hk.oro.iefas.domain.system.entity.QHoliday;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class HolidayPredicate {

    public static BooleanExpression findByCriteria(SearchHolidayDTO criteria) {
        QHoliday holiday = QHoliday.holiday;
        BooleanExpression yearExpression = null;
        if (criteria.getYear() != null && criteria.getYear() > 0) {
            yearExpression = (holiday.holidayDate.year().eq(criteria.getYear()));
        }
        BooleanExpression statusException = holiday.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(yearExpression, statusException);
    }

    public static BooleanExpression findAll() {
        QRole role = QRole.role;
        BooleanExpression statusException = role.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return statusException;
    }

    public static BooleanExpression findByDate(HolidayDTO holidayDto) {
        QHoliday holiday = QHoliday.holiday;
        Date startDate = DateUtils.getStartDate(holidayDto.getHolidayDate());
        Date endDate = DateUtils.getEndDate(holidayDto.getHolidayDate());
        BooleanExpression dateException = holiday.holidayDate.between(startDate, endDate);
        BooleanExpression idException = null;
        // holidayDto.getHolidayId() == null is for Add holiday
        // while holidayDto.getHolidayId() != null is for Save holiday
        if (holidayDto.getHolidayId() != null) {
            idException = holiday.holidayId.ne(holidayDto.getHolidayId());
        }
        BooleanExpression statusException = holiday.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(dateException, statusException, idException);
    }
}
