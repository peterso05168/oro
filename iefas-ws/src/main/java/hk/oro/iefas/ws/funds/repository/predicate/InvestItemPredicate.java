package hk.oro.iefas.ws.funds.repository.predicate;

import java.util.Date;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.funds.dto.SearchInvestmentInstructiontCriteriaDTO;
import hk.oro.iefas.domain.funds.entity.QInvItem;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class InvestItemPredicate {

    private static final QInvItem INVEST_ITEM = QInvItem.invItem;

    public static Predicate findAll() {
        return INVEST_ITEM.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
    }

    public static OrderSpecifier<Date> sortByInvestDate() {
        return INVEST_ITEM.investmentDate.asc();
    }

    public static Predicate findByCriteria(SearchInvestmentInstructiontCriteriaDTO criteriaDTO) {
        BooleanExpression statusExpression = null;
        if (criteriaDTO.getInvestmentStatus() != null
            && criteriaDTO.getInvestmentStatus().getInvestmentStatusCode() != null
            && !"".equals(criteriaDTO.getInvestmentStatus().getInvestmentStatusCode())) {
            statusExpression = INVEST_ITEM.status.eq(criteriaDTO.getInvestmentStatus().getInvestmentStatusCode());
        }

        BooleanExpression fromExpression = null;
        if (criteriaDTO.getInvestmentDateFrom() != null) {
            fromExpression = INVEST_ITEM.investmentDate.after(criteriaDTO.getInvestmentDateFrom());
        }

        BooleanExpression toExpression = null;
        if (criteriaDTO.getInvestmentDateTo() != null) {
            toExpression = INVEST_ITEM.investmentDate.before(criteriaDTO.getInvestmentDateTo());
        }

        return Expressions.allOf(statusExpression, fromExpression, toExpression);
    }
}
