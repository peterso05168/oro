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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "DIV_CASE_FEE_CAL")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivCaseFeeCal extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_FEE_CAL_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_FEE_CAL_SEQ")
    @TableGenerator(name = "CASE_FEE_CAL_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CASE_FEE_CAL_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer caseFeeCalId;

    @Column(name = "CASE_FEE_AMOUNT", precision = 16, scale = 2)
    private BigDecimal caseFeeAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "CASE_FEE_DATE")
    private Date caseFeeDate;

    @Column(name = "CASE_FEE_DESC", precision = 15)
    private Integer caseFeeDesc;

    @Column(name = "DIV_CAL_ID", precision = 15)
    private Integer divCalId;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to DivCaseFeeType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_FEE_TYPE_ID")
    private CaseFeeType divCaseFeeType;

}