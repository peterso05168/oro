package hk.oro.iefas.domain.shroff.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.bank.dto.CurrencyDTO;
import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3128 $ $Date: 2018-06-13 18:12:32 +0800 (週三, 13 六月 2018) $
 * @author $Author: garrett.han $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ChequeDTO extends TxnDTO {
    private Integer chequeId;
    private Integer parentChequeId;
    private BigDecimal chequeAmount;
    private Date chequeDate;
    private Integer chequeNo;
    private Integer chequeTypeId;
    private String bankCode;
    private CurrencyDTO curcy;
    private String debtor;
    private Integer divChequeId;
    private Integer fileItemNo;
    private String payee;
    private Date paymentDate;
    private Integer paymentFileId;
    private Date receiptDate;
    private Date reversalDate;
    private String status;
    private String remark;
    private CaseDTO caseInfo;
    private Integer groupaApproverId;
    private Integer groupbApproverId;
    // private List<ShrBulkRvlItem> shrBulkRvlItems;
    private VoucherDTO shrVcrInfo;
    // private List<ShrVcrItem> shrVcrItems;
}
