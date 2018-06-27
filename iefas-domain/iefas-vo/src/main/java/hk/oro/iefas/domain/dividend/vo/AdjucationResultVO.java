package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;

import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3022 $ $Date: 2018-06-10 17:23:43 +0800 (週日, 10 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AdjucationResultVO extends TxnVO {
    private Integer adjucationId;
    private CreditorVO creditor;
    private String natureOfClaim;
    private CreditorTypeVO creditorType;
    private String addressType;

    private BigDecimal claimed;
    private BigDecimal preAdmitted;
    private BigDecimal ordAdmitted;
    private BigDecimal defPreAdmitted;
    private BigDecimal defOrdAdmitted;
    private BigDecimal rejected;
    private String groundNos;

    private PreferentialAmountVO preAmount;
    private PreferentialAmountVO ordAmount;
    private PreferentialAmountVO rejAmount;

}
