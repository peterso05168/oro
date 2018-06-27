package hk.oro.iefas.ws.adjudication.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.domain.adjudication.entity.QGroCode;
import hk.oro.iefas.domain.dividend.dto.SearchGroundCodeCriteriaDTO;

/**
 * @version $Revision: 2342 $ $Date: 2018-05-07 15:00:03 +0800 (週一, 07 五月 2018) $
 * @author $Author: noah.liang $
 */

public class GroundCodePredicate {

    private static final QGroCode GRO_CODE = QGroCode.groCode;

    public static BooleanExpression findByParam(SearchGroundCodeCriteriaDTO param) {
        BooleanExpression groundTypeNameExpression = null;
        if (param.getNatureOfClaim() != null && !param.getNatureOfClaim().trim().isEmpty()) {
            groundTypeNameExpression = GRO_CODE.natureOfClaim.equalsIgnoreCase(param.getNatureOfClaim());
        }

        BooleanExpression caseTypeIdExpression = null;

        if (param.getCaseType() != null && param.getCaseType().getCaseTypeId() != null) {
            Integer caseTypeId = param.getCaseType().getCaseTypeId();
            if (caseTypeId > 0) {
                caseTypeIdExpression = GRO_CODE.caseType.caseTypeId.eq(caseTypeId);
            }
        }

        BooleanExpression groundCodeStatusExpression = null;
        if (param.getStatus() != null && !param.getStatus().trim().isEmpty()) {
            groundCodeStatusExpression = GRO_CODE.status.eq(param.getStatus());
        }
        return Expressions.allOf(groundTypeNameExpression, caseTypeIdExpression, groundCodeStatusExpression);
    }
}
