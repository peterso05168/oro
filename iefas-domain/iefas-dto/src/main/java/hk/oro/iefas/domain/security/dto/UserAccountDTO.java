/**
 * 
 */
package hk.oro.iefas.domain.security.dto;

import java.util.Date;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import hk.oro.iefas.domain.organization.dto.UserProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO extends TxnDTO {

    private Integer userAcId;

    private String loginName;

    private String password;

    private Date expiryDate;

    private Integer failCount;

    private Boolean lockedInd;

    private Date unlockTime;

    private Date lastLoginTime;

    private String status;

    private UserProfileDTO userProfile;
}
