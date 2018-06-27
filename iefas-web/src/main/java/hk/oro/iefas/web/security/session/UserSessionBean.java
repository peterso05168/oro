package hk.oro.iefas.web.security.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import hk.oro.iefas.core.security.CustomUserDetails;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.security.vo.MenuVO;
import hk.oro.iefas.web.core.jsf.scope.SessionScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date: 2018-04-23 16:58:23 +0800 (週一, 23 四月 2018) $
 * @author $Author: Garrett.han $
 */
@Slf4j
@Named
@SessionScoped
public class UserSessionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private DefaultMenuModel menuModel;

    @Getter
    @Setter
    private Map<Integer, MenuElement> menuElementMap;

    @Getter
    @Setter
    private Map<Integer, Integer> menuIdMap;

    @Getter
    @Setter
    private String userFullName;

    @PostConstruct
    private void init() {
        log.info("======UserSessionBean init======");

        initUser();
    }

    private void initUser() {
        log.info("initUser() start");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
            this.userFullName = user.getUserFullName();
        }
        log.info("initUser() end");
    }

    public void buildMenu(List<MenuVO> allMenu) {
        log.info("buildMenu() start");
        if (menuElementMap == null) {
            menuElementMap = new HashMap<>();
            menuIdMap = new HashMap<>();
            if (CommonUtils.isNotEmpty(allMenu)) {
                menuModel = new DefaultMenuModel();
                for (MenuVO menuVO : allMenu) {
                    if (CommonUtils.isNotEmpty(menuVO.getSubMenus())) {
                        buildSubMenu(menuModel, menuVO);
                    } else {
                        buildMenuItem(menuModel, menuVO);
                    }
                }
            }

        }

        log.info("buildMenu() end");
    }

    private void buildMenuItem(DefaultMenuModel menuModel, MenuVO menuVO) {
        log.info("buildMenuItem() start");
        DefaultMenuItem menuItem = new DefaultMenuItem(menuVO.getMenuDiaplayName(), menuVO.getMenuIcon(), null);
        // menuItem.setRendered(false);
        menuItem.setOutcome(menuVO.getMenuUrl());
        menuModel.addElement(menuItem);
        menuElementMap.put(menuVO.getMenuId(), menuItem);
        menuIdMap.put(menuVO.getMenuId(), menuVO.getParentMenuId());
        log.info("buildMenuItem() end");
    }

    private void buildSubMenu(DefaultMenuModel menuModel, MenuVO menuVO) {
        log.info("buildSubMenu() start");
        DefaultSubMenu subMenu = new DefaultSubMenu(menuVO.getMenuDiaplayName(), menuVO.getMenuIcon());
        // subMenu.setRendered(false);
        menuModel.addElement(subMenu);
        menuElementMap.put(menuVO.getMenuId(), subMenu);
        menuIdMap.put(menuVO.getMenuId(), menuVO.getParentMenuId());

        for (MenuVO element : menuVO.getSubMenus()) {
            if (CommonUtils.isNotEmpty(element.getSubMenus())) {
                buildSubMenu(subMenu, element);
            } else {
                buildSubMenuItem(subMenu, element);
            }
        }
        log.info("buildSubMenu() end");
    }

    private void buildSubMenu(DefaultSubMenu subMenu, MenuVO menuVO) {
        log.info("buildSubMenu() start");
        DefaultSubMenu nextMenu = new DefaultSubMenu(menuVO.getMenuDiaplayName(), menuVO.getMenuIcon());
        // nextMenu.setRendered(false);
        subMenu.addElement(nextMenu);
        menuElementMap.put(menuVO.getMenuId(), nextMenu);
        menuIdMap.put(menuVO.getMenuId(), menuVO.getParentMenuId());

        for (MenuVO element : menuVO.getSubMenus()) {
            if (CommonUtils.isNotEmpty(element.getSubMenus())) {
                buildSubMenu(nextMenu, element);
            } else {
                buildSubMenuItem(nextMenu, element);
            }
        }
        log.info("buildSubMenu() end");
    }

    private void buildSubMenuItem(DefaultSubMenu subMenu, MenuVO menuVO) {
        log.info("buildSubMenuItem() start");
        DefaultMenuItem menuItem = new DefaultMenuItem(menuVO.getMenuDiaplayName(), null, null);
        // menuItem.setRendered(false);
        menuItem.setOutcome(menuVO.getMenuUrl());
        subMenu.addElement(menuItem);
        menuElementMap.put(menuVO.getMenuId(), menuItem);
        menuIdMap.put(menuVO.getMenuId(), menuVO.getParentMenuId());
        log.info("buildSubMenuItem() end");
    }

    public void renderMenu(List<MenuVO> menuVOs) {
        log.info("renderMenu() start");
        if (CommonUtils.isNotEmpty(menuVOs) && CommonUtils.isNotEmpty(menuIdMap)) {
            menuVOs.parallelStream().forEach(menuVO -> {
                lookForMenu(menuVO.getMenuId());
            });
        }

        log.info("renderMenu() end");
    }

    private void lookForMenu(Integer menuId) {
        if (menuIdMap.containsKey(menuId)) {
            renderMenuElement(menuId);

            Integer parentMenuId = menuIdMap.get(menuId);
            if (menuIdMap.containsKey(parentMenuId)) {
                lookForMenu(parentMenuId);
            }
        }
    }

    private void renderMenuElement(Integer menuId) {
        MenuElement menuElement = menuElementMap.get(menuId);
        if (menuElement instanceof DefaultSubMenu) {
            DefaultSubMenu subMenu = (DefaultSubMenu)menuElement;
            subMenu.setRendered(true);
        } else if (menuElement instanceof DefaultMenuItem) {
            DefaultMenuItem menuItem = (DefaultMenuItem)menuElement;
            menuItem.setRendered(true);
        }
    }

}
