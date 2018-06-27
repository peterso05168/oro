package hk.oro.iefas.ws.funds.repository.predicate;

import com.querydsl.core.types.Predicate;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.funds.entity.QFundsParameter;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class FundsParameterPredicate {

    private static final QFundsParameter FUNDS_PARAMETER = QFundsParameter.fundsParameter;

    public static Predicate findAll() {
        return FUNDS_PARAMETER.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
    }
}
