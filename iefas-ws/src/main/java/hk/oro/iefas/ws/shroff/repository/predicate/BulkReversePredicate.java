package hk.oro.iefas.ws.shroff.repository.predicate;

import java.util.Date;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.security.entity.QRole;
import hk.oro.iefas.domain.shroff.dto.SearchBulkReversalDTO;
import hk.oro.iefas.domain.shroff.entity.QShrBulkRvl;
import hk.oro.iefas.domain.shroff.entity.QShrBulkRvlItem;
import hk.oro.iefas.domain.system.dto.HolidayDTO;
import hk.oro.iefas.domain.system.dto.SearchHolidayDTO;
import hk.oro.iefas.domain.system.entity.QHoliday;

/**
 * @version $Revision $ $Date $
 * @author $Author $
 */
public class BulkReversePredicate {

    public static BooleanExpression findByCriteria(SearchBulkReversalDTO criteria) {
        QShrBulkRvl bulkReversal = QShrBulkRvl.shrBulkRvl;
        //1 - Bulk Reversal ID
        BooleanExpression bulkReversalIdExpression = null;
        if (criteria.getBulkReversalId() != null) {
        	bulkReversalIdExpression = bulkReversal.bulkReversalId.eq(criteria.getBulkReversalId());
        }
        //2 - Process Date
        BooleanExpression processDateExpression = null;
        if (criteria.getProcessDate() != null) {
        	processDateExpression = bulkReversal.processDate.
        			between(DateUtils.getStartDate(criteria.getProcessDate()), 
        			DateUtils.getEndDate(criteria.getProcessDate()));
        }
        //3 - Cut Off Date
        BooleanExpression cutOffDateExpression = null;
        if (criteria.getCutOffDate() != null) {
        	cutOffDateExpression = bulkReversal.cutOffDate.
        			between(DateUtils.getStartDate(criteria.getCutOffDate()), 
        			DateUtils.getEndDate(criteria.getCutOffDate()));
        }
        //4 - Voucher Number
        BooleanExpression voucherNoExpression = null;
        if (criteria.getVoucherNo() != null && !criteria.getVoucherNo().isEmpty()) {
        	voucherNoExpression = bulkReversal.shrVcrInfo.voucherNo.eq(criteria.getVoucherNo());
        }
        BooleanExpression statusException = bulkReversal.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(bulkReversalIdExpression, processDateExpression,
        		cutOffDateExpression, voucherNoExpression, statusException);
    }

    public static BooleanExpression findById(Long bulkReversalId) {
    	QShrBulkRvl bulkReversal = QShrBulkRvl.shrBulkRvl;
    	BooleanExpression idException = bulkReversal.bulkReversalId.eq(bulkReversalId);
        BooleanExpression statusException = bulkReversal.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(idException, statusException);
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
