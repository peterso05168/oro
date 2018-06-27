package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import hk.oro.iefas.domain.casemgt.vo.CaseAccountInfoVO;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.core.vo.TxnVO;
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
public class DividendCalculationVO extends TxnVO {
    private Integer dividendCalculationId;
    private CaseVO vcase;
    private CaseAccountInfoVO mainCaseAccount;
    private String paymentType;
    private List<DividendCalculationCreditorVO> dividendCalculationCreditors;
    private String creditorTypeStr;
    private Date paymentDate;
    private String analysisCodes;
    private String chargedAmounts;
    private String submittedBy;
    private Date submittedDate;
    private String approvedBy;
    private Date approvedDate;
    private CalculationOfDividendFeeVO calculationOfDividendFee;
    private ProvisionsVO provisions;
    private List<CalculatedCreditorDividendDistributionVO> calculatedCreditorDividendDistributions;
    private List<ApproveHistoryVO> approveHistories;
    
    private BigDecimal caseInHand;
    
    private Integer workFlowId;
    private SysApprovalWfVO sysApprovalWf;
    private String status;
    

    private Map<Integer, Map<String, ApprovedAdjucationResultItemVO>> distributions;
}
