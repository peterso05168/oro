package hk.oro.iefas.ws.shroff.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.shroff.entity.QShrChequeType;

public class ShrChequeTypePredicate {

    private final static QShrChequeType Q_SHR_CHEQUE_TYPE = QShrChequeType.shrChequeType;

    public static BooleanExpression findByChequeTypeDesc(String typeDesc) {
        if (typeDesc != null && !typeDesc.trim().isEmpty()) {
            return Q_SHR_CHEQUE_TYPE.chequeTypeDesc.equalsIgnoreCase(typeDesc)
                .and(Q_SHR_CHEQUE_TYPE.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE));
        }
        return null;
    }
}
