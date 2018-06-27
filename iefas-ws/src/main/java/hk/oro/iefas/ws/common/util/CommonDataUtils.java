package hk.oro.iefas.ws.common.util;

import java.util.List;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.security.dto.MenuPrivilegeDTO;
import hk.oro.iefas.domain.security.dto.ReportPrivilegeDTO;

/**
 * @version $Revision: 2057 $ $Date: 2018-04-13 10:36:25 +0800 (週五, 13 四月 2018) $
 * @author $Author: dante.fang $
 */
public class CommonDataUtils {

    public static String genMenuName(List<MenuPrivilegeDTO> menuPrivileges) {
        StringBuffer result = new StringBuffer();
        if (CommonUtils.isNotEmpty(menuPrivileges)) {
            for (MenuPrivilegeDTO m : menuPrivileges) {
                String str = m.getMenu().getMenuDiaplayName() + "\n";
                result.append(str);
            }
        }
        if (result.toString().isEmpty()) {
            result.append("-");
        }
        return result.toString();
    }

    public static String genReportName(List<ReportPrivilegeDTO> reportPrivileges) {
        StringBuffer result = new StringBuffer();
        if (CommonUtils.isNotEmpty(reportPrivileges)) {
            for (ReportPrivilegeDTO r : reportPrivileges) {
                String str = r.getReportTemplate().getReportName() + "\n";
                result.append(str);
            }
        }
        if (result.toString().isEmpty()) {
            result.append("-");
        }
        return result.toString();
    }

}
