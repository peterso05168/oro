package hk.oro.iefas.web.funds.investment.bankrate.view;

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
import hk.oro.iefas.core.util.MimeTypeUtils;
import hk.oro.iefas.domain.report.DownloadFileVO;
import hk.oro.iefas.web.funds.investment.bankrate.service.BankRateClientService;
import lombok.Getter;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Named
@RequestScope
public class FileDownloadView implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private StreamedContent file;

    @Inject
    private BankRateClientService bankRateClientService;

    @PostConstruct
    public void init() throws Exception {

        DownloadFileVO downloadFileVO = bankRateClientService.downloadBankRateTemplate();
        if (downloadFileVO != null) {
            String fileRealName = downloadFileVO.getFileName();
            if (CommonUtils.isEmpty(fileRealName)) {
                fileRealName = "Bank Rate." + downloadFileVO.getFileFormat();
            }

            HttpServletResponse response
                = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="
                + new String(fileRealName.getBytes(CoreConstant.UTF_8), CoreConstant.ISO_8859_1));
            response.setContentType(MimeTypeUtils.getMimeType(downloadFileVO.getFileFormat()));
            ServletOutputStream outputStream = response.getOutputStream();

            if (CommonUtils.isNotEmpty(downloadFileVO.getFileResult())) {
                outputStream.write(downloadFileVO.getFileResult());
            }
            outputStream.close();
        }

    }
}
