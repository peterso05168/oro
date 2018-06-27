package hk.oro.iefas.ws.security.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import hk.oro.iefas.domain.security.dto.RolePrivilegeDTO;
import hk.oro.iefas.domain.security.entity.QRolePrivilege;
import hk.oro.iefas.domain.security.entity.RolePrivilege;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class RolePrivilegeDTOAssembler {

    public static RolePrivilegeDTO toDTO(RolePrivilege rolePrivilege) {
        RolePrivilegeDTO dto = null;
        if (rolePrivilege != null) {
            dto = DataUtils.copyProperties(rolePrivilege, RolePrivilegeDTO.class);
        }
        return dto;
    }

    public static QBean<PrivilegeDTO> toResultDTO() {
        QRolePrivilege p = QRolePrivilege.rolePrivilege;
        QBean<PrivilegeDTO> dto = Projections.bean(PrivilegeDTO.class, p.privilege.privilegeId,
            p.privilege.privilegeCode, p.privilege.privilegeDesc, p.privilege.privilegeName, p.privilege.privilegeType);
        return dto;
    }

    public static List<RolePrivilegeDTO> toDTOList(List<RolePrivilege> reportPrivilegeList) {
        List<RolePrivilegeDTO> list = null;
        if (CommonUtils.isNotEmpty(reportPrivilegeList)) {
            list = new ArrayList<RolePrivilegeDTO>();
            for (RolePrivilege rolePrivilege : reportPrivilegeList) {
                list.add(toDTO(rolePrivilege));
            }
        }
        return list;
    }

}
