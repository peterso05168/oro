package hk.oro.iefas.ws.dividend.repository.predicate;

import java.util.Date;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.casemgt.entity.QCase;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
import hk.oro.iefas.domain.dividend.dto.SearchOrFeeComputationCriteriaDTO;
import hk.oro.iefas.domain.dividend.entity.QDivCalCred;
import hk.oro.iefas.domain.dividend.entity.QDividendCal;

/**
 * @version $Revision: 3274 $ $Date: 2018-06-25 15:52:20 +0800 (週一, 25 六月 2018) $
 * @author $Author: vicki.huang $
 */
public class DividendCalPredicate {

    private static final QDividendCal DIVIDEND_CAL = QDividendCal.dividendCal;
    private static final QCase CASE = DIVIDEND_CAL.caseInfo;

    public static BooleanExpression findByCriteria(SearchOrFeeComputationCriteriaDTO criteriaDTO) {
        CaseNumberDTO caseNumber = criteriaDTO.getCaseNumber();
        String paymentType = criteriaDTO.getPaymentType();
        String status = criteriaDTO.getStatus();

        BooleanExpression caseNoPredicate = null;
        if (caseNumber != null) {
            caseNoPredicate = CASE.status.eq(CoreConstant.STATUS_ACTIVE)
                .and(CASE.caseNo.eq(Integer.valueOf(caseNumber.getCaseSequence())))
                .and(CASE.caseType.caseTypeCode.eq(caseNumber.getCaseType()))
                .and(CASE.caseYear.eq(Integer.valueOf(caseNumber.getCaseYear())));
        }
        BooleanExpression paymentTypePredicate = null;
        if (CommonUtils.isNotBlank(paymentType)) {
            // INT for Interest, DIV for Dividend
            paymentTypePredicate = DIVIDEND_CAL.divType.eq(paymentType);
        }
        BooleanExpression statusPredicate = null;
        if (CommonUtils.isNotBlank(status)) {
            statusPredicate = DIVIDEND_CAL.status.equalsIgnoreCase(status);
        } else {
            statusPredicate = DIVIDEND_CAL.status.notEqualsIgnoreCase(CoreConstant.STATUS_INACTIVE);
        }

        BooleanExpression credTypePredicate = null;
        if (criteriaDTO.getCreditorType() != null && criteriaDTO.getCreditorType().getCreditorTypeId() != null) {
            QDivCalCred qDivCalCred = QDividendCal.dividendCal.divDivCalCreds.any();
            credTypePredicate = qDivCalCred.status.eq(CoreConstant.STATUS_ACTIVE)
                .and(qDivCalCred.credType.creditorTypeId.eq(criteriaDTO.getCreditorType().getCreditorTypeId()));
        }

        return Expressions.allOf(caseNoPredicate, paymentTypePredicate, statusPredicate, credTypePredicate);
    }

    /**
     * The same case but not the current calculation
     * 
     * @param caseId
     * @param dividendCal
     * @return
     */
    public static BooleanExpression existedByCaseAndCalNot(Integer caseId, Integer dividendCalId) {
        BooleanExpression casePredicate = CASE.status.eq(CoreConstant.STATUS_ACTIVE).and(CASE.caseId.eq(caseId));

        BooleanExpression statusPredicate = DIVIDEND_CAL.status.eq(CoreConstant.STATUS_INACTIVE).not();
        BooleanExpression dividendCalPredicate = null;
        BooleanExpression dividendCalDatePredicate = null;
        if (dividendCalId != null && dividendCalId.intValue() > 0) {
            dividendCalPredicate = DIVIDEND_CAL.divCalId.eq(dividendCalId).not();
        }

        return Expressions.allOf(casePredicate, dividendCalPredicate, statusPredicate, dividendCalDatePredicate);
    }

    public static OrderSpecifier<Date> orderByDivDate() {
        return DIVIDEND_CAL.divDate.desc();
    }
}
