package hk.oro.iefas.domain.dividend.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3194 $ $Date: 2018-06-19 14:18:11 +0800 (週二, 19 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Entity
@Table(name = "DIV_PROVISIONS")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivProvision extends UpdateTxnKeyable {

    @Id
    @Column(name = "PROVSN_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PROVSN_SEQ")
    @TableGenerator(name = "PROVSN_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "PROVSN_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer provsnId;

    @Column(name = "COS_REL_COURT_FEE", precision = 16, scale = 2)
    private BigDecimal cosRelCourtFee;

    @Column(name = "COS_REL_GAZ_CHARGE", precision = 16, scale = 2)
    private BigDecimal cosRelGazCharge;

    @Column(name = "COS_REL_OR_FEE", precision = 16, scale = 2)
    private BigDecimal cosRelOrFee;

    @Column(name = "DIS_GAZ_ADV_CHARGE", precision = 16, scale = 2)
    private BigDecimal disGazAdvCharge;

    @Column(name = "DIS_GAZ_OR_FEE", precision = 16, scale = 2)
    private BigDecimal disGazOrFee;

    @Column(name = "INT_DIS_PUB_EXA_GAZ_CHARGE", precision = 16, scale = 2)
    private BigDecimal intDisPubExaGazCharge;

    @Column(name = "INT_DIS_PUB_EXA_OR_FEE", precision = 16, scale = 2)
    private BigDecimal intDisPubExaOrFee;

    @Column(name = "NOTICS_DIV_GAZ_CHARGE", precision = 16, scale = 2)
    private BigDecimal noticsDivGazCharge;

    @Column(name = "NOTICS_FULL_PAY_ADV_CHARGE", precision = 16, scale = 2)
    private BigDecimal noticsFullPayAdvCharge;

    @Column(name = "NOTICS_FULL_PAY_GAZ_CHARGE", precision = 16, scale = 2)
    private BigDecimal noticsFullPayGazCharge;

    @Column(name = "NOTICS_PRE_PAY_ADV_CHARGE", precision = 16, scale = 2)
    private BigDecimal noticsPrePayAdvCharge;

    @Column(name = "NOTICS_PRE_PAY_GAZ_CHARGE", precision = 16, scale = 2)
    private BigDecimal noticsPrePayGazCharge;

    @Column(name = "NOTICS_RET_CAP_GAZ_CHARGE", precision = 16, scale = 2)
    private BigDecimal noticsRetCapGazCharge;

    @Column(name = "OTHERS_DIS_MON_REF_LAND", precision = 16, scale = 2)
    private BigDecimal othersDisMonRefLand;

    @Column(name = "OTHERS_OTHERS", precision = 16, scale = 2)
    private BigDecimal othersOthers;

    @Column(name = "OTHERS_PET_TAX_COST_DEP", precision = 16, scale = 2)
    private BigDecimal othersPetTaxCostDep;

    @Column(name = "OTHERS_STO_SEI_DOC", precision = 16, scale = 2)
    private BigDecimal othersStoSeiDoc;

    @Column(name = "REC_ANN_GAZ_ADV_CHARGE", precision = 16, scale = 2)
    private BigDecimal recAnnGazAdvCharge;

    @Column(name = "REC_ANN_GAZ_OR_FEE", precision = 16, scale = 2)
    private BigDecimal recAnnGazOrFee;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "DIV_CAL_ID")
    private Integer divCalId;

    @Column(name = "OLD_BALANCE", precision = 16, scale = 2)
    private BigDecimal oldBalance;

}