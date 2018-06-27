package hk.oro.iefas.domain.casemgt.entity;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the CASE_DEBITOR database table.
 * 
 */
@Entity
@Table(name = "CASE_DEBITOR")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CaseDebitor extends UpdateTxnKeyable {

    @Id
    @Column(name = "DEBTOR_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DEBTOR_SEQ")
    @TableGenerator(name = "DEBTOR_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "DEBTOR_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long debtorId;

    @Column(name = "ADDRESS_ID", precision = 15)
    private BigDecimal addressId;

    @Column(name = "DEBTOR_CONTACT_NO", length = 100)
    private String debtorContactNo;

    @Column(name = "DEBTOR_NAME", length = 100)
    private String debtorName;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to CaseInfo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEBTOR_CASE_ID")
    private Case caseInfo;

}