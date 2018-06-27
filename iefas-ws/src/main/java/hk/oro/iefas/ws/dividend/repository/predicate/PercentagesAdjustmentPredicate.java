package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.domain.adjudication.entity.QAdjResult;
import hk.oro.iefas.domain.casemgt.entity.Case;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class PercentagesAdjustmentPredicate {

    private static final QAdjResult qAdjResult = QAdjResult.adjResult;

    public static BooleanExpression findByCriteria(Case caseInfo) {
        BooleanExpression caseBooleanExpression = null;
        if (caseInfo != null && caseInfo.getCaseId() != null) {
            caseBooleanExpression = qAdjResult.caseCred.caseInfo.caseId.eq(caseInfo.getCaseId());

        }
        return caseBooleanExpression;
    }
}
