package hk.oro.iefas.domain.report.entity;

import java.util.Date;

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
@Table(name = "RPT_RPT_REQ")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest {

    @Id
    @Column(name = "RPT_REQ_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RPT_REQ_SEQ")
    @TableGenerator(name = "RPT_REQ_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "RPT_REQ_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Long reportRequestId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "EXEC_DATE")
    private Date execDate;

    @Column(name = "STATUS")
    private String status;

}
