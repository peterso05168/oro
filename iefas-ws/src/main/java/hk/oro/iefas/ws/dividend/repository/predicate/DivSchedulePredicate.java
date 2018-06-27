package hk.oro.iefas.ws.dividend.repository.predicate;

import java.util.Date;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.casemgt.entity.QCase;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
import hk.oro.iefas.domain.dividend.dto.CreateDividendScheduleDTO;
import hk.oro.iefas.domain.dividend.dto.SearchDividendScheduleDTO;
import hk.oro.iefas.domain.dividend.entity.QDivSchedule;

/**
 * @version $Revision: 3208 $ $Date: 2018-06-20 10:50:21 +0800 (週三, 20 六月 2018) $
 * @author $Author: dante.fang $
 */
public class DivSchedulePredicate {

    private static final QDivSchedule DIV_SCHEDULE = QDivSchedule.divSchedule;
    private static final QCase CASE = DIV_SCHEDULE.caseInfo;

    public static BooleanExpression exists(CreateDividendScheduleDTO dto, Integer caseInfoId) {
        Date startDate = DateUtils.getStartDate(dto.getPaymentEffectiveDate());
        Date endDate = DateUtils.getEndDate(dto.getPaymentEffectiveDate());

        return ((DIV_SCHEDULE.paymentDate.between(startDate, endDate)).or(DIV_SCHEDULE.paymentDate.after(endDate)))
            .and(CASE.caseId.eq(caseInfoId)).and(DIV_SCHEDULE.status.eq(CoreConstant.STATUS_INACTIVE).not());
    }

    public static BooleanExpression findByCriteria(SearchDividendScheduleDTO dto) {
        CaseNumberDTO caseNumber = dto.getCaseNumber();
        String scheduleType = dto.getScheduleType();
        String status = dto.getStatus();
        BooleanExpression caseNoPredicate = null;
        if (caseNumber != null) {
            caseNoPredicate
                = CASE.status.eq(CoreConstant.STATUS_ACTIVE)
                    .and(CASE.caseNo.eq(Integer.valueOf(caseNumber.getCaseSequence())))
                    .and(CASE.caseType.caseTypeCode.eq(caseNumber.getCaseType()))
                    .and(CASE.caseYear.eq(Integer.valueOf(caseNumber.getCaseYear())));
        }
        BooleanExpression scheduleTypePredicate = null;
        if (CommonUtils.isNotBlank(scheduleType)) {
            scheduleTypePredicate = DIV_SCHEDULE.scheduleType.eq(scheduleType);
        }
        BooleanExpression statusPredicate = null;
        if (CommonUtils.isNotBlank(status)) {
            statusPredicate = DIV_SCHEDULE.status.eq(status);
        }

        return Expressions.allOf(caseNoPredicate, scheduleTypePredicate, statusPredicate);
    }

    public static BooleanExpression findByDivCal(Integer divCalId) {
        return DIV_SCHEDULE.divDividendCal.divCalId.eq(divCalId)
            .and(DIV_SCHEDULE.status.eq(CoreConstant.STATUS_INACTIVE).not());
    }

    public static BooleanExpression findByCaseId(Integer caseId) {
        if (caseId != null && caseId.intValue() > 0) {
            return DIV_SCHEDULE.caseInfo.caseId.eq(caseId)
                .and(DIV_SCHEDULE.status.eq(CoreConstant.STATUS_INACTIVE).not());
        }
        return null;
    }
}
