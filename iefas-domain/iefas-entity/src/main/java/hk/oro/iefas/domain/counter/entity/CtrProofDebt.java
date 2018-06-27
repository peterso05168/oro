package hk.oro.iefas.domain.counter.entity;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the CTR_PROOF_DEBT database table.
 * 
 */
@Entity
@Table(name = "CTR_PROOF_DEBT")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CtrProofDebt extends UpdateTxnKeyable {

    @Id
    @Column(name = "PROOF_OF_DEBT_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PROOF_OF_DEBT_SEQ")
    @TableGenerator(name = "PROOF_OF_DEBT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "PROOF_OF_DEBT_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer proofOfDebtId;

    @Column(name = "COMMON_CREDITOR_SEQ_NO")
    private String commonCreditorSeqNo;

    @Column(name = "COMMON_CREDITOR_NAME", length = 100)
    private String commonCreditorName;

    @Column(name = "COMMON_CREDITOR_NAME_CHI", length = 300)
    private String commonCreditorNameChi;

    @Column(name = "CONTACT_NO", length = 20)
    private String contactNo;

    @Column(name = "CONTACT_PERSON", length = 100)
    private String contactPerson;

    @Column(name = "CREDITOR_ADDRESS_1", length = 100)
    private String creditorAddress1;

    @Column(name = "CREDITOR_ADDRESS_2", length = 100)
    private String creditorAddress2;

    @Column(name = "CREDITOR_ADDRESS_3", length = 100)
    private String creditorAddress3;

    @Column(name = "CREDITOR_ADDRESS_CHI_1", length = 300)
    private String creditorAddressChi1;

    @Column(name = "CREDITOR_ADDRESS_CHI_2", length = 300)
    private String creditorAddressChi2;

    @Column(name = "CREDITOR_ADDRESS_CHI_3", length = 300)
    private String creditorAddressChi3;

    @Column(name = "PROOF_NO", length = 10)
    private String proofNo;

    @Column(name = "RECEIPT_DATE")
    private Date receiptDate;

    @Column(name = "REF_NO", length = 100)
    private String refNo;

    @Column(name = "SECTION_ADDRESS_1", length = 100)
    private String sectionAddress1;

    @Column(name = "SECTION_ADDRESS_2", length = 100)
    private String sectionAddress2;

    @Column(name = "SECTION_ADDRESS_3", length = 100)
    private String sectionAddress3;

    @Column(name = "SECTION_ADDRESS_CHI_1", length = 300)
    private String sectionAddressChi1;

    @Column(name = "SECTION_ADDRESS_CHI_2", length = 300)
    private String sectionAddressChi2;

    @Column(name = "SECTION_ADDRESS_CHI_3", length = 300)
    private String sectionAddressChi3;

    @Column(name = "SECTION_CONTACT_NO_NO", length = 100)
    private String sectionContactNoNo;

    @Column(name = "SECTION_CONTACT_PERSON", length = 100)
    private String sectionContactPerson;

    @Column(name = "SECTION_SEQ_NO")
    private String sectionSeqNo;

    @Column(name = "SECTION_NAME", length = 100)
    private String sectionName;

    @Column(name = "SECTION_NAME_CHI", length = 300)
    private String sectionNameChi;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "UNCAP_INT", precision = 16, scale = 2)
    private BigDecimal uncapInt;

    // bi-directional many-to-one association to CaseInfo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ID")
    private Case caseInfo;

    // bi-directional many-to-one association to CtrProofDebtItem
    @OneToMany(mappedBy = "proofOfDebtItemId", fetch = FetchType.LAZY)
    private List<CtrProofDebtItem> proofDebtItems;

}