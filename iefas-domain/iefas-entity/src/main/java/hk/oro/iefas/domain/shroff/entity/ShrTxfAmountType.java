package hk.oro.iefas.domain.shroff.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_TXF_AMOUNT_TYPE database table.
 * 
 */
@Entity
@Table(name = "SHR_TXF_AMOUNT_TYPE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrTxfAmountType {

    @Id
    @Column(name = "TXF_AMOUNT_TYPE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TXF_AMOUNT_TYPE_SEQ")
    @TableGenerator(name = "TXF_AMOUNT_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "TXF_AMOUNT_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer txfAmountTypeId;

    @Column(name = "TXF_AMOUNT_TYPE_DESC", length = 300)
    private String txfAmountTypeDesc;

    @Column(name = "TXF_AMOUNT_TYPE_NAME", length = 100)
    private String txfAmountTypeName;

}