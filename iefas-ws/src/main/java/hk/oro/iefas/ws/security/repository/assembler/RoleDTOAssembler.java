package hk.oro.iefas.ws.security.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.organization.dto.SearchRoleResultDTO;
import hk.oro.iefas.domain.security.dto.RoleDTO;
import hk.oro.iefas.domain.security.entity.QRole;
import hk.oro.iefas.domain.security.entity.Role;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class RoleDTOAssembler {

    public static RoleDTO toDTO(Role role) {
        RoleDTO dto = null;
        if (role != null) {
            dto = DataUtils.copyProperties(role, RoleDTO.class);
        }
        return dto;
    }

    public static QBean<SearchRoleResultDTO> toResultDTO() {
        QRole r = QRole.role;
        QBean<SearchRoleResultDTO> dto = Projections.bean(SearchRoleResultDTO.class, r.roleId, r.roleName, r.roleDesc,
            r.division.divisionName, r.status);
        return dto;
    }

    public static List<RoleDTO> toDTOList(List<Role> roleList) {
        List<RoleDTO> list = null;
        if (CommonUtils.isNotEmpty(roleList)) {
            list = new ArrayList<RoleDTO>();
            for (Role role : roleList) {
                list.add(toDTO(role));
            }
        }
        return list;
    }
}
