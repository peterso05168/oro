package hk.oro.iefas.domain.casemgt.vo;

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
public class SearchCaseDetailCriteriaVO {

    private Integer caseId;
    private String caseName;
    private Integer caseTypeId;
    private Integer outsiderTypeId;
    private String outsiderName;
    private String caseTypeCode;
    private String caseYear;
    private String caseNo;
    private String handlingOfficerPostName;
    private String status;
}