package hk.oro.iefas.domain.system.entity;

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
@Table(name = "SYS_REJECT_REASON")
public class SysRejectReason extends UpdateTxnKeyable {
    @Id
    @Column(name = "REJECT_REASON_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "REJECT_REASON")
    @TableGenerator(name = "REJECT_REASON", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "REJECT_REASON_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer rejectReasonId;

    @Column(name = "MODULE_CODE")
    private String moduleCode;

    @Column(name = "REJECT_REASON_DESC")
    private String rejectReasonDesc;

    @Column(name = "STATUS")
    private String status;
}
