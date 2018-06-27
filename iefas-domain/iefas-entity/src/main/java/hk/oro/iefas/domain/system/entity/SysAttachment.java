package hk.oro.iefas.domain.system.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import hk.oro.iefas.domain.release.entity.RelInstList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SYS_ATTACHMENT database table.
 * 
 */
@Entity
@Table(name = "SYS_ATTACHMENT")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SysAttachment extends UpdateTxnKeyable {

    @Id
    @Column(name = "ATTACHMENT_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ATTACHMENT_SEQ")
    @TableGenerator(name = "ATTACHMENT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ATTACHMENT_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer attachmentId;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to RelInstList
    @OneToMany(mappedBy = "caseListId", fetch = FetchType.LAZY)
    private List<RelInstList> relInstLists;

    // bi-directional many-to-one association to SysDlTemplate
    @OneToMany(mappedBy = "dlTemplateId", fetch = FetchType.LAZY)
    private List<SysDlTemplate> sysDlTemplates;

}