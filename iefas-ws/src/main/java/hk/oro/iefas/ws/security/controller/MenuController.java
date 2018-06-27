package hk.oro.iefas.ws.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.security.dto.MenuDTO;
import hk.oro.iefas.ws.security.service.MenuService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2064 $ $Date: 2018-04-14 15:40:32 +0800 (週六, 14 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_MENU)
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping(value = RequestUriConstant.URI_TOPMENU_LIST)
    public List<MenuDTO> findAllTopMenu() {
        log.info("findAllTopMenu() start - ");
        List<MenuDTO> result = menuService.findAllTopMenu();
        log.info("findAllTopMenu() end - " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_MENU_FINDBY_POSTID)
    public List<MenuDTO> findByPostId(@RequestBody Integer postId) {
        log.info("findByPostId() start - PostId: " + postId);
        List<MenuDTO> result = menuService.findByPostId(postId);
        log.info("findByPostId() end - " + result);
        return result;
    }

}
