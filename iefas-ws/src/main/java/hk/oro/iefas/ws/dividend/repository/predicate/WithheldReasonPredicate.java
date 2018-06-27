package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.dividend.dto.SearchWithheldReasonDTO;
import hk.oro.iefas.domain.dividend.entity.QDivWithheldReasonType;

/**
 * @version $Revision: 3087 $ $Date: 2018-06-12 18:02:20 +0800 (週二, 12 六月 2018) $
 * @author $Author: noah.liang $
 */

public class WithheldReasonPredicate {

    private static final QDivWithheldReasonType qDivWithheldReasonType = QDivWithheldReasonType.divWithheldReasonType;

    public static BooleanExpression findByCriteria(SearchWithheldReasonDTO searchWithheldReasonDTO) {
        BooleanExpression withheldReasonCodepredicate = null;
        if (searchWithheldReasonDTO != null && searchWithheldReasonDTO.getWithheldReasonCode() != null
            && !searchWithheldReasonDTO.getWithheldReasonCode().trim().isEmpty()) {
            withheldReasonCodepredicate = qDivWithheldReasonType.withheldRsnCode
                .equalsIgnoreCase(searchWithheldReasonDTO.getWithheldReasonCode());
        }
        BooleanExpression withheldReasonStatus = null;
        if (searchWithheldReasonDTO != null && searchWithheldReasonDTO.getStatus() != null
            && !searchWithheldReasonDTO.getStatus().trim().isEmpty()) {
            withheldReasonStatus = qDivWithheldReasonType.status.eq(searchWithheldReasonDTO.getStatus());
        }
        return Expressions.allOf(withheldReasonCodepredicate, withheldReasonStatus);
    }

    public static BooleanExpression findByStatus() {
        return qDivWithheldReasonType.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
    }
}
