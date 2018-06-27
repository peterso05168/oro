package hk.oro.iefas.ws.adjudication.repository.predicate;

import com.querydsl.core.types.Predicate;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.adjudication.entity.QAdjApplyItem;

/**
 * @version $Revision: 3124 $ $Date: 2018-06-13 17:47:21 +0800 (Wed, 13 Jun 2018) $
 * @author $Author: vicki.huang $
 */
public class AdjApplyItemPredicate {
    private static final QAdjApplyItem ADJ_APPLY_ITEM = QAdjApplyItem.adjApplyItem;

    public static Predicate findByAdjResult(Integer adjResultId) {
        return ADJ_APPLY_ITEM.adjResultId.eq(adjResultId).and(ADJ_APPLY_ITEM.status.eq(CoreConstant.STATUS_ACTIVE));
    }
}
