package hk.oro.iefas.ws.bank.repository.predicate;

import java.util.Date;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.bank.dto.SearchUploadBankRateCriteriaDTO;
import hk.oro.iefas.domain.bank.entity.QBankRate;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class BankRatePredicate {

    private static final QBankRate BANK_RATE = QBankRate.bankRate;

    public static BooleanExpression findAll(SearchUploadBankRateCriteriaDTO criteria) {
        Date effectiveFrom = DateUtils.getStartDate(criteria.getEffectiveFrom());
        Date effectiveTo = DateUtils.getEndDate(criteria.getEffectiveTo());
        BooleanExpression betweenExpression = null;
        BooleanExpression goeExpression = null;
        BooleanExpression loeExpression = null;
        if (effectiveFrom != null && effectiveTo != null) {
            betweenExpression = BANK_RATE.investDate.between(effectiveFrom, effectiveTo);
        } else {
            if (effectiveFrom != null) {
                goeExpression = BANK_RATE.investDate.goe(effectiveFrom);
            }

            if (effectiveTo != null) {
                loeExpression = BANK_RATE.investDate.loe(effectiveTo);
            }
        }

        return Expressions.allOf(betweenExpression, goeExpression, loeExpression,
            BANK_RATE.status.eq(CoreConstant.STATUS_ACTIVE));
    }

    public static BooleanExpression searchBankRate(Integer bankRateId) {
        BooleanExpression booleanExpression
            = BANK_RATE.bankRateId.eq(bankRateId).and(BANK_RATE.status.eq(CoreConstant.STATUS_ACTIVE));
        return booleanExpression;
    }

    public static BooleanExpression searchBankRateByInvestDate(Date investDate) {
        Date startDate = DateUtils.getStartDate(investDate);
        Date endDate = DateUtils.getEndDate(investDate);
        BooleanExpression booleanExpression
            = BANK_RATE.investDate.between(startDate, endDate).and(BANK_RATE.status.eq(CoreConstant.STATUS_ACTIVE));
        return booleanExpression;
    }

}
