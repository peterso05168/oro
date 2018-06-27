package hk.oro.iefas.domain.system.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2508 $ $Date: 2018-05-21 15:18:07 +0800 (週一, 21 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SYS_EMAIL_TEMPLATE")
public class EmailTemplate {

    @Id
    @Column(name = "EMAIL_TEMPLATE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EMAIL_TEMPLATE_SEQ")
    @TableGenerator(name = "EMAIL_TEMPLATE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "EMAIL_TEMPLATE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer emailTemplateId;

    @Column(name = "MODULE_CODE", length = 100)
    private String moduleCode;

    @Column(name = "SUB_MODULE_CODE", length = 100)
    private String subModuleCode;

    @Column(name = "EMAIL_SUBJECT", length = 100)
    private String emailSubject;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "EMAIL_CONTENT")
    private String emailContent;

}
