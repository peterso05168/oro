package hk.oro.iefas.ws.bank.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.bank.dto.SearchBankAccountInfoCriteriaDTO;
import hk.oro.iefas.domain.bank.entity.QBankInfo;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class BankAccountPredicate {

    public static Predicate findByCriteria(SearchBankAccountInfoCriteriaDTO criteriaDTO) {
        QBankInfo bankAccount = QBankInfo.bankInfo;

        BooleanExpression idExpression = null;
        if (criteriaDTO.getBankInfoId() != null && criteriaDTO.getBankInfoId().intValue() > 0) {
            idExpression = bankAccount.bankInfoId.eq(criteriaDTO.getBankInfoId());
        }

        BooleanExpression nameExpression = null;
        if (CommonUtils.isNotBlank(criteriaDTO.getBankName())) {
            nameExpression = bankAccount.bankName.toLowerCase().contains(criteriaDTO.getBankName().toLowerCase());
        }

        BooleanExpression shortNameExpression = null;
        if (CommonUtils.isNotBlank(criteriaDTO.getBankShortName())) {
            shortNameExpression
                = bankAccount.bankShortName.toLowerCase().contains(criteriaDTO.getBankShortName().toLowerCase());
        }

        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotBlank(criteriaDTO.getStatus())) {
            statusExpression = bankAccount.status.equalsIgnoreCase(criteriaDTO.getStatus());
        }

        return Expressions.allOf(idExpression, nameExpression, shortNameExpression, statusExpression);
    }

    public static Predicate findByBankName(String bankName) {
        QBankInfo bankAccount = QBankInfo.bankInfo;
        return bankAccount.bankName.eq(bankName).and(bankAccount.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE));
    }
}
