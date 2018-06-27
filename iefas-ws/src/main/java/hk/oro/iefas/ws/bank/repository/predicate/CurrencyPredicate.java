package hk.oro.iefas.ws.bank.repository.predicate;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.bank.dto.CurrencySearchDTO;
import hk.oro.iefas.domain.bank.entity.QCurrency;

/**
 * @version $Revision: 3149 $ $Date: 2018-06-14 20:04:03 +0800 (週四, 14 六月 2018) $
 * @author $Author: noah.liang $
 */
public class CurrencyPredicate {

    private static final QCurrency CURRENCY = QCurrency.currency;

    public static Predicate findByCriteria(CurrencySearchDTO currencySearchDTO) {
        BooleanExpression codeExpression = null;
        if (CommonUtils.isNotBlank(currencySearchDTO.getCurrencyCode())) {
            codeExpression = CURRENCY.curcyCode.equalsIgnoreCase(currencySearchDTO.getCurrencyCode());
        }

        BooleanExpression nameExpression = null;
        if (CommonUtils.isNotBlank(currencySearchDTO.getCurrencyName())) {
            nameExpression
                = CURRENCY.curcyName.toLowerCase().contains(currencySearchDTO.getCurrencyName().toLowerCase());
        }

        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotBlank(currencySearchDTO.getStatus())) {
            statusExpression = CURRENCY.status.equalsIgnoreCase(currencySearchDTO.getStatus());
        }

        return Expressions.allOf(codeExpression, nameExpression, statusExpression);
    }

    public static Predicate findAll() {
        return CURRENCY.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
    }

    public static OrderSpecifier<String> sortByCurrencyName() {
        return CURRENCY.curcyName.asc();
    }

    public static BooleanExpression findOne(Integer currencyId) {
        return CURRENCY.curcyId.eq(currencyId).and(CURRENCY.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE));
    }

    public static BooleanExpression findByCuryCode(String curyCode) {
        if (curyCode != null && !curyCode.trim().isEmpty()) {
            return CURRENCY.curcyCode.equalsIgnoreCase(curyCode)
                .and(CURRENCY.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE));
        }
        return null;
    }
}
