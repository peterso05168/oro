package hk.oro.iefas.domain.adjudication.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ADJ_INT_TR_ACC_ITEM")
public class AdjIntTrialAccItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "INT_TRIAL_ACC_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "INT_TRIAL_ACC_ITEM_SEQ")
    @TableGenerator(name = "INT_TRIAL_ACC_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "INT_TRIAL_ACC_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer intTrialAccItemId;

    @Column(name = "INT_TR_ADJ_ID")
    private Integer adjIntTrAdjId;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "ADM_AMOUNT")
    private BigDecimal admAmount;

    @Column(name = "CONTRACTUAL_RATE")
    private BigDecimal contractualRate;

    @Column(name = "FINAL_RATE")
    private BigDecimal finalRate;

    @Column(name = "STATUS")
    private String status;

}
