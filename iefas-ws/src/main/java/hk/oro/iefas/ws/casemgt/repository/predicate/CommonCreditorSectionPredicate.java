package hk.oro.iefas.ws.casemgt.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.casemgt.entity.QCommonCreditorSection;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class CommonCreditorSectionPredicate {

    private static final QCommonCreditorSection COMMON_CREDITOR_SECTION = QCommonCreditorSection.commonCreditorSection;

    public static BooleanExpression findBySectionName(String sectionName) {
        BooleanExpression nameExpression = null;
        if (CommonUtils.isNotBlank(sectionName)) {
            nameExpression
                = COMMON_CREDITOR_SECTION.sectionName.toLowerCase().trim().contains(sectionName.toLowerCase().trim());
        }

        BooleanExpression statusExpression = COMMON_CREDITOR_SECTION.status.eq(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(nameExpression, statusExpression);
    }

    public static Predicate findBySectionSeqNo(String sectionSeqNo) {
        BooleanExpression seqExpression = null;
        if (CommonUtils.isNotBlank(sectionSeqNo)) {
            seqExpression = COMMON_CREDITOR_SECTION.sectionSeqNo.like("%" + sectionSeqNo + "%");
        }

        BooleanExpression statusExpression = COMMON_CREDITOR_SECTION.status.eq(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(seqExpression, statusExpression);
    }
}
