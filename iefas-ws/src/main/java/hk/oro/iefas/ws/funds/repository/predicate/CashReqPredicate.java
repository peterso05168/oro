package hk.oro.iefas.ws.funds.repository.predicate;

import java.util.Date;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.funds.dto.CashRequirementResultDTO;
import hk.oro.iefas.domain.funds.dto.InvestmentTypeDTO;
import hk.oro.iefas.domain.funds.dto.SearchCashRequirementCriteriaDTO;
import hk.oro.iefas.domain.funds.entity.QCashReq;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class CashReqPredicate {

    private static final QCashReq CASH_REQUIREMENT = QCashReq.cashReq;

    public static Predicate findByCriteria(SearchCashRequirementCriteriaDTO criteriaDTO) {

        BooleanExpression investTypePredicate = null;
        if (criteriaDTO.getInvestmentTypeId() != null) {
            investTypePredicate = CASH_REQUIREMENT.investType.investmentTypeId.eq(criteriaDTO.getInvestmentTypeId());
        }

        Date fromDate = criteriaDTO.getFromDate();
        Date toDate = criteriaDTO.getToDate();
        BooleanExpression fromDatePredicate = null;
        BooleanExpression toDatePredicate = null;
        BooleanExpression periodOverlapPredicate = null;
        if (fromDate != null && toDate != null) {
            periodOverlapPredicate = periodOverlapPredicate(fromDate, toDate);
        } else {
            if (fromDate != null) {
                fromDatePredicate = CASH_REQUIREMENT.rqmtPeriodTo.goe(fromDate);
            }

            if (toDate != null) {
                toDatePredicate = CASH_REQUIREMENT.rqmtPeriodFrom.loe(toDate);
            }
        }

        return Expressions.allOf(investTypePredicate, periodOverlapPredicate, fromDatePredicate, toDatePredicate,
            CASH_REQUIREMENT.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE));
    }

    public static Predicate periodOverlap(CashRequirementResultDTO dto) {
        if (dto != null) {
            Date periodFrom = dto.getRqmtPeriodFrom();
            Date periodTo = dto.getRqmtPeriodTo();
            InvestmentTypeDTO investmentType = dto.getInvestmentType();
            if (periodFrom != null && periodTo != null && investmentType != null
                && investmentType.getInvestmentTypeId() != null) {
                BooleanExpression existOtherExpression = null;
                if (dto.getCashRequirementResultId() != null) {
                    existOtherExpression
                        = CASH_REQUIREMENT.cashRequirementId.eq(dto.getCashRequirementResultId()).not();
                }
                return periodOverlapPredicate(periodFrom, periodTo)
                    .and(CASH_REQUIREMENT.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE))
                    .and(CASH_REQUIREMENT.investType.investmentTypeId.eq(investmentType.getInvestmentTypeId()))
                    .and(existOtherExpression);
            }
        }
        return null;
    }

    private static BooleanExpression periodOverlapPredicate(Date startDate, Date endDate) {
        BooleanExpression fromBetween = CASH_REQUIREMENT.rqmtPeriodFrom.between(startDate, endDate);
        BooleanExpression toBetween = CASH_REQUIREMENT.rqmtPeriodTo.between(startDate, endDate);
        BooleanExpression outOfBetween
            = CASH_REQUIREMENT.rqmtPeriodFrom.before(startDate).and(CASH_REQUIREMENT.rqmtPeriodTo.after(endDate));
        return Expressions.anyOf(fromBetween, toBetween, outOfBetween);
    }
}
