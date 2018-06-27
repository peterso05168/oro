package hk.oro.iefas.domain.shroff.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import hk.oro.iefas.domain.system.entity.SysAttachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_VCR_ATTACHMENT database table.
 * 
 */
@Entity
@Table(name = "SHR_VCR_ATTACHMENT")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrVcrAttachment extends UpdateTxnKeyable {

    @Id
    @Column(name = "VOUCHER_ATTACHMENT_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "VOUCHER_ATTACHMENT_SEQ")
    @TableGenerator(name = "VOUCHER_ATTACHMENT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "VOUCHER_ATTACHMENT_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer voucherAttachmentId;

    @Column(name = "REMARK", length = 300)
    private String remark;

    @Column(name = "FILE_NAME", length = 100)
    private String fileName;

    @Column(name = "STATUS", length = 3)
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTACHMENT_ID")
    private SysAttachment attachment;

    // bi-directional many-to-one association to ShrVcrInfo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOUCHER_ID")
    private Voucher voucher;

}