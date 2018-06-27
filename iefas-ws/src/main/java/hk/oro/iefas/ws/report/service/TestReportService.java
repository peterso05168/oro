package hk.oro.iefas.ws.report.service;

import java.io.InputStream;

import hk.oro.iefas.domain.report.DownloadFileDTO;
import hk.oro.iefas.domain.report.ReportGeneratedDTO;

public interface TestReportService {

    public DownloadFileDTO testMergeReport(ReportGeneratedDTO reportInfo) throws Exception;

    public InputStream testSingleReport(ReportGeneratedDTO reportInfo) throws Exception;
}
