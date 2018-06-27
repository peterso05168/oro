package hk.oro.iefas.domain.dividend.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import hk.oro.iefas.domain.casemgt.dto.CaseAccountInfoDTO;
import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3124 $ $Date: 2018-06-13 17:47:21 +0800 (週三, 13 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DividendCalculationDTO extends TxnDTO {
    private Integer dividendCalculationId;
    private CaseDTO vcase;
    private CaseAccountInfoDTO mainCaseAccount;
    private String paymentType;
    private List<DividendCalculationCreditorDTO> dividendCalculationCreditors;
    private String creditorTypeStr;
    private Date paymentDate;
    private String analysisCodes;
    private String chargedAmounts;
    private String submittedBy;
    private Date submittedDate;
    private String approvedBy;
    private Date approvedDate;
    // tab : details
    private CalculationOfDividendFeeDTO calculationOfDividendFee;
    // tab : Provision
    private ProvisionsDTO provisions;
    // tab : distributions
    private List<CalculatedCreditorDividendDistributionDTO> calculatedCreditorDividendDistributions;

    private BigDecimal caseInHand;

    // tab : histories
    private List<ApproveHistoryDTO> approveHistories;
    private Integer workFlowId;
    private SysApprovalWfDTO sysApprovalWf;
    private String status;
    
    private Map<Integer, Map<String, ApprovedAdjucationResultItemDTO>> distributions;
}
