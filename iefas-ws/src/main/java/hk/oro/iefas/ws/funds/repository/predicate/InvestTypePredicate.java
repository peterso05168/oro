package hk.oro.iefas.ws.funds.repository.predicate;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.funds.entity.QInvType;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class InvestTypePredicate {

    private static final QInvType INVEST_TYPE = QInvType.invType;

    public static Predicate findAll() {
        return INVEST_TYPE.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE).and(INVEST_TYPE.displayOption.eq("Y"));
    }

    public static OrderSpecifier<String> sortByInvestTypeCode() {
        return INVEST_TYPE.investTypeCode.asc();
    }
}
