package hk.oro.iefas.web.report.view;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.report.DownloadFileVO;
import hk.oro.iefas.web.report.service.ReportClientService;
import lombok.Getter;

@Named
@RequestScoped
public class ReportSearchBean {

    @Inject
    private ReportSearchView reportSearchView;

    @Inject
    private ReportClientService reportService;

    @Getter
    private StreamedContent file;

    public void generateReport() throws Exception {

        DownloadFileVO downloadFileVO = reportService.generateReport(null);
        String fileRealName = downloadFileVO.getFileName();

        HttpServletResponse response
            = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=" + new String(fileRealName.getBytes(CoreConstant.UTF_8), CoreConstant.ISO_8859_1));
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);

        byte[] fileResult = downloadFileVO.getFileResult();
        if (fileResult != null) {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(fileResult);
            outputStream.flush();
        }

        // this.file = new DefaultStreamedContent(inputStream, MediaType.APPLICATION_PDF_VALUE,
        // new String(fileRealName.getBytes(CoreConstant.UTF_8), CoreConstant.ISO_8859_1));
    }
}
