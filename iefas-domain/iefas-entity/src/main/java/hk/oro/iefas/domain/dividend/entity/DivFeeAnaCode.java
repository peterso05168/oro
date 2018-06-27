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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Entity
@Table(name = "DIV_FEE_ANA_CODE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivFeeAnaCode extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_FEE_ANA_CODE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_FEE_ANA_CODE_SEQ")
    @TableGenerator(name = "CASE_FEE_ANA_CODE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CASE_FEE_ANA_CODE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long caseFeeAnaCodeId;

    @Column(name = "ANALYSIS_CODE_ID", precision = 15)
    private BigDecimal analysisCodeId;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to DivCaseFeeType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_FEE_TYPE_ID")
    private CaseFeeType divCaseFeeType;

}