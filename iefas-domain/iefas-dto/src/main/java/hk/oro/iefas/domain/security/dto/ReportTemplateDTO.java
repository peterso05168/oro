package hk.oro.iefas.domain.security.dto;

import javax.validation.constraints.Size;

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
@NoArgsConstructor
@AllArgsConstructor
public class ReportTemplateDTO {

    private Integer reportTemplateId;

    private String reportId;

    @Size(max = 100)
    private String reportName;

    @Size(max = 200)
    private String reportDesc;

    @Size(max = 3)
    private String reportType;

    @Size(max = 300)
    private String reportRepositoryPath;

}
