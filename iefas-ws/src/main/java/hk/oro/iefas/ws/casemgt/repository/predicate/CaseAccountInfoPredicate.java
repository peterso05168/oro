package hk.oro.iefas.ws.casemgt.repository.predicate;

import java.util.Calendar;
import java.util.Date;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CaseConstant;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.casemgt.entity.QCaseAccountInfo;

/**
 * @version $Revision: 3174 $ $Date: 2018-06-15 19:54:00 +0800 (週五, 15 六月 2018) $
 * @author $Author: dante.fang $
 */
public class CaseAccountInfoPredicate {

    public static Predicate findByCaseNumber(String caseNumber) {
        QCaseAccountInfo caseAccount = QCaseAccountInfo.caseAccountInfo;

        BooleanExpression accountExpression = null;
        if (CommonUtils.isNotBlank(caseNumber)) {
            caseNumber = caseNumber.replace("-", "-%");
            accountExpression = caseAccount.caseAcNumber.like(caseNumber);
        }

        BooleanExpression statusExpression = caseAccount.status.eq(CoreConstant.STATUS_ACTIVE);

        return Expressions.allOf(accountExpression, statusExpression);
    }

    public static Predicate findByAcNumber(String acNumber) {
        QCaseAccountInfo caseAccount = QCaseAccountInfo.caseAccountInfo;

        BooleanExpression accountExpression = null;
        if (CommonUtils.isNotBlank(acNumber)) {
            accountExpression = caseAccount.caseAcNumber.eq(acNumber);
        }

        BooleanExpression statusExpression = caseAccount.status.eq(CoreConstant.STATUS_ACTIVE);

        return Expressions.allOf(accountExpression, statusExpression);
    }

    public static Predicate findMainAccountByCase(Integer caseId) {
        QCaseAccountInfo caseAccount = QCaseAccountInfo.caseAccountInfo;

        BooleanExpression caseExpression = caseAccount.caseInfo.caseId.eq(caseId);

        BooleanExpression caseAcctTypeExpression
            = caseAccount.caseAccountType.caseAcTypeCode.equalsIgnoreCase(CaseConstant.MAIN_CASE_ACCOUNT_TYPE_CODE);

        BooleanExpression statusExpression = caseAccount.status.eq(CoreConstant.STATUS_ACTIVE);

        return Expressions.allOf(caseExpression, caseAcctTypeExpression, statusExpression);
    }

    public static Predicate findOldAccountByAcType(Integer accountTypeId, Date cutOffDate) {
        QCaseAccountInfo caseAccount = QCaseAccountInfo.caseAccountInfo;

        BooleanExpression accountExpression = null;
        if (accountTypeId != null) {
            accountExpression = caseAccount.caseAccountType.caseAcTypeId.eq(accountTypeId);
        }

        BooleanExpression dateExpression = null;
        if (cutOffDate != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(cutOffDate);
            c.add(Calendar.YEAR, -3);
            dateExpression = caseAccount.lastTransactionDate.before(DateUtils.getEndDate(c.getTime()));
        }

        BooleanExpression statusExpression = caseAccount.status.eq(CoreConstant.STATUS_ACTIVE);

        return Expressions.allOf(accountExpression, dateExpression, statusExpression);
    }
}
