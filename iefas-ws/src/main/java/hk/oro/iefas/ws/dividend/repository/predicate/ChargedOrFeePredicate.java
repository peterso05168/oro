package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.dividend.entity.QChargedOrFee;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class ChargedOrFeePredicate {

    private static final QChargedOrFee CHARGED_OR_FEE = QChargedOrFee.chargedOrFee;

    public static BooleanExpression findByDivCal(Integer divCalId) {
        BooleanExpression statusPredicate = CHARGED_OR_FEE.status.eq(CoreConstant.STATUS_ACTIVE);

        BooleanExpression divCalPredicate = null;
        if (divCalId != null) {
            divCalPredicate = CHARGED_OR_FEE.divCalId.eq(divCalId);
        }

        BooleanExpression codeStatusPredicate = CHARGED_OR_FEE.analysisCode.status.eq(CoreConstant.STATUS_ACTIVE);

        return Expressions.allOf(divCalPredicate, codeStatusPredicate, statusPredicate);
    }
}
