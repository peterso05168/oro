package hk.oro.iefas.domain.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SYS_ATTACHMENT")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Attachment extends UpdateTxnKeyable {

    @Id
    @Column(name = "ATTACHMENT_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SYS_ATTACHMENT_SEQ")
    @TableGenerator(name = "SYS_ATTACHMENT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ATTACHMENT_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long attachmentId;

    @Lob
    @Column(name = "CONTENT")
    private byte[] content;

    @Column(name = "STATUS", length = 3)
    private String status;
}
