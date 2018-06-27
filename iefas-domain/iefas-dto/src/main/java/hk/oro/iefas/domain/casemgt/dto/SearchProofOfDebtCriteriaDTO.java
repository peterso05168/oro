package hk.oro.iefas.domain.casemgt.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SearchProofOfDebtCriteriaDTO {

    private Integer proofOfDebtId;
    private String caseTypeCode;
    private String caseSeqNo;
    private String caseYear;
    private String caseNumber;
    private String proofNumber;
    private String caseName;
    private String commonCreditorSeqNo;
    private String sectionSeqNo;
    private Date dateOfReceipt;
    private String commonCreditorName;
    private String sectionName;
    private String status;
}
