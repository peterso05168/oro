package hk.oro.iefas.domain.security.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import hk.oro.iefas.domain.organization.entity.UserProfile;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount extends UpdateTxnKeyable {

    @Id
    @Column(name = "USER_AC_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_AC_SEQ")
    @TableGenerator(name = "USER_AC_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "USER_AC_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer userAcId;

    @Column(name = "LOGIN_NAME", length = 30)
    private String loginName;

    @Column(name = "PASSWORD", length = 100)
    private String password;

    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

    @Column(name = "FAIL_COUNT", length = 15)
    private Integer failCount;

    @Column(name = "LOCKED")
    private Boolean lockedInd;

    @Column(name = "UNLOCK_TIME")
    private Date unlockTime;

    @Column(name = "LAST_LOGIN_TIME")
    private Date lastLoginTime;

    @Column(name = "STATUS", length = 3)
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userAcId", referencedColumnName = "userAcId")
    private UserProfile userProfile;

}
