package hk.oro.iefas.ws.security.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.security.dto.ReportPrivilegeDTO;
import hk.oro.iefas.domain.security.entity.ReportPrivilege;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class ReportPrivilegeDTOAssembler {

    public static ReportPrivilegeDTO toDTO(ReportPrivilege reportPrivilege) {
        ReportPrivilegeDTO dto = null;
        if (reportPrivilege != null) {
            dto = DataUtils.copyProperties(reportPrivilege, ReportPrivilegeDTO.class);
        }
        return dto;
    }

    public static List<ReportPrivilegeDTO> toDTOList(List<ReportPrivilege> reportPrivilegeList) {
        List<ReportPrivilegeDTO> list = null;
        if (CommonUtils.isNotEmpty(reportPrivilegeList)) {
            list = new ArrayList<ReportPrivilegeDTO>();
            for (ReportPrivilege reportPrivilege : reportPrivilegeList) {
                list.add(toDTO(reportPrivilege));
            }
        }
        return list;
    }

}
