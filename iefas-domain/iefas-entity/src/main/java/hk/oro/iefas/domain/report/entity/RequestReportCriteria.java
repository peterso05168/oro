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
@Table(name = "RPT_REQ_RPT_CRI")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RequestReportCriteria {

    @Id
    @Column(name = "RPT_REQ_RPT_CRI_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RPT_REQ_RPT_CRI_SEQ")
    @TableGenerator(name = "RPT_REQ_RPT_CRI_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "RPT_REQ_RPT_CRI_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Long requestReportCriteriaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RPT_REQ_RPT_ID")
    private RequestReport requestReport;

    @Column(name = "CRI_NAME")
    private String criteriaName;

    @Column(name = "CRI_VALUE")
    private String criteriaValue;
}
