package hk.oro.iefas.domain.casemgt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CASE_COM_CRED_SECT")
public class CommonCreditorSection extends UpdateTxnKeyable {

    @Id
    @Column(name = "COMMON_CRED_SECTION_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_COM_CRED_SECT_SEQ")
    @TableGenerator(name = "CASE_COM_CRED_SECT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CASE_COM_CRED_SECT_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer commonCreditorSectionId;

    @Column(name = "COMMON_CREDITOR_ID")
    private Integer commonCreditorId;

    @Column(name = "SECTION_NAME")
    private String sectionName;

    @Column(name = "SECTION_SEQ_NO")
    private String sectionSeqNo;

    @Column(name = "SECTION_NAME_CHI")
    private String sectionNameChinese;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Column(name = "CONTACT_NO")
    private String contactNo;

    @Column(name = "CONTACT_PERSON")
    private String contactPerson;

    @Column(name = "PAYEE_NAME")
    private String payeeName;

    @Column(name = "PAYEE_NAME_CHI")
    private String payeeNameChinese;

    @Column(name = "FAX")
    private String sectionFax;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "AC_NO")
    private String sectionAccountNumber;

    @Column(name = "REFERENCE")
    private String reference;

    @Column(name = "STATUS")
    private String status;
}
