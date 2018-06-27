package hk.oro.iefas.domain.system.entity;

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

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the ORO_INFO database table.
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SYS_NOTIFICATION")
public class SysNotification extends UpdateTxnKeyable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NOTIFICATION_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SYS_NOTIFICATION_SEQ")
    @TableGenerator(name = "SYS_NOTIFICATION_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "NOTIFICATION_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer notificationId;

    @Column(name = "NOTIFICATION_CONTENT", length = 4000)
    private String notificationContent;

    @Temporal(TemporalType.DATE)
    @Column(name = "NOTIFICATION_EFF_START_DATE")
    private Date notificationEffStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "NOTIFICATION_EFF_END_DATE")
    private Date notificationEffEndDate;

    @Column(name = "STATUS", length = 3)
    private String status;

}
