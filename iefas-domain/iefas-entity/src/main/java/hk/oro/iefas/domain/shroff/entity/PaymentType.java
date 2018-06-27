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

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SHR_PAYMENT_TYPE")
public class PaymentType extends UpdateTxnKeyable {

    @Id
    @Column(name = "PAYMENT_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PAYMENT_TYPE_SEQ")
    @TableGenerator(name = "PAYMENT_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "PAYMENT_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer paymentTypeId;

    @Column(name = "PAYMENT_TYPE_NAME")
    private String paymentTypeName;

    @Column(name = "PAYMENT_TYPE_DESC")
    private String paymentTypeDesc;

    @Column(name = "STATUS")
    private String status;
}
