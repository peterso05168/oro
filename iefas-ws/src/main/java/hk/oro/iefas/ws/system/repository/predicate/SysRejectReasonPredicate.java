package hk.oro.iefas.ws.system.repository.predicate;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.system.entity.QSysRejectReason;

public class SysRejectReasonPredicate {

    private static final QSysRejectReason SYS_REJECT_REASON = QSysRejectReason.sysRejectReason;

    public static BooleanExpression findActiveList() {
        return SYS_REJECT_REASON.status.eq(CoreConstant.STATUS_ACTIVE);
    }

    public static OrderSpecifier<String> order() {
        return SYS_REJECT_REASON.rejectReasonDesc.asc();
    }
}
