package hk.oro.iefas.domain.shroff.entity;

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

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SHR_ANALYSIS_CODE")
public class AnalysisCode extends UpdateTxnKeyable {

    @Id
    @Column(name = "ANALYSIS_CODE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ANALYSIS_CODE_SEQ")
    @TableGenerator(name = "ANALYSIS_CODE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ANALYSIS_CODE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer analysisCodeId;

    @Column(name = "ANALYSIS_CODE")
    private String analysisCode;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "FULL_NAME")
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOUCHER_TYPE_ID")
    private VoucherType voucherType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANALYSIS_CODE_TYPE_ID")
    private AnalysisCodeType analysisCodeType;

    @Column(name = "REALIZATION_FEE")
    private String realizationFee;

    @Column(name = "STATUS")
    private String status;
}
