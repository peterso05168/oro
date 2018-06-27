package hk.oro.iefas.domain.shroff.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import hk.oro.iefas.domain.system.dto.SysWorkFlowRuleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @version $Revision: 3220 $ $Date: 2018-06-20 14:10:52 +0800 (週三, 20 六月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptVoucherBasicInfoDTO extends TxnDTO {
    private Integer voucherId;
    private VoucherTypeDTO voucherType;
    private String groupCode;
    private String voucherNo;
    private String receiveMethod;
    private Date voucherDate;
    private Date submissionDate;
    private BigDecimal voucherTotalAmount;
    private Integer preparerId;
    private Integer firstApproverId;
    private Integer paymentVerifierId;
    private String paymentApproverName;
    private Integer workflowId;
    private String remark;
    private Boolean isHardCopy;
    private Boolean isChargeRealizationFee;
    private Integer currencyId;
    private ControlAccountDTO controlAccount;
    private String status;
    private SysWorkFlowRuleDTO sysWorkFlowRule;
}
