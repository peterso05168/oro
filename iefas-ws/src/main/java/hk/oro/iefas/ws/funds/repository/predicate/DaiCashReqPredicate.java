package hk.oro.iefas.ws.funds.repository.predicate;

import java.util.Date;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.funds.dto.SearchDailyCashRequirementCriteriaDTO;
import hk.oro.iefas.domain.funds.entity.QDaiCashReq;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class DaiCashReqPredicate {

    private static final QDaiCashReq DAI_CASH_REQ = QDaiCashReq.daiCashReq;

    public static BooleanExpression isExistingPredicate(Date investDate, Integer investType) {
        return DAI_CASH_REQ.investDate.between(DateUtils.getStartDate(investDate), DateUtils.getEndDate(investDate))
            .and(DAI_CASH_REQ.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE))
            .and(DAI_CASH_REQ.investType.investmentTypeId.eq(investType));
    }

    public static Predicate findByCriteria(SearchDailyCashRequirementCriteriaDTO criteriaDTO) {
        BooleanExpression statusExpression = DAI_CASH_REQ.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);

        BooleanExpression investTypeExpression
            = DAI_CASH_REQ.investType.investmentTypeId.eq(criteriaDTO.getInvestmentTypeId());

        BooleanExpression dateExpression = DAI_CASH_REQ.investDate
            .between(DateUtils.getStartDate(criteriaDTO.getFromDate()), DateUtils.getEndDate(criteriaDTO.getToDate()));

        return Expressions.allOf(statusExpression, investTypeExpression, dateExpression);
    }
}
