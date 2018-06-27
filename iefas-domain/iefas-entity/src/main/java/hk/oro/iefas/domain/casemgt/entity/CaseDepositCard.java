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
 * The persistent class for the CASE_DEPOSIT_CARD database table.
 * 
 */
@Entity
@Table(name = "CASE_DEPOSIT_CARD")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CaseDepositCard extends UpdateTxnKeyable {

    @Id
    @Column(name = "DEPOSIT_CARD_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DEPOSIT_CARD_SEQ")
    @TableGenerator(name = "DEPOSIT_CARD_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DEPOSIT_CARD_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer depositCardId;

    @Column(name = "DEPOSIT_CARD_NAME", length = 200)
    private String depositCardName;

    @Column(name = "DEPOSIT_CARD_NARRATIVE", length = 100)
    private String depositCardNarrative;

    @Temporal(TemporalType.DATE)
    @Column(name = "EFFECTIVE_END_DATE")
    private Date effectiveEndDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "EFFECTIVE_START_DATE")
    private Date effectiveStartDate;

    @Column(name = "MONTHLY_CONTRIBUTION_AMOUNT", precision = 16, scale = 2)
    private BigDecimal monthlyContributionAmount;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to CaseInfo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ID")
    private Case caseInfo;

}