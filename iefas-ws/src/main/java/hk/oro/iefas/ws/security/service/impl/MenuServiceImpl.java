package hk.oro.iefas.ws.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.security.dto.MenuDTO;
import hk.oro.iefas.domain.security.entity.Menu;
import hk.oro.iefas.ws.security.repository.MenuReposiroty;
import hk.oro.iefas.ws.security.repository.assembler.MenuDTOAssembler;
import hk.oro.iefas.ws.security.service.MenuService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date: 2018-04-14 15:40:32 +0800 (週六, 14 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuReposiroty menuReposiroty;

    @Transactional(readOnly = true)
    @Override
    public List<MenuDTO> findAllTopMenu() {
        log.info("findAllTopMenu() start");
        List<Menu> menuList = menuReposiroty.findByParentMenuIdIsNullOrderByRecordSeq(Menu.class);
        List<MenuDTO> result = MenuDTOAssembler.toDTOs(menuList);
        log.info("findAllTopMenu() end");
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<MenuDTO> findByPostId(Integer postId) {
        log.info("findByPostId() start - PostId: " + postId);
        List<Menu> menus = menuReposiroty.findByPostId(postId);
        List<MenuDTO> result = MenuDTOAssembler.toDTOs(menus);
        log.info("findByPostId() end - " + result);
        return result;
    }

}
