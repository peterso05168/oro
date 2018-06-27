package hk.oro.iefas.domain.shroff.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.bank.vo.CurrencyVO;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2745 $ $Date: 2018-05-30 20:19:44 +0800 (週三, 30 五月 2018) $
 * @author $Author: garrett.han $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveDepositVO extends TxnVO {

    private Integer depositId;

    private CaseVO caseInfo;

    private BigDecimal chequeNo;

    private String contactNo;

    private BigDecimal depositAmount;

    private String depositNo;

    private String payer;

    private CurrencyVO curcy;

    private Integer paymentTypeId;

    private Date receiveDate;

    private String status;

    private BigDecimal voucherId;

    private ReceiptVO shrReceipt;
}
