package hk.oro.iefas.domain.report.entity;

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
 * The persistent class for the REPORT_BATCH_JOB database table.
 * 
 */
@Entity
@Table(name = "REPORT_BATCH_JOB")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ReportBatchJob extends UpdateTxnKeyable {

    @Id
    @Column(name = "REPORT_BATCH_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "REPORT_BATCH_SEQ")
    @TableGenerator(name = "REPORT_BATCH_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "REPORT_BATCH_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long reportBatchId;

    @Column(name = "REPORT_BATCH_STATUS")
    private String reportBatchStatus;

    @Column(name = "STATUS", length = 3)
    private String status;

}