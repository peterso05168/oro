package hk.oro.iefas.domain.dividend.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import hk.oro.iefas.domain.shroff.entity.AnalysisCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Entity
@Table(name = "DIV_CHARGED_OR_FEE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ChargedOrFee extends UpdateTxnKeyable {

    @Id
    @Column(name = "CHARGED_OR_FEE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CHARGED_OR_FEE_SEQ")
    @TableGenerator(name = "CHARGED_OR_FEE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CHARGED_OR_FEE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer chargedOrFeeId;

    @Column(name = "DIV_CAL_ID", length = 100)
    private Integer divCalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANALYSIS_CODE_ID")
    private AnalysisCode analysisCode;

    @Column(name = "CHARGED_AMOUNT")
    private BigDecimal chargedAmount;

    @Column(name = "STATUS", length = 3)
    private String status;

}