package hk.oro.iefas.domain.dividend.entity;

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
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DIV_FEE_CHARG")
public class FeeCharg extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_FEE_TO_BE_CHARGED_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FEE_CHARG_SEQ")
    @TableGenerator(name = "FEE_CHARG_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CASE_FEE_TO_BE_CHARGED_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer caseFeeToBeChargedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_FEE_TYPE_ID")
    private CaseFeeType caseFeeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANALYSIS_CODE_ID")
    private AnalysisCode analysisCode;

    @Column(name = "STATUS")
    private String status;
}
