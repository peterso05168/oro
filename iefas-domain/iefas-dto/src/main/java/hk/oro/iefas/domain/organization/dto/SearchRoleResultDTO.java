package hk.oro.iefas.domain.organization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRoleResultDTO {

    private Integer roleId;
    private Integer postId;
    private String divisionName;
    private String roleName;
    private String roleDesc;
    private String status;

    public SearchRoleResultDTO(Integer roleId, String divisionName, String roleName, String roleDesc, String status) {
        super();
        this.roleId = roleId;
        this.divisionName = divisionName;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.status = status;
    }

}
