package hk.oro.iefas.ws.bank.repository.predicate;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.bank.dto.BankDepositTypeDTO;
import hk.oro.iefas.domain.bank.entity.QBankDepositType;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class BankDepositTypePredicate {

    private static final QBankDepositType BANK_DEPOSIT_TYPE = QBankDepositType.bankDepositType;

    public static BooleanExpression findAll() {
        return BANK_DEPOSIT_TYPE.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
    }

    public static QBean<BankDepositTypeDTO> getQBean() {
        return Projections.bean(BankDepositTypeDTO.class, BANK_DEPOSIT_TYPE.bankDepositTypeId,
            BANK_DEPOSIT_TYPE.depositName, BANK_DEPOSIT_TYPE.depositCode, BANK_DEPOSIT_TYPE.status);
    }
}
