package hk.oro.iefas.domain.report.entity;

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

@Entity
@Table(name = "RPT_RPT_INFO")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ReportInfo {

    @Id
    @Column(name = "REPORT_INFO_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "REPORT_INFO_SEQ")
    @TableGenerator(name = "REPORT_INFO_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "REPORT_INFO_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Long reportInfoId;

    @Column(name = "REPORT_NAME")
    private String reportName;

    @Column(name = "REPORT_PAGE_FMT")
    private String reportPageFormat;

    @Column(name = "REPORT_CONT_URI")
    private String reportContentUri;

    @Column(name = "REPORT_ID")
    private String reportId;
}
