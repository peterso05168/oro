package hk.oro.iefas.ws.report.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.oro.iefas.domain.report.DownloadFileDTO;
import hk.oro.iefas.domain.report.ReportGeneratedDTO;
import hk.oro.iefas.domain.report.entity.ReportInfo;
import hk.oro.iefas.domain.report.entity.ReportRequest;
import hk.oro.iefas.domain.report.entity.RequestCriteria;
import hk.oro.iefas.domain.report.entity.RequestReport;
import hk.oro.iefas.ws.core.constant.ReportConstant;
import hk.oro.iefas.ws.report.repository.ReportRequestRepository;
import hk.oro.iefas.ws.report.repository.RequestCriteriaRepository;
import hk.oro.iefas.ws.report.repository.RequestReportRepository;
import hk.oro.iefas.ws.report.repository.predicate.RequestCriteriaPredicate;
import hk.oro.iefas.ws.report.repository.predicate.RequestReportPredicate;
import hk.oro.iefas.ws.report.service.ReportService;
import hk.oro.iefas.ws.report.service.TestReportService;

@Service
public class TestReportServiceImpl implements TestReportService {

    @Autowired
    private ReportService reportService;

    @Autowired
    private RequestCriteriaRepository requestCriteriaRepository;

    @Autowired
    private RequestReportRepository requestReportRepository;

    @Autowired
    private ReportRequestRepository reportRequestRepository;

    @Override
    public DownloadFileDTO testMergeReport(ReportGeneratedDTO reportGeneratedDTO) throws Exception {
        Long reportRequestId = reportGeneratedDTO.getReportRequestId();
        ReportRequest reportRequest = reportRequestRepository.findOne(reportRequestId);
        String title = reportRequest.getTitle();
        if (title != null) {
            reportGeneratedDTO.setReportName(title);
        }
        String reportFormat = null;
        DownloadFileDTO downloadFileDTO = new DownloadFileDTO();
        String fileRealName = reportGeneratedDTO.getReportName() + ".zip";
        downloadFileDTO.setFileName(fileRealName);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream outputStream = new ZipOutputStream(bos);
        outputStream.setLevel(Deflater.BEST_COMPRESSION);

        Iterable<RequestReport> requestReports
            = requestReportRepository.findAll(RequestReportPredicate.findByReportRequestId(reportRequestId));
        if (requestReports != null) {
            Long requestReportId = null;
            String reportRootUri = ReportConstant.RPT_ROOT + "/" + ReportConstant.RPT_ENV + "/";
            Map<String, Object> rootParameters = new HashMap<>();
            rootParameters.put(ReportConstant.ReportParam.RPT_ROOT.name(), ReportConstant.RPT_ROOT);
            rootParameters.put(ReportConstant.ReportParam.RPT_ENV.name(), ReportConstant.RPT_ENV);
            for (RequestReport requestReport : requestReports) {
                requestReportId = requestReport.getRequestReportId();
                String merge = requestReport.getMerge();
                reportFormat = requestReport.getRptFormat();
                String fileExtension = "." + reportFormat;
                ReportInfo info = requestReport.getReportInfo();
                String reportName = info.getReportName();
                reportGeneratedDTO.setReportUnitUri(reportRootUri
                    + ReportConstant.MainReportPage.valueOf(info.getReportPageFormat()).getMainReportUri());
                reportGeneratedDTO.setReportFormat(reportFormat);

                Map<String, Object> parameters = new HashMap<>();
                reportGeneratedDTO.setParameters(parameters);
                parameters.put(ReportConstant.ReportParam.REQUEST_REPORT_ID.name(), String.valueOf(requestReportId));
                parameters.put(ReportConstant.ReportParam.RPT_CONT_TMPL.name(), info.getReportContentUri());
                parameters.putAll(rootParameters);

                String rptName = null;
                byte[] bs = new byte[1024];
                int index = 0;
                if ("Y".equals(merge)) {
                    InputStream inputStream = this.reportService.generateReport(reportGeneratedDTO);
                    if (inputStream != null && inputStream.available() > 0) {
                        rptName = reportName + fileExtension;
                        outputStream.putNextEntry(new ZipEntry(rptName));

                        int length = 0;
                        while ((length = inputStream.read(bs)) > 0) {
                            outputStream.write(bs, 0, length);
                        }
                        outputStream.closeEntry();
                        IOUtils.closeQuietly(inputStream);
                    }

                } else {
                    Iterable<RequestCriteria> iterable = requestCriteriaRepository
                        .findAll(RequestCriteriaPredicate.findByReportRequestId(reportRequestId));
                    if (iterable != null) {
                        int length = 0;
                        for (Iterator<RequestCriteria> iterator = iterable.iterator(); iterator.hasNext();) {
                            RequestCriteria requestCriteria = iterator.next();
                            if ("Y".equals(merge)) {
                                // if ("CASE_NO_START".equals(requestCriteria.getCriteriaName())) {
                                // continue;
                                // }

                            } else {
                                parameters.put(ReportConstant.ReportParam.REQUEST_CRITERIA_ID.name(),
                                    String.valueOf(requestCriteria.getRequestCriteriaId()));
                            }

                            InputStream inputStream = this.reportService.generateReport(reportGeneratedDTO);
                            if (inputStream != null && inputStream.available() > 0) {
                                if (index == 0) {
                                    rptName = reportName + fileExtension;
                                } else {
                                    rptName = reportName + "(" + index + ")" + fileExtension;
                                }
                                outputStream.putNextEntry(new ZipEntry(rptName));

                                while ((length = inputStream.read(bs)) > 0) {
                                    outputStream.write(bs, 0, length);
                                }
                                outputStream.closeEntry();
                                IOUtils.closeQuietly(inputStream);
                            }
                            index++;

                        }
                    } else {

                    }
                }

            }
        }
        IOUtils.closeQuietly(outputStream);
        downloadFileDTO.setFileResult(bos.toByteArray());

        IOUtils.closeQuietly(bos);

        return downloadFileDTO;
    }

    @Override
    public InputStream testSingleReport(ReportGeneratedDTO reportInfo) throws Exception {

        return null;
    }

}
