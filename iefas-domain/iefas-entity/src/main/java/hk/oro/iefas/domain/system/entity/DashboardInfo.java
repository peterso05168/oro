package hk.oro.iefas.domain.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the DASHBOARD_INFO database table.
 * 
 */
@Entity
@Table(name = "DASHBOARD_INFO")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DashboardInfo extends UpdateTxnKeyable {

    @Id
    @Column(name = "DASHBOARD_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DASHBOARD_SEQ")
    @TableGenerator(name = "DASHBOARD_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DASHBOARD_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer dashboardId;

    @Column(name = "DASHBOARD_COUNT", length = 100)
    private String dashboardCount;

    @Column(name = "POST_ID", precision = 15)
    private Integer postId;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "USER_TYPE", length = 100)
    private String userType;

    @Column(name = "UPDATED")
    private Boolean updated;

    // bi-directional many-to-one association to DashboardType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DASHBOARD_TYPE_ID")
    private DashboardType dashboardType;

}