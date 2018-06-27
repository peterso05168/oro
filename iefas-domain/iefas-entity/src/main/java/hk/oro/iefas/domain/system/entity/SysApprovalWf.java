package hk.oro.iefas.domain.system.entity;

import java.math.BigDecimal;

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
import hk.oro.iefas.domain.security.entity.Privilege;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SYS_APPROVAL_WF database table.
 * 
 */
@Entity
@Table(name = "SYS_APPROVAL_WF")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SysApprovalWf extends UpdateTxnKeyable {

    @Id
    @Column(name = "APPROVAL_WORKFLOW_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "APPROVAL_WORKFLOW_SEQ")
    @TableGenerator(name = "APPROVAL_WORKFLOW_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "APPROVAL_WORKFLOW_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long approvalWorkflowId;

    @Column(name = "WORKFLOW_ID", precision = 15)
    private Integer workflowId;

    @Column(name = "SEQ", precision = 15)
    private BigDecimal seq;

    // bi-directional many-to-one association to Privilege
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRIVILEGE_ID")
    private Privilege privilege;

    @Column(name = "ACTION", length = 100)
    private String action;

    @Column(name = "ACTION_BY", precision = 15)
    private Integer actionBy;

    @Column(name = "REMARKS", length = 300)
    private String remark;

    @Column(name = "IS_LATEST", precision = 1)
    private BigDecimal isLatest;

    @Column(name = "ACTION_BY_DIVISION", precision = 15)
    private Integer actionByDivision;

    @Column(name = "ACTION_BY_PERSON", length = 300)
    private String actionByPerson;

    @Column(name = "STATUS", length = 3)
    private String status;

}