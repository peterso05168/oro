/**
 * 
 */
package hk.oro.iefas.domain.security.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2015 $ $Date: 2018-04-11 14:13:56 +0800 (週三, 11 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_ATTEMPT_LOG")
public class UserAttemptLog {

    @Id
    @Column(name = "LOGIN_ATTEMPT_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "LOGIN_ATTEMPT_SEQ")
    @TableGenerator(name = "LOGIN_ATTEMPT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "LOGIN_ATTEMPT_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer loginAttemptId;

    @Column(name = "LOGIN_NAME", length = 30)
    private String loginName;

    @Column(name = "ATTEMPT_DATE")
    private Date attemptDate;

    @Column(name = "LOGIN_RESULT", length = 3)
    private String loginResult;

    @Column(name = "IP_ADDRESS", length = 100)
    private String ipAddress;

}
