package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3170 $ $Date: 2018-06-15 19:34:19 +0800 (週五, 15 六月 2018) $
 * @author $Author: noah.liang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DividendScheduleVO extends TxnVO {

    private Integer dividendScheduleId;
    private String pledgeType;
    private String scheduleType;
    private Date paymentEffectiveDate;
    private CalculationOfDividendFeeVO calculationOfDividendFee;
    private List<DividendScheduleItemVO> dividendScheduleItems = new ArrayList<>();
    private String submittedBy;
    private Date submittedDate;
    private String reviewedBy;
    private Date reviewedDate;
    private String approvedBy;
    private Date approvedDate;
    private String confirmedBy;
    private Date confirmedDate;
    private List<ApproveHistoryVO> approveHistory;
    private String status;
    private List<DividendScheduleCreditorVO> dividendScheduleCreditorList;
    private Integer caseId;
    private BigDecimal totalDistributedAmount;
    
    private SysApprovalWfVO sysApprovalWf;
    private Integer workFlowId;
}
