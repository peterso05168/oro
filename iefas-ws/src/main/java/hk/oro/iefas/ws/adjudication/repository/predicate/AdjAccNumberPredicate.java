package hk.oro.iefas.ws.adjudication.repository.predicate;

import com.querydsl.core.types.Predicate;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.adjudication.entity.QAdjAccNumber;

/**
 * @version $Revision: 3124 $ $Date: 2018-06-13 17:47:21 +0800 (Wed, 13 Jun 2018) $
 * @author $Author: vicki.huang $
 */
public class AdjAccNumberPredicate {
    private static final QAdjAccNumber ADJ_ACC_NUMBER = QAdjAccNumber.adjAccNumber;

    public static Predicate findByAdjResult(Integer adjResultId) {
        return ADJ_ACC_NUMBER.adjResultId.eq(adjResultId).and(ADJ_ACC_NUMBER.status.eq(CoreConstant.STATUS_ACTIVE));
    }
}
