package hk.oro.iefas.domain.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RPT_REQ_RPT")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RequestReport {

    @Id
    @Column(name = "RPT_REQ_RPT_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RPT_REQ_RPT_SEQ")
    @TableGenerator(name = "RPT_REQ_RPT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "RPT_REQ_RPT_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Long requestReportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RPT_REQ_ID")
    private ReportRequest reportRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORT_INFO_ID")
    private ReportInfo reportInfo;

    @Column(name = "RPT_FORMAT")
    private String rptFormat;

    @Column(name = "MERGE")
    private String merge;
}
