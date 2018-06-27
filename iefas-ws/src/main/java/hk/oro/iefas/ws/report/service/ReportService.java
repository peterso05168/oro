package hk.oro.iefas.ws.report.service;

import java.io.InputStream;

import hk.oro.iefas.domain.report.ReportGeneratedDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface ReportService {

    /**
     * Generate report
     * 
     * @param reportInfoVO
     * @return report content
     */
    InputStream generateReport(ReportGeneratedDTO reportInfo);

}
