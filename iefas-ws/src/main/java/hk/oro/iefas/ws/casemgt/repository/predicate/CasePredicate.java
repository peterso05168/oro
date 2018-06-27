package hk.oro.iefas.ws.casemgt.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.BusinessUtils;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.casemgt.dto.SearchCaseDetailCriteriaDTO;
import hk.oro.iefas.domain.casemgt.entity.QCase;

/**
 * @version $Revision: 3208 $ $Date: 2018-06-20 10:50:21 +0800 (週三, 20 六月 2018) $
 * @author $Author: dante.fang $
 */
public class CasePredicate {

    public static Predicate findByCaseNo(String caseTypeCode, String caseSeqNo, String caseYear) {
        QCase casemgt = QCase.case$;

        String caseNumber = BusinessUtils.genCaseNumber(caseTypeCode, caseSeqNo, caseYear);

        BooleanExpression caseNumberExpression = null;
        if (CommonUtils.isNotBlank(caseNumber)) {
            caseNumberExpression = casemgt.wholeCaseNo.eq(caseNumber);
        }

        BooleanExpression statusExpression = null;
        statusExpression = casemgt.status.eq(CoreConstant.STATUS_ACTIVE);

        return Expressions.allOf(caseNumberExpression, statusExpression);
    }

    public static Predicate findByCriteria(SearchCaseDetailCriteriaDTO criteria) {
        QCase casemgt = QCase.case$;

        BooleanExpression caseNameExpression = null;
        if (criteria.getCaseName() != null) {
            caseNameExpression = casemgt.caseName.eq(criteria.getCaseName());
        }

        BooleanExpression typeCodeExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getCaseTypeCode())) {
            typeCodeExpression = casemgt.caseType.caseTypeCode.eq(criteria.getCaseTypeCode().toUpperCase());
        }

        BooleanExpression caseSerNoExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getCaseNo())) {
            caseSerNoExpression = casemgt.caseNo.eq(Integer.valueOf(criteria.getCaseNo()));
        }

        BooleanExpression caseYrExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getCaseYear())) {
            caseYrExpression = casemgt.caseYear.eq(Integer.valueOf(criteria.getCaseYear()));
        }

        BooleanExpression typeIdExpression = null;
        if (criteria.getCaseTypeId() != null) {
            typeIdExpression = casemgt.caseType.caseTypeId.eq(criteria.getCaseTypeId());
        }

        BooleanExpression outsiderTypeExpression = null;
        if (criteria.getOutsiderTypeId() != null) {
            outsiderTypeExpression = casemgt.outsider.outsiderType.outsiderTypeId.eq(criteria.getOutsiderTypeId());
        }

        BooleanExpression outsiderExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getOutsiderName())) {
            outsiderExpression = casemgt.outsider.outsiderName.eq(criteria.getOutsiderName());
        }

        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getStatus())) {
            statusExpression = casemgt.status.eq(criteria.getStatus());
        }
        return Expressions.allOf(caseNameExpression, typeCodeExpression, caseSerNoExpression, caseYrExpression,
            typeIdExpression, outsiderTypeExpression, outsiderExpression, statusExpression);
    }

    public static Predicate findByWholeCaseNo(String wholeCaseNumber) {
        QCase qCase = QCase.case$;
        BooleanExpression wholeCaseNumberExpression = null;
        if (CommonUtils.isNotBlank(wholeCaseNumber)) {
            wholeCaseNumberExpression = qCase.wholeCaseNo.eq(wholeCaseNumber);
        }
        BooleanExpression statusExpression = qCase.status.ne(CoreConstant.STATUS_DELETE);
        return Expressions.allOf(wholeCaseNumberExpression, statusExpression);
    }
}
