package hk.oro.iefas.domain.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SYS_DL_TEMPLATE database table.
 * 
 */
@Entity
@Table(name = "SYS_DL_TEMPLATE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SysDlTemplate {

    @Id
    @Column(name = "DL_TEMPLATE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DL_TEMPLATE_SEQ")
    @TableGenerator(name = "DL_TEMPLATE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DL_TEMPLATE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long dlTemplateId;

    @Column(name = "MODULE_CODE", length = 3)
    private String moduleCode;

    @Column(name = "SUB_MODULE_CODE", length = 3)
    private String subModuleCode;

    // bi-directional many-to-one association to SysAttachment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTACHMENT_ID")
    private SysAttachment sysAttachment;

}