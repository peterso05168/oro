package hk.oro.iefas.ws.report.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.domain.report.entity.QRequestCriteria;

public class RequestCriteriaPredicate {

    private static final QRequestCriteria REQUEST_CRITERIA = QRequestCriteria.requestCriteria;

    public static BooleanExpression findByReportRequestId(Long reportRequestId) {
        return REQUEST_CRITERIA.reportRequest.reportRequestId.eq(reportRequestId);
    }
}
