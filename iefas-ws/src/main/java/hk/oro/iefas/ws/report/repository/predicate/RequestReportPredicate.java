package hk.oro.iefas.ws.report.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.domain.report.entity.QRequestReport;

public class RequestReportPredicate {

    private static final QRequestReport REQUEST_REPORT = QRequestReport.requestReport;

    public static BooleanExpression findByReportRequestId(Long reportRequestId) {
        return REQUEST_REPORT.reportRequest.reportRequestId.eq(reportRequestId);
    }
}
