package hk.oro.iefas.domain.shroff.entity;

import java.io.Serializable;
import javax.persistence.*;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the SHR_REALIZATION_FEE database table.
 * 
 */
@Entity
@Table(name = "SHR_REALIZATION_FEE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrRealizationFee extends UpdateTxnKeyable {

    @Id
    @Column(name = "REALIZATION_FEE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "REALIZATION_FEE_SEQ")
    @TableGenerator(name = "REALIZATION_FEE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "REALIZATION_FEE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long realizationFeeId;

    @Column(name = "ANALYSIS_CODE_DESC", length = 200)
    private String analysisCodeDesc;

    @Column(name = "ANALYSIS_CODE_ID", precision = 15)
    private BigDecimal analysisCodeId;

    @Column(name = "FEE_AMOUNT", precision = 16, scale = 2)
    private BigDecimal feeAmount;

    @Column(name = "STATUS", length = 3)
    private String status;

}