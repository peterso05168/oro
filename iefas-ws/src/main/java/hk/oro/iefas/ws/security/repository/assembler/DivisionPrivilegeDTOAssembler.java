package hk.oro.iefas.ws.security.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.security.dto.DivisionPrivilegeDTO;
import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import hk.oro.iefas.domain.security.entity.DivisionPrivilege;
import hk.oro.iefas.domain.security.entity.QDivisionPrivilege;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class DivisionPrivilegeDTOAssembler {

    public static DivisionPrivilegeDTO toDTO(DivisionPrivilege divisionPrivilege) {
        DivisionPrivilegeDTO dto = null;
        if (divisionPrivilege != null) {
            dto = DataUtils.copyProperties(divisionPrivilege, DivisionPrivilegeDTO.class);
        }
        return dto;
    }

    public static QBean<PrivilegeDTO> toResultDTO() {
        QDivisionPrivilege p = QDivisionPrivilege.divisionPrivilege;
        QBean<PrivilegeDTO> dto = Projections.bean(PrivilegeDTO.class, p.privilege.privilegeId,
            p.privilege.privilegeCode, p.privilege.privilegeDesc, p.privilege.privilegeName, p.privilege.privilegeType);
        return dto;
    }

    public static Page<PrivilegeDTO> toResultDTO(Page<DivisionPrivilege> page) {
        List<DivisionPrivilege> list = page.getContent();
        List<PrivilegeDTO> dtoList = new ArrayList<>();
        if (CommonUtils.isNotEmpty(list)) {
            list.stream().forEach(item -> {
                PrivilegeDTO privilege = PrivilegeDTOAssembler.toDTO(item.getPrivilege());
                dtoList.add(privilege);
            });
        }
        return new PageImpl<>(dtoList, new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
            page.getTotalElements());
    }

    public static List<DivisionPrivilegeDTO> toDTOList(List<DivisionPrivilege> privilegeList) {
        List<DivisionPrivilegeDTO> list = null;
        if (CommonUtils.isNotEmpty(privilegeList)) {
            list = new ArrayList<DivisionPrivilegeDTO>();
            for (DivisionPrivilege divisionPrivilege : privilegeList) {
                list.add(toDTO(divisionPrivilege));
            }
        }
        return list;
    }

}
