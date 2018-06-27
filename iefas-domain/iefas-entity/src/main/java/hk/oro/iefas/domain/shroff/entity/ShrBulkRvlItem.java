package hk.oro.iefas.domain.shroff.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_BULK_RVL_ITEM database table.
 * 
 */
@Entity
@Table(name = "SHR_BULK_RVL_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrBulkRvlItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "BULK_REVERSAL_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BULK_REVERSAL_ITEM_SEQ")
    @TableGenerator(name = "BULK_REVERSAL_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "BULK_REVERSAL_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long bulkReversalItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BULK_REVERSAL_ID")
    private ShrBulkRvl shrBulkRvl;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to ShrCheque
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHEQUE_ID")
    private ShrCheque shrCheque;

}