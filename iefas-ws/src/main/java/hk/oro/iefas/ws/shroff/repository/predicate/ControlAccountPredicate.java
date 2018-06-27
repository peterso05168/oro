package hk.oro.iefas.ws.shroff.repository.predicate;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.shroff.dto.SearchControlAccountDTO;
import hk.oro.iefas.domain.shroff.entity.QShrCtlAc;

/**
 * @version $Revision: 3139 $ $Date: 2018-06-14 11:07:10 +0800 (週四, 14 六月 2018) $
 * @author $Author: dante.fang $
 */
public class ControlAccountPredicate {

    public static OrderSpecifier<String> sortByControllerAccountName() {
        QShrCtlAc shrCtlAc = QShrCtlAc.shrCtlAc;
        return shrCtlAc.ctlAcName.asc();
    }

    public static Predicate findAll() {
        QShrCtlAc shrCtlAc = QShrCtlAc.shrCtlAc;
        return shrCtlAc.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
    }

    public static Predicate findByCriteria(SearchControlAccountDTO criteria) {
        QShrCtlAc shrCtlAc = QShrCtlAc.shrCtlAc;
        BooleanExpression ctlAcCodeExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getCtlAcCode())) {
            ctlAcCodeExpression = shrCtlAc.ctlAcCode.eq(criteria.getCtlAcCode());
        }
        BooleanExpression ctlAcNameExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getCtlAcName())) {
            ctlAcNameExpression = shrCtlAc.ctlAcName.eq(criteria.getCtlAcName());
        }
        BooleanExpression balanceNatureExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getBalanceNature())) {
            balanceNatureExpression = shrCtlAc.balanceNature.eq(criteria.getBalanceNature());
        }
        BooleanExpression ctlAcTypeIdExpression = null;
        if (criteria.getAccountTypeId() != null) {
            ctlAcTypeIdExpression = shrCtlAc.shrCtlAcType.ctlAcTypeId.eq(criteria.getAccountTypeId());
        }
        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getStatus())) {
            statusExpression = shrCtlAc.status.eq(criteria.getStatus());
        } else {
            statusExpression = shrCtlAc.status.ne(CoreConstant.STATUS_DELETE);
        }
        return Expressions.allOf(ctlAcCodeExpression, ctlAcNameExpression, ctlAcTypeIdExpression, statusExpression,
            balanceNatureExpression);
    }

}
