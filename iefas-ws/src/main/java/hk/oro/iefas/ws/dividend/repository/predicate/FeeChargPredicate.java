package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.domain.dividend.entity.QFeeCharg;

/**
 * @version $Revision: 2830 $ $Date: 2018-06-02 12:07:55 +0800 (週六, 02 六月 2018) $
 * @author $Author: vicki.huang $
 */
public class FeeChargPredicate {

    private static final QFeeCharg qFeeCharg = QFeeCharg.feeCharg;

    public static BooleanExpression findByCaseFeeTypeId(Integer caseTypeId) {
        if (caseTypeId != null && caseTypeId.intValue() > 0) {
            return qFeeCharg.caseFeeType.caseFeeTypeId.eq(caseTypeId);
        }
        return null;
    }

    public static BooleanExpression findByCaseFeeType(String type) {
        if (type != null && !"".equals(type)) {
            return qFeeCharg.caseFeeType.caseFeeTypeItem.eq(type);
        }
        return null;
    }
}
