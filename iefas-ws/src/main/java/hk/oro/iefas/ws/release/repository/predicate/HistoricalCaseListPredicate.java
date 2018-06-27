package hk.oro.iefas.ws.release.repository.predicate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.release.dto.SearchHistoricalCaseListDTO;
import hk.oro.iefas.domain.release.entity.QRelHistList;
import hk.oro.iefas.domain.security.entity.QRole;
import hk.oro.iefas.domain.system.dto.HolidayDTO;
import hk.oro.iefas.domain.system.dto.SearchHolidayDTO;
import hk.oro.iefas.domain.system.entity.QHoliday;
import lombok.extern.slf4j.Slf4j;
/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (Mon, 09 Apr 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
public class HistoricalCaseListPredicate {

	public static BooleanExpression findByCriteria(SearchHistoricalCaseListDTO criteria) {
        QRelHistList relHist = QRelHistList.relHistList;
        BooleanExpression yearExpression = null;
        BooleanExpression monthExpression = null;
        BooleanExpression dayExpression = null;
        if (criteria.getListDate() != null) {
        	Calendar calendar = new GregorianCalendar();
            calendar.setTime(criteria.getListDate());
        	yearExpression = (relHist.histCaseListDate.year().eq(calendar.get(Calendar.YEAR)));
            dayExpression = (relHist.histCaseListDate.dayOfMonth().eq(calendar.get(Calendar.DAY_OF_MONTH)));
            monthExpression = (relHist.histCaseListDate.month().eq(calendar.get(Calendar.MONTH)+1));
        }
        BooleanExpression statusException = relHist.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        
        if (criteria.getStatus() != null) {
        	statusException = relHist.status.eq(criteria.getStatus());
        } else {
        	statusException = relHist.status.ne(CoreConstant.STATUS_DELETE);
        }
        return Expressions.allOf(yearExpression, dayExpression, monthExpression, statusException);
    }

    public static BooleanExpression findAll() {
    	QRelHistList role = QRelHistList.relHistList;
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
