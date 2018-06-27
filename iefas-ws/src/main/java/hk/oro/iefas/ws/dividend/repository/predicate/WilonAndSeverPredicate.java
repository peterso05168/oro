package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
import hk.oro.iefas.domain.dividend.entity.QDivWilonAndSever;

/**
 * @version $Revision: 3208 $ $Date: 2018-06-20 10:50:21 +0800 (週三, 20 六月 2018) $
 * @author $Author: dante.fang $
 */
public class WilonAndSeverPredicate {

    private static final QDivWilonAndSever WILON_AND_SEVER = QDivWilonAndSever.divWilonAndSever;

    public static Predicate findByCaseNumber(CaseNumberDTO caseNumber) {
        BooleanExpression activePredicate = WILON_AND_SEVER.status.eq(CoreConstant.STATUS_ACTIVE);
        BooleanExpression caseTypePredicate = null;
        BooleanExpression caseNumberPredicate = null;
        BooleanExpression caseYearPredicate = null;
        if (caseNumber != null) {
            String caseType = caseNumber.getCaseType();
            String caseSequence = caseNumber.getCaseSequence();
            String caseYear = caseNumber.getCaseYear();
            if (caseType != null && caseSequence != null && caseYear != null) {
                caseTypePredicate = WILON_AND_SEVER.caseInfo.caseType.caseTypeCode.equalsIgnoreCase(caseType);
                caseNumberPredicate = WILON_AND_SEVER.caseInfo.caseNo.eq(Integer.valueOf(caseSequence));
                caseYearPredicate = WILON_AND_SEVER.caseInfo.caseYear.eq(Integer.valueOf(caseYear));
            }
        }
        return Expressions.allOf(activePredicate, caseTypePredicate, caseNumberPredicate, caseYearPredicate);
    }

    public static Predicate findByCaseNumberAndCreditorId(CaseNumberDTO caseNumber, Integer creditorId) {
        BooleanExpression activePredicate = WILON_AND_SEVER.status.eq(CoreConstant.STATUS_ACTIVE);
        BooleanExpression caseTypePredicate = null;
        BooleanExpression caseNumberPredicate = null;
        BooleanExpression caseYearPredicate = null;
        BooleanExpression proofPredicate = null;
        if (caseNumber != null) {
            caseTypePredicate
                = WILON_AND_SEVER.caseInfo.caseType.caseTypeCode.equalsIgnoreCase(caseNumber.getCaseType());
            caseNumberPredicate = WILON_AND_SEVER.caseInfo.caseNo.eq(Integer.valueOf(caseNumber.getCaseSequence()));
            caseYearPredicate = WILON_AND_SEVER.caseInfo.caseYear.eq(Integer.valueOf(caseNumber.getCaseYear()));
        }
        if (creditorId != 0) {
            proofPredicate = WILON_AND_SEVER.caseCred.creditorId.eq(creditorId);
        }
        return Expressions.allOf(activePredicate, caseTypePredicate, caseNumberPredicate, caseYearPredicate,
            proofPredicate);
    }

    public static Predicate findByCaseInfoAndCreditorId(CaseDTO caseInfo, Integer creditorId) {
        BooleanExpression activePredicate = WILON_AND_SEVER.status.eq(CoreConstant.STATUS_ACTIVE);
        BooleanExpression caseTypePredicate = null;
        BooleanExpression caseNumberPredicate = null;
        BooleanExpression caseYearPredicate = null;
        BooleanExpression proofPredicate = null;
        if (caseInfo != null) {
            caseTypePredicate = WILON_AND_SEVER.caseInfo.caseType.caseTypeCode
                .equalsIgnoreCase(caseInfo.getCaseType().getCaseTypeCode());
            caseNumberPredicate = WILON_AND_SEVER.caseInfo.caseNo.eq(Integer.valueOf(caseInfo.getCaseNo()));
            caseYearPredicate = WILON_AND_SEVER.caseInfo.caseYear.eq(Integer.valueOf(caseInfo.getCaseYear()));
        }
        if (creditorId != 0) {
            proofPredicate = WILON_AND_SEVER.caseCred.creditorId.eq(creditorId);
        }
        return Expressions.allOf(activePredicate, caseTypePredicate, caseNumberPredicate, caseYearPredicate,
            proofPredicate);
    }

    public static Predicate findAll() {

        return null;

        // return FUNDS_PARAMETER.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
    }
}
