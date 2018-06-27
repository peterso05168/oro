package hk.oro.iefas.domain.counter.entity;

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
 * The persistent class for the CTR_PROOF_DEBT_ITEM database table.
 * 
 */
@Entity
@Table(name = "CTR_PROOF_DEBT_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CtrProofDebtItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "PROOF_OF_DEBT_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PROOF_OF_DEBT_ITEM_SEQ")
    @TableGenerator(name = "PROOF_OF_DEBT_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "PROOF_OF_DEBT_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer proofOfDebtItemId;

    @Column(name = "CURCY_ID", precision = 15)
    private Integer curcyId;

    @Column(name = "DEBT_AMOUNT", precision = 16, scale = 2)
    private BigDecimal debtAmount;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to CtrProofDebt
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROOF_OF_DEBT_ID")
    private CtrProofDebt proofDebt;

}