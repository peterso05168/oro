package hk.oro.iefas.domain.casemgt.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "CASE_COM_CRED")
public class CommonCreditor extends UpdateTxnKeyable {
    @Id
    @Column(name = "COMMON_CREDITOR_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMON_CREDITOR_SEQ")
    @TableGenerator(name = "COMMON_CREDITOR_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "COMMON_CREDITOR_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer commonCreditorId;

    @Column(name = "COMMON_CREDITOR_NAME")
    private String commonCreditorName;

    @Column(name = "COMMON_CREDITOR_NAME_CHI")
    private String commonCreditorNameChinese;

    @Column(name = "COMMON_CREDITOR_SEQ_NO")
    private String commonCreditorSeqNo;

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

    @Column(name = "REFERENCE")
    private String reference;

    @OneToMany(mappedBy = "commonCreditorId", fetch = FetchType.LAZY)
    private List<CommonCreditorSection> commonCreditorSections;

    @Column(name = "STATUS")
    private String status;
}
