package hk.oro.iefas.ws.core.constant;

import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class ReportConstant {

    public static enum ReportParam {
        RPT_ROOT, RPT_ENV, RPT_CONT_TMPL, REQUEST_REPORT_ID, REQUEST_CRITERIA_ID;
    }

    public static enum MainReportPage {
        A4L("Common/MainReport/A4LMAINREPORT"), A4P("Common/MainReport/A4PMAINREPORT");

        @Getter
        private String mainReportUri;

        private MainReportPage(String mainReportUri) {
            this.mainReportUri = mainReportUri;
        }
    }

    public static String RPT_ROOT = "reports";

    public static String RPT_ENV = "DEV";
}
