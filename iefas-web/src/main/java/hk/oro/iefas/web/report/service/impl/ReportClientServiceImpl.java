package hk.oro.iefas.web.report.service.impl;

import org.springframework.stereotype.Service;

import hk.oro.iefas.domain.report.DownloadFileVO;
import hk.oro.iefas.domain.report.ReportGeneratedVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.report.service.ReportClientService;

/**
 * @version $Revision: 2164 $ $Date: 2018-04-23 15:54:10 +0800 (週一, 23 四月 2018) $
 * @author $Author: scott.feng $
 */
@Service
public class ReportClientServiceImpl extends BaseClientService implements ReportClientService {

    @Override
    public DownloadFileVO generateReport(ReportGeneratedVO reportGeneratedVO) {
        DownloadFileVO downloadFileVO = this.postForObject("/testMergeReport", null, DownloadFileVO.class);
        return downloadFileVO;
    }

}
