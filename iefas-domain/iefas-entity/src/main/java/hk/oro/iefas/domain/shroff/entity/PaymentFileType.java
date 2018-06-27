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
@Table(name = "SHR_PAYMENT_FILE_TYPE")
public class PaymentFileType extends UpdateTxnKeyable {

    @Id
    @Column(name = "PAYMENT_FILE_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PAYMENT_FILE_TYPE_SEQ")
    @TableGenerator(name = "PAYMENT_FILE_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "PAYMENT_FILE_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer paymentFileTypeId;

    @Column(name = "PAYMENT_FILE_TYPE_NAME")
    private String paymentFileTypeName;

    @Column(name = "PAYMENT_FILE_TYPE_DESC")
    private String paymentFileTypeDesc;

    @Column(name = "STATUS")
    private String status;
}
