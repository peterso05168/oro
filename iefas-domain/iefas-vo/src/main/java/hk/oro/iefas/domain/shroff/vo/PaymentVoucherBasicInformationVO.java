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
 * @version $Revision: 2967 $ $Date: 2018-06-06 22:24:51 +0800 (週三, 06 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVoucherBasicInformationVO extends TxnVO {

    private Integer voucherId;
    private VoucherTypeVO voucherType;
    private String fileRef;
    private String groupCode;
    private String voucherNo;
    private Date voucherDate;
    private Date submissionDate;
    private String paymentMethod;
    private ControlAccountVO controlAccount;
    private BigDecimal voucherTotalAmount;
    private Integer preparerId;
    private Integer firstApproverId;
    private Integer secondApproverId;
    private Integer paymentVerifierId;
    private String paymentApproverName;
    private Integer workflowId;
    private Date cancelDate;
    private String remark;
    private Boolean isHardCopy;
    private Integer currencyId;
    private String payeeName;
    private String status;
    private SysWorkFlowRuleVO sysWorkFlowRule;

}
