package hk.oro.iefas.domain.casemgt.entity;

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

import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import hk.oro.iefas.domain.counter.entity.CtrProofDebt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the CASE_CRED database table.
 * 
 */
@Entity
@Table(name = "CASE_CRED")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CaseCred extends UpdateTxnKeyable {

    @Id
    @Column(name = "CREDITOR_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CREDITOR_SEQ")
    @TableGenerator(name = "CREDITOR_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CREDITOR_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer creditorId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Column(name = "COMMON_CRED_SECTION_ID", precision = 15)
    private Integer commonCredSectionId;

    @Column(name = "COMMON_CREDITOR_ID", precision = 15)
    private Integer commonCreditorId;

    @Column(name = "CREDITOR_CONTACT_NO", length = 20)
    private String creditorContactNo;

    @Column(name = "CREDITOR_NAME_CHI", length = 100)
    private String creditorNameChi;

    @Column(name = "CREDITOR_NAME_ENG", length = 100)
    private String creditorNameEng;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROOF_OF_DEBT_ID")
    private CtrProofDebt ctrProofDebt;

    @Column(name = "SECTION_ADDRESS_ID", precision = 15)
    private Integer sectionAddressId;

    @Column(name = "SECTION_CONTACT_NO", length = 20)
    private String sectionContactNo;

    @Column(name = "SECTION_CONTACT_PERSON", length = 100)
    private String sectionContactPerson;

    @Column(name = "SECTION_NAME", length = 100)
    private String sectionName;

    @Column(name = "SECTION_NAME_CHI", length = 300)
    private String sectionNameChi;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "PROOF_NO")
    private String proofNo;

    @Column(name = "CREDITOR_CONTACT_PERSON")
    private String creditorContactPerson;

    @Column(name = "PAYEE_NAME")
    private String payeeName;

    @Column(name = "PAYEE_NAME_CHI")
    private String payeeNameChinese;

    @Column(name = "SECTION_PAYEE_NAME")
    private String sectionPayeeName;

    @Column(name = "SECTION_PAYEE_NAME_CHI")
    private String sectionPayeeNameChi;

    // bi-directional many-to-one association to AdjResult
    @OneToMany(mappedBy = "adjResultId", fetch = FetchType.LAZY)
    private List<AdjResult> adjResults;

    // bi-directional many-to-one association to CaseInfo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDITOR_CASE_ID")
    private Case caseInfo;

}