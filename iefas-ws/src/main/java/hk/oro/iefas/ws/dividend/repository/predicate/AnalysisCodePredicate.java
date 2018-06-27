package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.shroff.dto.SearchAnalysisCodeCriteriaDTO;
import hk.oro.iefas.domain.shroff.entity.QAnalysisCode;

/**
 * @version $Revision: 3058 $ $Date: 2018-06-11 21:25:47 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
public class AnalysisCodePredicate {

    private static final QAnalysisCode ANALYSIS_CODE = QAnalysisCode.analysisCode1;

    public static Predicate findByCriteria(SearchAnalysisCodeCriteriaDTO criteria) {

        BooleanExpression idExpression = null;
        if (criteria.getAnalysisCodeId() != null && criteria.getAnalysisCodeId() != 0) {
            idExpression = ANALYSIS_CODE.analysisCodeId.eq(criteria.getAnalysisCodeId());
        }

        BooleanExpression codeExpression = null;
        if (CommonUtils.isNotBlank(criteria.getAnalysisCode())) {
            codeExpression = ANALYSIS_CODE.analysisCode.eq(criteria.getAnalysisCode());
        }

        BooleanExpression voucherTypeExpression = null;
        if (criteria.getVoucherTypeId() != null && criteria.getVoucherTypeId() != 0) {
            voucherTypeExpression = ANALYSIS_CODE.voucherType.voucherTypeId.eq(criteria.getVoucherTypeId());
        }
        
        BooleanExpression voucherTypeCodeValueExpression = null;
        if(CommonUtils.isNotBlank(criteria.getVoucherTypeCodeValue())) {
            voucherTypeCodeValueExpression = ANALYSIS_CODE.voucherType.voucherTypeCode.equalsIgnoreCase(criteria.getVoucherTypeCodeValue());
        }

        BooleanExpression codeTypeExpression = null;
        if (criteria.getAnalysisCodeTypeId() != null && criteria.getAnalysisCodeTypeId() != 0) {
            codeTypeExpression = ANALYSIS_CODE.analysisCodeType.analysisCodeTypeId.eq(criteria.getAnalysisCodeTypeId());
        }
        
        BooleanExpression shortExpression = null;
        if (CommonUtils.isNotBlank(criteria.getShortName())) {
            shortExpression = ANALYSIS_CODE.shortName.eq(criteria.getShortName());
        }

        BooleanExpression fullExpression = null;
        if (CommonUtils.isNotBlank(criteria.getFullName())) {
            fullExpression = ANALYSIS_CODE.fullName.eq(criteria.getFullName());
        }

        BooleanExpression feeExpression = null;
        if (CommonUtils.isNotBlank(criteria.getRealizationFee())) {
            feeExpression = ANALYSIS_CODE.realizationFee.eq(criteria.getRealizationFee());
        }

        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotBlank(criteria.getStatus())) {
            statusExpression = ANALYSIS_CODE.status.eq(criteria.getStatus());
        }

        return Expressions.allOf(idExpression, codeExpression, voucherTypeExpression, voucherTypeCodeValueExpression, 
            codeTypeExpression, shortExpression, fullExpression, feeExpression, statusExpression);
    }
}
