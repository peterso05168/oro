package hk.oro.iefas.domain.system.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import hk.oro.iefas.domain.security.entity.Privilege;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the DASHBOARD_TYPE database table.
 * 
 */
@Entity
@Table(name = "DASHBOARD_TYPE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DashboardType extends UpdateTxnKeyable {

    @Id
    @Column(name = "DASHBOARD_TYPE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DASHBOARD_TYPE_SEQ")
    @TableGenerator(name = "DASHBOARD_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DASHBOARD_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer dashboardTypeId;

    @Column(name = "DASHBOARD_TYPE_DESC", length = 200)
    private String dashboardTypeDesc;

    @Column(name = "FORWARD_TO_URL", length = 300)
    private String forwardToUrl;

    @Column(name = "FUNCTION_ACTION", precision = 15)
    private Integer functionAction;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to DashboardInfo
    @OneToMany(mappedBy = "dashboardId", fetch = FetchType.LAZY)
    private List<DashboardInfo> dashboardInfos;

    // bi-directional many-to-one association to Privilege
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRIVILEGE_ID")
    private Privilege privilege;

}