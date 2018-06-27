package hk.oro.iefas.domain.dividend.dto;

import java.math.BigDecimal;

import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.core.dto.TxnDTO;
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
public class AdjucationResultDTO extends TxnDTO {
    private Integer adjucationId;
    private CreditorDTO creditor;
    private String natureOfClaim;
    private CreditorTypeDTO creditorType;
    private String addressType;

    private BigDecimal claimed;
    private BigDecimal preAdmitted;
    private BigDecimal ordAdmitted;
    private BigDecimal defPreAdmitted;
    private BigDecimal defOrdAdmitted;
    private BigDecimal rejected;
    private String groundNos;

    private PreferentialAmountDTO preAmount;
    private PreferentialAmountDTO ordAmount;
    private PreferentialAmountDTO rejAmount;

}
