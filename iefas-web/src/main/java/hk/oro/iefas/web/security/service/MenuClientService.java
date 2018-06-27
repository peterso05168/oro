package hk.oro.iefas.web.security.service;

import java.util.List;

import hk.oro.iefas.domain.security.vo.MenuVO;

/**
 * @version $Revision: 2064 $ $Date: 2018-04-14 15:40:32 +0800 (週六, 14 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface MenuClientService {

    List<MenuVO> findAllTopMenu();

    List<MenuVO> findByPostId(Integer postId);
}
