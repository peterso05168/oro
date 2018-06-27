package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.common.dto.CaseTypeDTO;
import hk.oro.iefas.domain.dividend.entity.QCaseFeeType;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class CaseFeeTypePredicate {

    private static final QCaseFeeType CASE_FEE_TYPE = QCaseFeeType.caseFeeType;

    public static BooleanExpression findByCriteria(CaseTypeDTO searchDTO) {
        BooleanExpression activePredicate = CASE_FEE_TYPE.status.eq(CoreConstant.STATUS_ACTIVE);
        BooleanExpression searchPredicate = null;
        if (searchDTO != null && searchDTO.getCaseTypeId() != null && searchDTO.getCaseTypeId().intValue() > 0) {
            searchPredicate = CASE_FEE_TYPE.caseType.caseTypeId.eq(searchDTO.getCaseTypeId());
        }
        return Expressions.allOf(activePredicate, searchPredicate);
    }

    public static OrderSpecifier<String> order() {
        return CASE_FEE_TYPE.caseFeeTypeItem.asc();
    }

    public static BooleanExpression findOne(Integer id) {
        return CASE_FEE_TYPE.status.eq(CoreConstant.STATUS_ACTIVE).and(CASE_FEE_TYPE.caseFeeTypeId.eq(id));
    }

    public static BooleanExpression findByName(String caseFeeTypeName) {
        return CASE_FEE_TYPE.status.eq(CoreConstant.STATUS_ACTIVE)
            .and(CASE_FEE_TYPE.caseFeeTypeItem.eq(caseFeeTypeName));
    }
}
