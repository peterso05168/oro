package hk.oro.iefas.web.system.systemadministration.holiday.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.annotation.RequestScope;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.web.funds.investment.bankrate.service.BankRateClientService;
import hk.oro.iefas.web.system.systemadministration.holiday.service.HolidayClientService;
import lombok.Getter;

@Named
@RequestScope
public class HolidayTemplateDownloadView implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private StreamedContent file;

    @Inject
    private HolidayClientService holidayClientService;

    @Getter
    private String template;

    private static final String CSV_FILE_EXTENDS = ".csv";
    private static final String CSV_MIME_TYPE = "text/comma-separated-values";

    @PostConstruct
    public void init() throws Exception {

        String fileRealName = "Import_Holidays" + CSV_FILE_EXTENDS;
        HttpServletResponse response
            = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=" + new String(fileRealName.getBytes(CoreConstant.UTF_8), CoreConstant.ISO_8859_1));
        response.setContentType(CSV_MIME_TYPE);
        ServletOutputStream outputStream = response.getOutputStream();
        template = holidayClientService.downloadHolidayTemplate();
        if (CommonUtils.isNotEmpty(template)) {
            outputStream.write(template.getBytes());
        }
        outputStream.close();
    }
}
