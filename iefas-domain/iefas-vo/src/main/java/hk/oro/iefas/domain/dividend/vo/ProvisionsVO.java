package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3194 $ $Date: 2018-06-19 14:18:11 +0800 (週二, 19 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProvisionsVO extends TxnVO {
    private Integer provsnId;
    private BigDecimal cosRelCourtFee;
    private BigDecimal cosRelGazCharge;
    private BigDecimal cosRelOrFee;
    private BigDecimal disGazAdvCharge;
    private BigDecimal disGazOrFee;
    private BigDecimal intDisPubExaGazCharge;
    private BigDecimal intDisPubExaOrFee;
    private BigDecimal noticsDivGazCharge;
    private BigDecimal noticsFullPayAdvCharge;
    private BigDecimal noticsFullPayGazCharge;
    private BigDecimal noticsPrePayAdvCharge;
    private BigDecimal noticsPrePayGazCharge;
    private BigDecimal noticsRetCapGazCharge;
    private BigDecimal othersDisMonRefLand;
    private BigDecimal othersOthers;
    private BigDecimal othersPetTaxCostDep;
    private BigDecimal othersStoSeiDoc;
    private BigDecimal recAnnGazAdvCharge;
    private BigDecimal recAnnGazOrFee;
    private BigDecimal oldBalance;
    private String status;
}
