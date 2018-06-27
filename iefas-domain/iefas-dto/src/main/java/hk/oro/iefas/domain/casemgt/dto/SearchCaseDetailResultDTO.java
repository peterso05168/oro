package hk.oro.iefas.domain.casemgt.dto;

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
public class SearchCaseDetailResultDTO {

    private Integer caseId;
    private String caseNo;
    private String caseName;
    private String caseTypeCode;
    private String caseYear;
    private String caseTypeDesc;
    private String caseNumber;
    private String outsiderTypeName;
    private String outsiderName;
    private String handlingOfficerPostTitle;
    private String status;
}
