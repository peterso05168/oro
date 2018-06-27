package hk.oro.iefas.domain.security.entity;

import java.io.Serializable;
import javax.persistence.*;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the LOGIN_SESSION database table.
 * 
 */
@Entity
@Table(name = "LOGIN_SESSION")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class LoginSession extends UpdateTxnKeyable {

    @Id
    @Column(name = "LOGIN_SESSION_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "LOGIN_SESSION_SEQ")
    @TableGenerator(name = "LOGIN_SESSION_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "LOGIN_SESSION_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long loginSessionId;

    @Column(name = "IP_ADDRESS", length = 100)
    private String ipAddress;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_ACTIVITY_TIME")
    private Date lastActivityTime;

    @Column(name = "LOGIN_NAME", length = 30)
    private String loginName;

    @Temporal(TemporalType.DATE)
    @Column(name = "LOGIN_TIME")
    private Date loginTime;

    @Column(name = "SESSION_ID", length = 100)
    private String sessionId;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to UserAccount
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_AC_ID")
    private UserAccount userAccount;

}