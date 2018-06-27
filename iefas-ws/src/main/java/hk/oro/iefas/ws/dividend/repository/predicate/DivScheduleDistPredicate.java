package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.dividend.entity.QDivScheduleDist;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
public class DivScheduleDistPredicate {

    private final static QDivScheduleDist QDIV_SCHEDULE_DIST = QDivScheduleDist.divScheduleDist;

    public static BooleanExpression findByAppAdjResultItemId(Integer appAdjResultItemId) {
        if (appAdjResultItemId != null && appAdjResultItemId.intValue() > 0) {
            return QDIV_SCHEDULE_DIST.appAdjResultItem.appResultItemId.eq(appAdjResultItemId)
                .and(QDIV_SCHEDULE_DIST.status.eq(CoreConstant.STATUS_ACTIVE));
        }
        return null;
    }

    public static BooleanExpression existByDivScheduleItemAndAdjType(Integer divScheduleItemId, Integer adjTypeId) {
        if (divScheduleItemId != null && divScheduleItemId.intValue() > 0) {
            return QDIV_SCHEDULE_DIST.divScheduleItem.divScheduleItemId.eq(divScheduleItemId)
                .and(QDIV_SCHEDULE_DIST.appAdjResultItem.status.eq(CoreConstant.STATUS_INACTIVE).not())
                .and(QDIV_SCHEDULE_DIST.appAdjResultItem.adjType.adjTypeId.eq(adjTypeId))
                .and(QDIV_SCHEDULE_DIST.status.eq(CoreConstant.STATUS_INACTIVE).not());
        }
        return null;
    }

    public static BooleanExpression findByDivScheduleItemId(Integer divScheduleItemId) {
        if (divScheduleItemId != null && divScheduleItemId.intValue() > 0) {
            return QDIV_SCHEDULE_DIST.divScheduleItem.divScheduleItemId.eq(divScheduleItemId)
                .and(QDIV_SCHEDULE_DIST.appAdjResultItem.status.eq(CoreConstant.STATUS_INACTIVE).not())
                .and(QDIV_SCHEDULE_DIST.status.eq(CoreConstant.STATUS_INACTIVE).not());
        }
        return null;
    }
}
