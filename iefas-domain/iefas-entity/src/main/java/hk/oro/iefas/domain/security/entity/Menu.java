package hk.oro.iefas.domain.security.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MENU")
public class Menu {

    @Id
    @Column(name = "MENU_ID")
    private Integer menuId;

    @Column(name = "MENU_DISPLAY_NAME", length = 60)
    private String menuDiaplayName;

    @Column(name = "MENU_DESC", length = 200)
    private String menuDesc;

    @Column(name = "PARENT_MENU_ID", length = 200)
    private Integer parentMenuId;

    @Column(name = "RECORD_SEQ")
    private Integer recordSeq;

    @Column(name = "MENU_ICON", length = 100)
    private String menuIcon;

    @Column(name = "MENU_URL", length = 200)
    private String menuUrl;

    @OneToMany(mappedBy = "parentMenuId", fetch = FetchType.LAZY)
    @OrderBy(value = "RECORD_SEQ")
    private List<Menu> subMenus;
}
