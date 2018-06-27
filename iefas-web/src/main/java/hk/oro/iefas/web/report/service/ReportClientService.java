package hk.oro.iefas.web.report.service;

import hk.oro.iefas.domain.report.DownloadFileVO;
import hk.oro.iefas.domain.report.ReportGeneratedVO;

/**
 * @version $Revision: 2164 $ $Date: 2018-04-23 15:54:10 +0800 (週一, 23 四月 2018) $
 * @author $Author: scott.feng $
 */
public interface ReportClientService {

    DownloadFileVO generateReport(ReportGeneratedVO reportGeneratedVO);
}
