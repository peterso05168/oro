package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.domain.dividend.dto.SearchGazetteDescriptionDTO;
import hk.oro.iefas.domain.dividend.entity.QGazette;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */

public class GazettePredicate {

    private static final QGazette GAZETTE = QGazette.gazette;

    public static BooleanExpression findByCriteria(SearchGazetteDescriptionDTO criteria) {
        BooleanExpression gazetteCodeBooleanExpression = null;
        if (criteria != null && criteria.getGazetteDescriptionCode() != null
            && !criteria.getGazetteDescriptionCode().isEmpty()) {
            gazetteCodeBooleanExpression = GAZETTE.gazetteCode.equalsIgnoreCase(criteria.getGazetteDescriptionCode());
        }
        BooleanExpression gazetteStatusBooleanExpression = null;
        if (criteria != null && criteria.getStatus() != null && !criteria.getStatus().trim().isEmpty()) {
            gazetteStatusBooleanExpression = GAZETTE.status.eq(criteria.getStatus());
        }
        return Expressions.allOf(gazetteCodeBooleanExpression, gazetteStatusBooleanExpression);
    }

    public static BooleanExpression findLastGazetteCode() {
        BooleanExpression lastCodeExpression = null;
        lastCodeExpression = GAZETTE.gazetteId.eq(GAZETTE.gazetteId.max());
        return lastCodeExpression;
    }

}
