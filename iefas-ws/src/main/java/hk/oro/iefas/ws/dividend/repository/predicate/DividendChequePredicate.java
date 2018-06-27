package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.casemgt.entity.QCase;
import hk.oro.iefas.domain.dividend.dto.SearchDividendChequeDTO;
import hk.oro.iefas.domain.dividend.entity.QDividendCheque;

/**
 * @version $Revision: 2945 $ $Date: 2018-06-06 15:38:06 +0800 (Wed, 06 Jun 2018) $
 * @author $Author: vicki.huang $
 */
public class DividendChequePredicate {

    private static final QDividendCheque DIVIDEND_CHEQUE = QDividendCheque.dividendCheque;
    private static final QCase CASE = DIVIDEND_CHEQUE.shrCheque.caseInfo;

    public static BooleanExpression findByCriteria(SearchDividendChequeDTO criteriaDTO) {
        BooleanExpression statusPredicate = null;
        if (criteriaDTO.getStatus() != null && !"".equals(criteriaDTO.getStatus().trim())) {
            statusPredicate = DIVIDEND_CHEQUE.status.eq(criteriaDTO.getStatus());
        } else {
            statusPredicate = DIVIDEND_CHEQUE.status.eq(CoreConstant.STATUS_INACTIVE).not();
        }

        Integer caseId = -1;
        if (criteriaDTO.getCaseId() != null) {
            caseId = criteriaDTO.getCaseId();
        }
        BooleanExpression caseExpression = CASE.caseId.eq(caseId);

        BooleanExpression chequeNumberExpression = null;
        if (criteriaDTO.getChequeNumber() != null && !"".equals(criteriaDTO.getChequeNumber().trim())) {
            chequeNumberExpression = DIVIDEND_CHEQUE.shrCheque.chequeNo.like(criteriaDTO.getChequeNumber().trim());
        }

        return Expressions.allOf(caseExpression, statusPredicate, chequeNumberExpression);
    }

    public static BooleanExpression findByDivScheduleItemId(Integer divScheduleId) {
        if (divScheduleId != null && divScheduleId.intValue() > 0) {
            return DIVIDEND_CHEQUE.status.equalsIgnoreCase(CoreConstant.STATUS_INACTIVE).not()
                .and(DIVIDEND_CHEQUE.divScheduleItemId.eq(divScheduleId));
        }
        return null;
    }
}
