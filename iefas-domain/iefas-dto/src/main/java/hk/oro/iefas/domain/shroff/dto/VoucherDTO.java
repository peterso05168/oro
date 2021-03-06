package hk.oro.iefas.domain.shroff.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2246 $ $Date: 2018-04-27 10:18:29 +0800 (週五, 27 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO extends TxnDTO {

    private Integer voucherId;
    private VoucherTypeDTO voucherType;
    private JournalTypeDTO journalType;
    private String fileRef;
    private String groupCode;
    private String voucherNo;
    private Date voucherDate;
    private Date submissionDate;
    private PaymentTypeDTO paymentType;
    private BigDecimal voucherTotalAmount;
    private Integer preparerId;
    private Integer firstApproverId;
    private Integer secondApproverId;
    private Integer paymentVerifierId;
    private String paymentApproverName;
    private Integer workflowId;
    private Date cancelDate;
    private String remark;
    private Integer isHardCopy;
    private Date bringUpDate;
    private Integer currencyId;
    private String payeeName;
    private String realizationFee;
    private String status;
}
