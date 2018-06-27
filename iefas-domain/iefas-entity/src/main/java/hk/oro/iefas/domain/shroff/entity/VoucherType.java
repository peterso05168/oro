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

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SHR_VCR_TYPE")
public class VoucherType {

    @Id
    @Column(name = "VOUCHER_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "VCR_TYPE_SEQ")
    @TableGenerator(name = "VCR_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "VOUCHER_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer voucherTypeId;

    @Column(name = "VOUCHER_TYPE_CODE")
    private String voucherTypeCode;

    @Column(name = "VOUCHER_TYPE_NAME")
    private String voucherTypeName;

    @Column(name = "VOUCHER_TYPE_DESC")
    private String voucherTypeDesc;
}
