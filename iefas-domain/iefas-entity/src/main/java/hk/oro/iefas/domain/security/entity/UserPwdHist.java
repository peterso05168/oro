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

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
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
@Table(name = "USER_PWD_HIST")
public class UserPwdHist extends UpdateTxnKeyable {

    @Id
    @Column(name = "USER_AC_PWD_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_AC_PWD_SEQ")
    @TableGenerator(name = "USER_AC_PWD_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "USER_AC_PWD_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer userAcPwdId;

    @Column(name = "USER_AC_ID", length = 15)
    private Integer userAcId;

    @Column(name = "PREVIOUS_PASSWORD", length = 100)
    private String previousPassword;

    @Column(name = "PASSWORD_DATE")
    private Date passwordDate;

    @Column(name = "STATUS", length = 3)
    private String status;
}
