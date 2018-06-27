package hk.oro.iefas.domain.casemgt.entity;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the CASE_CONTRIBUTION database table.
 * 
 */
@Entity
@Table(name = "CASE_CONTRIBUTION")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CaseContribution extends UpdateTxnKeyable {

    @Id
    @Column(name = "CONTRIBUTION_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CONTRIBUTION_SEQ")
    @TableGenerator(name = "CONTRIBUTION_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CONTRIBUTION_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long contributionId;

    @Column(length = 100)
    private String contributor;

    @Temporal(TemporalType.DATE)
    @Column(name = "EFF_END_DATE")
    private Date effEndDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "EFF_START_DATE")
    private Date effStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_CONTRIBUTION_DATE")
    private Date lastContributionDate;

    @Column(name = "PERIODIC_AMOUNT", precision = 16, scale = 2)
    private BigDecimal periodicAmount;

    @Column(name = "REF_NO", length = 100)
    private String refNo;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to CaseInfo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ID")
    private Case caseInfo;

}