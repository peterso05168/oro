package hk.oro.iefas.domain.casemgt.entity;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import hk.oro.iefas.domain.counter.entity.CtrProofDebt;
import hk.oro.iefas.domain.dividend.entity.CaseFee;
import hk.oro.iefas.domain.dividend.entity.DivSchedule;
import hk.oro.iefas.domain.dividend.entity.DividendCal;
import hk.oro.iefas.domain.organization.entity.Post;
import hk.oro.iefas.domain.shroff.entity.ShrCheque;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CASE_INFO")
public class Case extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_SEQ")
    @TableGenerator(name = "CASE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "CASE_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer caseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_TYPE_ID")
    private CaseType caseType;

    @Column(name = "CASE_NAME", length = 100)
    private String caseName;

    @Column(name = "CASE_NO", length = 5)
    private Integer caseNo;

    @Column(name = "CASE_YEAR", length = 4)
    private Integer caseYear;

    @Column(name = "BRING_UP_DATE")
    private Date bringUpDate;

    @Column(name = "OPENING_DATE")
    private Date openingDate;

    @Column(name = "CLOSING_DATE")
    private Date closingDate;

    @Column(name = "ESTATE_BALANCE", length = 16, scale = 2)
    private BigDecimal estateBalance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OUTSIDER_ID")
    private Outsider outsider;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_FEE_ID")
    private CaseFee caseFee;

    @Column(name = "NO_OF_CREDITOR")
    private Integer noOfCreditor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OR_FEE_VOUCHER_ID")
    private Voucher orFeeVoucher;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REMIT_VOUCHER_ID")
    private Voucher remitVoucher;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "WHOLE_CASE_NO")
    private String wholeCaseNo;

    // bi-directional many-to-one association to CaseContribution
    @OneToMany(mappedBy = "caseInfo", fetch = FetchType.LAZY)
    private List<CaseContribution> caseContributions;

    // bi-directional many-to-one association to CaseCred
    @OneToMany(mappedBy = "caseInfo", fetch = FetchType.LAZY)
    private List<CaseCred> caseCreds;

    // bi-directional many-to-one association to CaseDepositCard
    @OneToMany(mappedBy = "caseInfo", fetch = FetchType.LAZY)
    private List<CaseDepositCard> caseDepositCards;

    // bi-directional many-to-one association to CtrProofDebt
    @OneToMany(mappedBy = "caseInfo", fetch = FetchType.LAZY)
    private List<CtrProofDebt> ctrProofDebts;

    // bi-directional many-to-one association to DivDividendCal
    @OneToMany(mappedBy = "caseInfo", fetch = FetchType.LAZY)
    private List<DividendCal> divDividendCals;

    // bi-directional many-to-one association to DivSchedule
    @OneToMany(mappedBy = "caseInfo", fetch = FetchType.LAZY)
    private List<DivSchedule> divSchedules;

    // bi-directional many-to-one association to ShrCheque
    @OneToMany(mappedBy = "caseInfo", fetch = FetchType.LAZY)
    private List<ShrCheque> shrCheques;
}
