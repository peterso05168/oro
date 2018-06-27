package hk.oro.iefas.domain.log.entity;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ACTIVITY_LOG")
public class ActivityLog {

    @Id
    @Column(name = "ACTIVITY_LOG_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ACTIVITY_LOG_SEQ")
    @TableGenerator(name = "ACTIVITY_LOG_SEQ", table = "TABLE_SEQ", pkColumnName = "seq_name",
        pkColumnValue = "ACTIVITY_LOG_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer activityLogId;

    @Column(name = "TRANSACTION_CODE", length = 10)
    private String transactionCode;

    @Column(name = "LOGIN_NAME", length = 30)
    private String loginName;

    @Column(name = "CURR_LOGIN_NAME", length = 30)
    private String currLoginName;

    @Column(name = "ACT_AS_LOGIN_NAME", length = 30)
    private String actAsLoginName;

    @Column(name = "ACTIVITY_RESULT", length = 3)
    private String activityResult;

    @Column(name = "ACTIVITY_CONTENT", length = 1000)
    private String activityContent;

    @Column(name = "ACTIVITY_DATETIME")
    private Date activityDatetime;

    @Column(name = "TXN_KEY_REF")
    private Long txnKeyRef;
}
