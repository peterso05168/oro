package hk.oro.iefas.domain.release.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import hk.oro.iefas.domain.system.entity.SysAttachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the REL_INST_LIST database table.
 * 
 */
@Entity
@Table(name = "REL_INST_LIST")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RelInstList extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_LIST_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_LIST_SEQ")
    @TableGenerator(name = "CASE_LIST_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CASE_LIST_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long caseListId;

    @Temporal(TemporalType.DATE)
    @Column(name = "IMPORT_DATE")
    private Date importDate;

    @Column(name = "LIST_NAME", length = 100)
    private String listName;
    
    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to SysAttachment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTACHMENT_ID")
    private SysAttachment sysAttachment;

    // bi-directional many-to-one association to RelInstListItem
    @OneToMany(mappedBy = "caseListItemId", fetch = FetchType.LAZY)
    private List<RelInstListItem> relInstListItems;

}