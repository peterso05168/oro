/**
 * 
 */
package hk.oro.iefas.domain.security.dto;

import java.util.Date;
import java.util.List;

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
public class UserDetailDTO {

    private String loginName;
    private String engName;
    private String password;
    private Integer postId;
    private String postTitle;
    private Integer divisionId;
    private String divisionName;
    private Boolean divisionAdminInd;
    private Boolean lockedInd;
    private Date unlockTime;
    private Integer failCount;
    private Date expiryDate;
    private String status;
    private List<PrivilegeDTO> privileges;

}
