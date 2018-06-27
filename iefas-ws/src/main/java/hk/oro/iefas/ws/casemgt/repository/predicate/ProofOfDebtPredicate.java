package hk.oro.iefas.ws.casemgt.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.casemgt.dto.SearchProofOfDebtCriteriaDTO;
import hk.oro.iefas.domain.counter.entity.QCtrProofDebt;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class ProofOfDebtPredicate {

    public static Predicate findByCriteria(SearchProofOfDebtCriteriaDTO criteria) {
        QCtrProofDebt debt = QCtrProofDebt.ctrProofDebt;

        BooleanExpression caseNumberExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getCaseNumber())) {
            caseNumberExpression = debt.caseInfo.wholeCaseNo.eq(criteria.getCaseNumber());
        }

        BooleanExpression caseNameExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getCaseName())) {
            caseNameExpression = debt.caseInfo.caseName.eq(criteria.getCaseName());
        }

        BooleanExpression proofNumberExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getProofNumber())) {
            proofNumberExpression = debt.proofNo.eq(criteria.getProofNumber());
        }

        BooleanExpression dateOfReceiptExpression = null;
        if (criteria.getDateOfReceipt() != null) {
            dateOfReceiptExpression = debt.receiptDate.eq(criteria.getDateOfReceipt());
        }

        BooleanExpression creditorIdExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getCommonCreditorSeqNo())) {
            creditorIdExpression = debt.commonCreditorSeqNo.like("%" + criteria.getCommonCreditorSeqNo() + "%");
        }

        BooleanExpression creditorNameExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getCommonCreditorName())) {
            creditorNameExpression = debt.commonCreditorName.eq(criteria.getCommonCreditorName());
        }

        BooleanExpression sectionIdExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getSectionSeqNo())) {
            sectionIdExpression = debt.sectionSeqNo.like("%" + criteria.getSectionSeqNo() + "%");
        }

        BooleanExpression sectionNameExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getSectionName())) {
            sectionNameExpression = debt.sectionName.eq(criteria.getSectionName());
        }

        BooleanExpression statusExpression = debt.status.eq(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(caseNumberExpression, caseNameExpression, proofNumberExpression,
            dateOfReceiptExpression, creditorIdExpression, creditorNameExpression, sectionIdExpression,
            sectionNameExpression, statusExpression);
    }
}
