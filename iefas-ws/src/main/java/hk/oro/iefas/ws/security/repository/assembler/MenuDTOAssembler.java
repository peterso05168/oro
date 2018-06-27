package hk.oro.iefas.ws.security.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.security.dto.MenuDTO;
import hk.oro.iefas.domain.security.entity.Menu;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class MenuDTOAssembler {

    public static MenuDTO toDTO(Menu menu) {
        if (menu != null) {
            return DataUtils.copyProperties(menu, MenuDTO.class);
        }
        return null;
    }

    public static List<MenuDTO> toDTOs(List<Menu> menus) {
        List<MenuDTO> menuDTOs = null;
        if (CommonUtils.isNotEmpty(menus)) {
            menuDTOs = new ArrayList<>();
            for (Menu menu : menus) {
                menuDTOs.add(toDTO(menu));
            }
        }
        return menuDTOs;
    }
}
