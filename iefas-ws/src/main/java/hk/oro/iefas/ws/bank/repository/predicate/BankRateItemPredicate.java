package hk.oro.iefas.ws.bank.repository.predicate;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.bank.entity.QBankRateItem;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class BankRateItemPredicate {

    private static final QBankRateItem BANK_RATE_ITEM = QBankRateItem.bankRateItem;

    public static BooleanExpression searchByBankRate(Integer bankRateId) {
        return BANK_RATE_ITEM.bankRate.bankRateId.eq(bankRateId)
            .and(BANK_RATE_ITEM.status.eq(CoreConstant.STATUS_ACTIVE));
    }

    public static BooleanExpression searchByBankRateAndBankInfo(Integer bankRateId, Integer bankInfoId) {
        return searchByBankRate(bankRateId).and(BANK_RATE_ITEM.bankInfo.bankInfoId.eq(bankInfoId));
    }

    public static OrderSpecifier<Integer> orderById() {
        return BANK_RATE_ITEM.bankDepositType.bankDepositTypeId.asc();
    }

    public static BooleanExpression findOne(Integer bankRateId, Integer bankInfoId, Integer bankDepositTypeId) {
        return searchByBankRateAndBankInfo(bankRateId, bankInfoId)
            .and(BANK_RATE_ITEM.bankDepositType.bankDepositTypeId.eq(bankDepositTypeId));
    }

}
