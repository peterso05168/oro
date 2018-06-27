package hk.oro.iefas.domain.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchPrivilegeCriteriaVO {

    public Integer roleId;
    public Integer menuId;
    public Integer divisionId;
    public Integer reportId;
    public Integer privilegeId;
    public String status;
}
