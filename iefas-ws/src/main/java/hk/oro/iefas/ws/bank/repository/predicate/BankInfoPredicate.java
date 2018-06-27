package hk.oro.iefas.ws.bank.repository.predicate;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.bank.entity.QBankInfo;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class BankInfoPredicate {

    private static final QBankInfo BANK_INFO = QBankInfo.bankInfo;

    public static BooleanExpression findActiveBank() {
        return BANK_INFO.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
    }

    public static OrderSpecifier<String> orderByBankName() {
        return BANK_INFO.bankName.asc();
    }

    public static BooleanExpression findOne(Integer id) {
        return BANK_INFO.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE).and(BANK_INFO.bankInfoId.eq(id));
    }
}
