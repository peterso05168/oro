package hk.oro.iefas.ws.security.repository.predicate;

import com.querydsl.core.types.Predicate;

import hk.oro.iefas.domain.security.entity.QMenu;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class MenuPredicate {

    public static Predicate findAllTopMenu() {
        QMenu menu = QMenu.menu;
        return menu.parentMenuId.isNull();
    }
}
