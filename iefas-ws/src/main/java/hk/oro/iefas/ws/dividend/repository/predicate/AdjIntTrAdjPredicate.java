package hk.oro.iefas.ws.dividend.repository.predicate;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.adjudication.entity.QAdjIntTrAdj;

/**
 * @version $Revision: 2961 $ $Date: 2018-06-06 20:14:53 +0800 (Wed, 06 Jun 2018) $
 * @author $Author: noah.liang $
 */
public class AdjIntTrAdjPredicate {
    private final static QAdjIntTrAdj Q_ADJ_INT_TR_ADJ = QAdjIntTrAdj.adjIntTrAdj;

    // TODO do not use 'in'
    public static BooleanExpression findByAdjResults(List<AdjResult> adjResults) {
        return Q_ADJ_INT_TR_ADJ.adjResult.in(adjResults)
            .and(Q_ADJ_INT_TR_ADJ.status.eq(CoreConstant.STATUS_INACTIVE).not());
    }

    public static BooleanExpression findByAdjResultId(Integer adjResultId) {
        return Q_ADJ_INT_TR_ADJ.adjResult.adjResultId.eq(adjResultId)
            .and(Q_ADJ_INT_TR_ADJ.status.eq(CoreConstant.STATUS_INACTIVE).not());
    }
}
