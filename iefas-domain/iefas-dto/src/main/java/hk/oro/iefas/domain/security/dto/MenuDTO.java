package hk.oro.iefas.domain.security.dto;

import java.util.List;

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
public class MenuDTO {

    private Integer menuId;

    private String menuDiaplayName;

    private String menuDesc;

    private Integer parentMenuId;

    private Integer recordSeq;

    private String menuIcon;

    private String menuUrl;

    private List<MenuDTO> subMenus;
}
