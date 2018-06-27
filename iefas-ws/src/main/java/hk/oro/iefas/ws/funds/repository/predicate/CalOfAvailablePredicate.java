package hk.oro.iefas.ws.funds.repository.predicate;

import java.util.Date;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.bank.dto.SearchPlacingDepositsCriteriaDTO;
import hk.oro.iefas.domain.funds.entity.QCalOfAvailable;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class CalOfAvailablePredicate {

    private static final QCalOfAvailable CAL_OF_AVAILABLE = QCalOfAvailable.calOfAvailable;

    public static BooleanExpression isExistingPredicate(Date investDate) {
        return CAL_OF_AVAILABLE.investmentDate
            .between(DateUtils.getStartDate(investDate), DateUtils.getEndDate(investDate))
            .and(CAL_OF_AVAILABLE.status.notEqualsIgnoreCase(CoreConstant.STATUS_INACTIVE));
    }

    // TODO
    public static Predicate findByCriteria(SearchPlacingDepositsCriteriaDTO criteriaDTO) {
        BooleanExpression dateExpression = null;
        if (criteriaDTO.getInvestmentDate() != null) {
            dateExpression = CAL_OF_AVAILABLE.investmentDate.eq(criteriaDTO.getInvestmentDate());
        }

        BooleanExpression statusExpression = CAL_OF_AVAILABLE.status.notEqualsIgnoreCase(CoreConstant.STATUS_INACTIVE);

        BooleanExpression currencyExpression = null;
        if (criteriaDTO.getCurrencyBasicInfo() != null && criteriaDTO.getCurrencyBasicInfo().getCurcyId() != null
            && criteriaDTO.getCurrencyBasicInfo().getCurcyId().intValue() > 0) {
        }
        return Expressions.allOf(dateExpression, currencyExpression, statusExpression);
    }

    public static OrderSpecifier<Date> orderByInvtDate() {
        return CAL_OF_AVAILABLE.investmentDate.desc();
    }

}
