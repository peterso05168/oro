package hk.oro.iefas.ws.security.service;

import java.util.List;

import hk.oro.iefas.domain.security.dto.MenuDTO;

/**
 * @version $Revision $ $Date: 2018-04-14 15:40:32 +0800 (週六, 14 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface MenuService {

    List<MenuDTO> findAllTopMenu();

    List<MenuDTO> findByPostId(Integer postId);
}
