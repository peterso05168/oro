/**
 * 
 */
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
 * @version $Revision: 3002 $ $Date: 2018-06-08 11:44:09 +0800 (週五, 08 六月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class JournalVoucherBasicInformationVO extends TxnVO {

    private Integer voucherId;
    private VoucherTypeVO voucherType;
    private JournalTypeVO journalType;
    private String groupCode;
    private String voucherNo;
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
    private Integer currencyId;
    private String status;
    private SysWorkFlowRuleVO sysWorkFlowRule;
}
