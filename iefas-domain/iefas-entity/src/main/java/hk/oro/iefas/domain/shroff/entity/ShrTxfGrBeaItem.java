package hk.oro.iefas.domain.shroff.entity;

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

/**
 * The persistent class for the SHR_TXF_GR_BEA_ITEM database table.
 * 
 */
@Entity
@Table(name = "SHR_TXF_GR_BEA_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrTxfGrBeaItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "TRANSFER_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TRANSFER_ITEM_SEQ")
    @TableGenerator(name = "TRANSFER_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "TRANSFER_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer transferItemId;

    @Column(name = "AC_ID", precision = 15)
    private Integer acId;

    @Column(name = "AC_TYPE_ID", precision = 15)
    private Integer acTypeId;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "TRAN_AMOUNT", precision = 16, scale = 2)
    private BigDecimal tranAmount;

    @Column(name = "TRANSFER_ID")
    private Integer transferId;

}