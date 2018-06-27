package hk.oro.iefas.domain.dividend.entity;

import java.math.BigDecimal;
import java.util.Date;

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
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DIV_CASE_FEE_MAIN")
public class CaseFeeMain extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_FEE_MAIN_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_FEE_MAIN_SEQ")
    @TableGenerator(name = "CASE_FEE_MAIN_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CASE_FEE_MAIN_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer caseFeeMainId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_FEE_TYPE_ID")
    private CaseFeeType caseFeeType;

    @Column(name = "CAL_TYPE")
    private String calType;

    @Column(name = "PERIOD_FROM")
    private Date periodFrom;

    @Column(name = "PERIOD_TO")
    private Date periodTo;

    @Column(name = "FEE_AMOUNT", length = 16, scale = 2)
    private BigDecimal feeAmount;

    @Column(name = "PERCENT", length = 16, scale = 5)
    private BigDecimal percent;

    @Column(name = "VALUE_FROM")
    private Integer valueFrom;

    @Column(name = "VALUE_TO")
    private Integer valueTo;

    @Column(name = "VALUE_PERCENT", length = 16, scale = 5)
    private BigDecimal valuePercent;

    @Column(name = "ROUNDING_UNIT")
    private Integer roundingUnit;

    @Column(name = "ROUNDING_AMOUNT")
    private Integer roundingAmount;

    @Column(name = "STATUS")
    private String status;
}
