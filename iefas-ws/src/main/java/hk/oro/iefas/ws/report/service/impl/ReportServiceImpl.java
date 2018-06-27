package hk.oro.iefas.ws.report.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jaspersoft.jasperserver.jaxrs.client.apiadapters.reporting.ReportOutputFormat;
import com.jaspersoft.jasperserver.jaxrs.client.apiadapters.reporting.RunReportAdapter;
import com.jaspersoft.jasperserver.jaxrs.client.core.JasperserverRestClient;
import com.jaspersoft.jasperserver.jaxrs.client.core.RestClientConfiguration;
import com.jaspersoft.jasperserver.jaxrs.client.core.Session;
import com.jaspersoft.jasperserver.jaxrs.client.core.operationresult.OperationResult;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.report.ReportGeneratedDTO;
import hk.oro.iefas.ws.report.service.ReportService;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Value(value = "${report.server.url}")
    private String reportServerUrl;

    @Value(value = "${report.server.username}")
    private String username;

    @Value(value = "${report.server.password}")
    private String password;

    @SuppressWarnings("unchecked")
    @Override
    public InputStream generateReport(ReportGeneratedDTO reportInfo) {
        Session session = loginServer();
        RunReportAdapter reportAdapter = session.reportingService().report(reportInfo.getReportUnitUri())
            .prepareForRun(ReportOutputFormat.valueOf(reportInfo.getReportFormat().toUpperCase()));

        Map<String, Object> parameters = reportInfo.getParameters();
        if (CommonUtils.isNotEmpty(parameters)) {
            parameters.forEach((key, value) -> {
                if (value != null) {

                    if (value instanceof List) {
                        List<String> list = (List<String>)value;
                        reportAdapter.parameter(key, list);
                    } else {
                        reportAdapter.parameter(key, String.valueOf(value));
                    }
                }

            });
        }

        OperationResult<InputStream> result = reportAdapter.run();
        InputStream inputStream = result.getEntity();
        return inputStream;
    }

    private Session loginServer() {
        Session session
            = new JasperserverRestClient(new RestClientConfiguration(reportServerUrl)).authenticate(username, password);
        return session;
    }

}
