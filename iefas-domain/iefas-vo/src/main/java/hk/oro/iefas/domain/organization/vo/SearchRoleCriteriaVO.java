package hk.oro.iefas.domain.organization.vo;

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
public class SearchRoleCriteriaVO {

    public Integer roleId;
    public Integer postId;
    public String roleName;
    public Integer divisionId;
    public String status;
}
