package hk.oro.iefas.ws.casemgt.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.casemgt.dto.SearchCommonCreditorCriteriaDTO;
import hk.oro.iefas.domain.casemgt.entity.QCommonCreditor;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class CommonCreditorPredicate {

    private static final QCommonCreditor COMMON_CREDITOR = QCommonCreditor.commonCreditor;

    public static BooleanExpression findByCriteria(SearchCommonCreditorCriteriaDTO criteriaDTO) {
        BooleanExpression nameExpression = null;
        if (criteriaDTO.getCommonCreditorName() != null && !"".equals(criteriaDTO.getCommonCreditorName().trim())) {
            nameExpression = COMMON_CREDITOR.commonCreditorName.toLowerCase()
                .contains(criteriaDTO.getCommonCreditorName().toLowerCase());
        }

        BooleanExpression statusExpression = null;
        if (criteriaDTO.getStatus() != null && !"".equals(criteriaDTO.getStatus().trim())) {
            statusExpression = COMMON_CREDITOR.status.equalsIgnoreCase(criteriaDTO.getStatus());
        }
        return Expressions.allOf(nameExpression, statusExpression);
    }

    public static Predicate findByCommonCreditorName(String commonCreditorName) {
        BooleanExpression nameExpression = null;
        if (CommonUtils.isNotBlank(commonCreditorName)) {
            nameExpression = COMMON_CREDITOR.commonCreditorName.toLowerCase().trim()
                .contains(commonCreditorName.toLowerCase().trim());
        }

        BooleanExpression statusExpression = COMMON_CREDITOR.status.eq(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(nameExpression, statusExpression);
    }

    public static Predicate findByCommonCreditorSeqNo(String commonCreditorSeqNo) {
        BooleanExpression seqExpression = null;
        if (CommonUtils.isNotBlank(commonCreditorSeqNo)) {
            seqExpression = COMMON_CREDITOR.commonCreditorSeqNo.like("%" + commonCreditorSeqNo + "%");
        }

        BooleanExpression statusExpression = COMMON_CREDITOR.status.eq(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(seqExpression, statusExpression);
    }
}
