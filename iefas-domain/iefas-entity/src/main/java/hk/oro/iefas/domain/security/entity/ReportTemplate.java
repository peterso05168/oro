package hk.oro.iefas.domain.security.entity;

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

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REPORT_TEMPLATE")
public class ReportTemplate {

    @Id
    @Column(name = "REPORT_TEMPLATE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "REPORT_TEMPLATE_SEQ")
    @TableGenerator(name = "REPORT_TEMPLATE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "REPORT_TEMPLATE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer reportTemplateId;

    @Column(name = "REPORT_ID", length = 10)
    private String reportId;

    @Column(name = "REPORT_NAME", length = 100)
    private String reportName;

    @Column(name = "REPORT_DESC", length = 200)
    private String reportDesc;

    @Column(name = "REPORT_TYPE", length = 3)
    private String reportType;

    @Column(name = "REPORT_REPOSITORY_PATH", length = 300)
    private String reportRepositoryPath;
}
