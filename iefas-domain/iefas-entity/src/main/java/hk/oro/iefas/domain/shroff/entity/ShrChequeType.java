package hk.oro.iefas.domain.shroff.entity;

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
 * The persistent class for the SHR_CHEQUE_TYPE database table.
 * 
 */
@Entity
@Table(name = "SHR_CHEQUE_TYPE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrChequeType extends UpdateTxnKeyable {

    @Id
    @Column(name = "CHEQUE_TYPE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CHEQUE_TYPE_SEQ")
    @TableGenerator(name = "CHEQUE_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CHEQUE_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long chequeTypeId;

    @Column(name = "CHEQUE_TYPE_DESC", length = 200)
    private String chequeTypeDesc;

    @Column(name = "STATUS", length = 3)
    private String status;

}