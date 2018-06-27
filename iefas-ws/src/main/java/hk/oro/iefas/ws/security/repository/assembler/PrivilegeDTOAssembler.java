package hk.oro.iefas.ws.security.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import hk.oro.iefas.domain.security.entity.Privilege;
import hk.oro.iefas.domain.security.entity.QPrivilege;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class PrivilegeDTOAssembler {

    public static PrivilegeDTO toDTO(Privilege privilege) {
        PrivilegeDTO dto = null;
        if (privilege != null) {
            dto = DataUtils.copyProperties(privilege, PrivilegeDTO.class);
        }
        return dto;
    }

    public static QBean<PrivilegeDTO> toResultDTO() {
        QPrivilege p = QPrivilege.privilege;
        QBean<PrivilegeDTO> dto = Projections.bean(PrivilegeDTO.class, p.privilegeId, p.privilegeCode, p.privilegeDesc,
            p.privilegeName, p.privilegeType);
        return dto;
    }

    public static List<PrivilegeDTO> toDTOList(List<Privilege> privilegeList) {
        List<PrivilegeDTO> list = null;
        if (CommonUtils.isNotEmpty(privilegeList)) {
            list = new ArrayList<PrivilegeDTO>();
            for (Privilege privilege : privilegeList) {
                list.add(toDTO(privilege));
            }
        }
        return list;
    }

}
