package hk.oro.iefas.domain.casemgt.dto;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import hk.oro.iefas.domain.counter.dto.ProofOfDebtDTO;
import hk.oro.iefas.domain.dividend.dto.CreditorTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3112 $ $Date: 2018-06-13 15:48:09 +0800 (週三, 13 六月 2018) $
 * @author $Author: noah.liang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CreditorDTO extends TxnDTO {

    private Integer creditorId;
    private String creditorNameEng;
    private String creditorContactNo;
    private CaseDTO caseInfo;
    private AddressDTO address;
    private Integer commonCredSectionId;
    private String creditorNameChi;
    private Integer proofOfDebtId;
    private Integer commonCreditorId;
    private String sectionName;
    private String sectionNameChi;
    private Integer sectionAddressId;
    private String sectionContactNo;
    private String sectionContactPerson;
    private String proofNo;
    private String lastProofNo;
    private String payeeName;
    private String payeeNameChinese;
    private String status;
    private ProofOfDebtDTO proofOfDebt;
    // --------------------Adjudication Result param ----------
    private String natureOfClaim;
    private CreditorTypeDTO creditorType;
}
