package hk.oro.iefas.domain.shroff.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.vo.TxnVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
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
public class ReceiptVoucherBasicInformationVO extends TxnVO {
    private Integer voucherId;
    private VoucherTypeVO voucherType;
    private String groupCode;
    private String receiveMethod;
    private String voucherNo;
    private Date voucherDate;
    private Date submissionDate;
    private BigDecimal voucherTotalAmount;
    private Integer preparerId;
    private Integer firstApproverId;
    private Integer paymentVerifierId;
    private String paymentApproverName;
    private SysWorkFlowRuleVO sysWorkFlowRule;
    private String remark;
    private Boolean isHardCopy;
    private Boolean isChargeRealizationFee;
    private String status;
    private Integer currencyId;
    private ControlAccountVO controlAccount;
}
