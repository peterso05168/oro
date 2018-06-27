package hk.oro.iefas.domain.ormis.entity;

import java.math.BigDecimal;
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
 * The persistent class for the ORM_FILE_TO_ORMIS_DETAIL database table.
 * 
 */
@Entity
@Table(name = "ORM_FILE_TO_ORMIS_DETAIL")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class OrmFileToOrmisDetail extends UpdateTxnKeyable {

    @Id
    @Column(name = "ORMIS_FILE_DETAIL_SEQ", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ORMIS_FILE_DETAIL_SEQ")
    @TableGenerator(name = "ORMIS_FILE_DETAIL_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ORMIS_FILE_DETAIL_SEQ", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long ormisFileDetailSeq;

    @Column(name = "ACCUMULATED_AMOUNT_POSTED", precision = 16, scale = 2)
    private BigDecimal accumulatedAmountPosted;

    @Column(name = "ACCUMULATED_AMOUNT_RECEIVED", precision = 16, scale = 2)
    private BigDecimal accumulatedAmountReceived;

    @Column(name = "ADMITTED_ORD_AMOUNT_HKD", precision = 16, scale = 2)
    private BigDecimal admittedOrdAmountHkd;

    @Column(name = "ADMITTED_PREF_AMOUNT_HKD", precision = 16, scale = 2)
    private BigDecimal admittedPrefAmountHkd;

    @Column(name = "ANALYSIS_CODE", length = 7)
    private String analysisCode;

    @Column(name = "ANALYSIS_CODE_DESC", length = 200)
    private String analysisCodeDesc;

    @Column(name = "ANALYSIS_CODE_TYPE", length = 1)
    private String analysisCodeType;

    @Temporal(TemporalType.DATE)
    @Column(name = "AS_AT_DATE")
    private Date asAtDate;

    @Column(name = "CASE_NO", length = 5)
    private Integer caseNo;

    @Column(name = "CASE_TYPE", length = 3)
    private String caseType;

    @Column(name = "CASE_YEAR", length = 4)
    private Integer caseYear;

    @Column(name = "CASH_IN_HAND", precision = 15)
    private BigDecimal cashInHand;

    @Temporal(TemporalType.DATE)
    @Column(name = "CONTRIBUTION_END_DATE")
    private Date contributionEndDate;

    @Column(name = "CONTRIBUTION_REMARK", length = 40)
    private String contributionRemark;

    @Temporal(TemporalType.DATE)
    @Column(name = "CONTRIBUTION_START_DATE")
    private Date contributionStartDate;

    @Column(name = "CREDITOR_ADDRESS_1", length = 40)
    private String creditorAddress1;

    @Column(name = "CREDITOR_ADDRESS_2", length = 40)
    private String creditorAddress2;

    @Column(name = "CREDITOR_ADDRESS_3", length = 40)
    private String creditorAddress3;

    @Column(name = "CREDITOR_ADDRESS_4", length = 40)
    private String creditorAddress4;

    @Column(name = "CREDITOR_NAME", length = 40)
    private String creditorName;

    @Column(name = "CRPDET_TBC_FIELD", length = 1)
    private String crpdetTbcField;

    @Column(name = "DEBT_AMOUNT_IN_CLAIMED_CURCY", precision = 16, scale = 2)
    private BigDecimal debtAmountInClaimedCurcy;

    @Column(name = "DEBT_CLAIMED_CURCY", length = 3)
    private String debtClaimedCurcy;

    @Column(name = "DEBT_NATURE_NAME", length = 1)
    private String debtNatureName;

    @Column(name = "DEFERRED_ORD_AMOUNT", precision = 16, scale = 2)
    private BigDecimal deferredOrdAmount;

    @Column(name = "DEFERRED_ORD_AMOUNT_HKD", precision = 16, scale = 2)
    private BigDecimal deferredOrdAmountHkd;

    @Column(name = "DEFERRED_ORD_PERCENT", precision = 5)
    private BigDecimal deferredOrdPercent;

    @Column(name = "DEFERRED_PRE_AMOUNT", precision = 16, scale = 2)
    private BigDecimal deferredPreAmount;

    @Column(name = "DEFERRED_PRE_AMOUNT_HKD", precision = 16, scale = 2)
    private BigDecimal deferredPreAmountHkd;

    @Column(name = "DEFERRED_PRE_PERCENT", precision = 5)
    private BigDecimal deferredPrePercent;

    @Column(name = "INVEST_AMOUNT", precision = 16, scale = 2)
    private BigDecimal investAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_CONTRIBUTION_DATE")
    private Date lastContributionDate;

    @Column(name = "LIQUID_CASH_AMOUNT", precision = 16, scale = 2)
    private BigDecimal liquidCashAmount;

    @Column(name = "MONTHLY_CONTRIBUTION_AMOUNT", precision = 16, scale = 2)
    private BigDecimal monthlyContributionAmount;

    @Column(name = "OFFICE_CODE", length = 4)
    private String officeCode;

    @Column(name = "ORD_AMOUNT", precision = 16, scale = 2)
    private BigDecimal ordAmount;

    @Column(name = "ORD_AMOUNT_PERCENT", precision = 16, scale = 2)
    private BigDecimal ordAmountPercent;

    @Column(name = "ORMIS_FILE_SEQ", precision = 15)
    private BigDecimal ormisFileSeq;

    @Column(name = "PAYMENT_AMOUNT", precision = 16, scale = 2)
    private BigDecimal paymentAmount;

    @Column(name = "PAYMENT_TYPE", precision = 15)
    private BigDecimal paymentType;

    @Column(name = "POSITION_AMOUNT", precision = 16, scale = 2)
    private BigDecimal positionAmount;

    @Column(name = "PREF_AMOUNT", precision = 16, scale = 2)
    private BigDecimal prefAmount;

    @Column(name = "PREF_PERCENTAGE", precision = 5)
    private BigDecimal prefPercentage;

    @Column(name = "PROOF_NO", length = 10)
    private String proofNo;

    @Column(name = "PROOF_NO_SUFFIX", length = 2)
    private String proofNoSuffix;

    @Temporal(TemporalType.DATE)
    @Column(name = "PROOF_RECEIVE_DATE")
    private Date proofReceiveDate;

    @Column(name = "REALIZATION_AMOUNT", precision = 16, scale = 2)
    private BigDecimal realizationAmount;

    @Column(name = "RECEIPT_AMOUNT", precision = 16, scale = 2)
    private BigDecimal receiptAmount;

    @Column(name = "REJECTED_AMOUNT_HKD", precision = 16, scale = 2)
    private BigDecimal rejectedAmountHkd;

    @Column(name = "RETURN_OF_CAP", precision = 15)
    private BigDecimal returnOfCap;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "STATUTORY_INT_AMOUNT", precision = 16, scale = 2)
    private BigDecimal statutoryIntAmount;

    @Column(name = "STATUTORY_INT_PERCENT", precision = 5)
    private BigDecimal statutoryIntPercent;

    @Column(name = "TEAM_CODE", length = 1)
    private String teamCode;

    @Column(name = "TOTAL_CLAIMED_AMOUNT_HKD", precision = 16, scale = 2)
    private BigDecimal totalClaimedAmountHkd;

}