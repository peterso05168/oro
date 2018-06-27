package hk.oro.iefas.ws.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.domain.report.DownloadFileDTO;
import hk.oro.iefas.domain.report.ReportGeneratedDTO;
import hk.oro.iefas.ws.report.service.TestReportService;

@RestController
public class TestReportController {

    @Autowired
    private TestReportService testReportService;

    @RequestMapping("/testMergeReport")
    public DownloadFileDTO testMergeReport() throws Exception {

        ReportGeneratedDTO reportGeneratedDTO = new ReportGeneratedDTO();
        String reportName = "test";
        reportGeneratedDTO.setReportName(reportName);
        reportGeneratedDTO.setReportRequestId(2L);

        DownloadFileDTO downloadFileDTO = testReportService.testMergeReport(reportGeneratedDTO);

        return downloadFileDTO;
    }
}
