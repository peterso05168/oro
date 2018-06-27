package hk.oro.iefas.ws.adjudication.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.adjudication.entity.QAdjResult;
import hk.oro.iefas.domain.casemgt.entity.QCase;
import hk.oro.iefas.domain.dividend.dto.SearchAdjudicationCriteriaDTO;

/**
 * @version $Revision: 3163 $ $Date: 2018-06-15 16:09:30 +0800 (週五, 15 六月 2018) $
 * @author $Author: vicki.huang $
 */
public class AdjResultPredicate {
    private static final QAdjResult ADJ_RESULT = QAdjResult.adjResult;
    private static final QCase CASE = ADJ_RESULT.caseCred.caseInfo;

    public static Predicate findByCriteria(SearchAdjudicationCriteriaDTO criteriaDTO, String natureOfClaim) {
        Integer caseId = -1;
        if (criteriaDTO.getCaseId() != null) {
            caseId = criteriaDTO.getCaseId();
        }
        BooleanExpression caseExpression = CASE.caseId.eq(caseId);

        BooleanExpression natureOfClaimExpression = null;
        if (CommonUtils.isNotBlank(natureOfClaim)) {
            natureOfClaimExpression = ADJ_RESULT.natureOfClaim.equalsIgnoreCase(natureOfClaim);
        }

        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotBlank(criteriaDTO.getStatus())) {
            statusExpression = ADJ_RESULT.status.equalsIgnoreCase(criteriaDTO.getStatus());
        } else {
            statusExpression = ADJ_RESULT.status.notEqualsIgnoreCase(CoreConstant.STATUS_INACTIVE);
        }
        return Expressions.allOf(caseExpression, natureOfClaimExpression, statusExpression);
    }

    public static Predicate findApprovedAdjByCase(Integer caseId) {
        BooleanExpression caseExpression = ADJ_RESULT.caseCred.caseInfo.caseId.eq(caseId);

        BooleanExpression statusExpression = ADJ_RESULT.status.eq(CoreConstant.COMMON_STATUS_APPROVED);
        return Expressions.allOf(caseExpression, statusExpression);
    }

    public static Predicate findByCaseAndCreditorType(Integer caseId, Integer creditorTypeId) {
        BooleanExpression caseExpression = ADJ_RESULT.caseCred.caseInfo.caseId.eq(caseId);

        BooleanExpression creditorTypeExpression = ADJ_RESULT.creditorType.creditorTypeId.eq(creditorTypeId);

        BooleanExpression statusExpression = ADJ_RESULT.status.notEqualsIgnoreCase(CoreConstant.STATUS_INACTIVE);
        return Expressions.allOf(caseExpression, creditorTypeExpression, statusExpression);
    }

    public static Predicate findByCreditorId(Integer creditorId) {
        BooleanExpression creditorExpression = null;
        if (creditorId != null && creditorId.intValue() > 0) {
            creditorExpression = ADJ_RESULT.caseCred.creditorId.eq(creditorId);
        }
        return Expressions.allOf(creditorExpression);
    }

    public static Predicate findApprovedAdjByCreditor(Integer creditorId) {
        return ADJ_RESULT.caseCred.creditorId.eq(creditorId)
            .and(ADJ_RESULT.status.eq(CoreConstant.COMMON_STATUS_APPROVED));
    }
}
