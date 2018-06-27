package hk.oro.iefas.ws.security.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.security.dto.MenuPrivilegeDTO;
import hk.oro.iefas.domain.security.entity.MenuPrivilege;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class MenuPrivilegeDTOAssembler {

    public static MenuPrivilegeDTO toDTO(MenuPrivilege menuPrivilege) {
        MenuPrivilegeDTO dto = null;
        if (menuPrivilege != null) {
            dto = DataUtils.copyProperties(menuPrivilege, MenuPrivilegeDTO.class);
        }
        return dto;
    }

    public static List<MenuPrivilegeDTO> toDTOList(List<MenuPrivilege> menuPrivilegeList) {
        List<MenuPrivilegeDTO> list = null;
        if (CommonUtils.isNotEmpty(menuPrivilegeList)) {
            list = new ArrayList<MenuPrivilegeDTO>();
            for (MenuPrivilege menuPrivilege : menuPrivilegeList) {
                list.add(toDTO(menuPrivilege));
            }
        }
        return list;
    }

}
