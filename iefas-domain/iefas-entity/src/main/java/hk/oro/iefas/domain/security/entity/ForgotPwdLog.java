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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "FORGOT_PWD_LOG")
public class ForgotPwdLog {

    @Id
    @Column(name = "FORGOT_PWD_LOG_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FORGOT_PWD_LOG_SEQ")
    @TableGenerator(name = "FORGOT_PWD_LOG_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "FORGOT_PWD_LOG_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer forgotPwdLogId;

    @Column(name = "USER_AC_ID")
    private Integer userAcId;

    @Column(name = "TOKEN", length = 50)
    private String token;

    @Column(name = "USED")
    private Boolean used;

    @Temporal(TemporalType.DATE)
    @Column(name = "FORGOT_PWD_DATE")
    private Date forgotPwdDate;

}
