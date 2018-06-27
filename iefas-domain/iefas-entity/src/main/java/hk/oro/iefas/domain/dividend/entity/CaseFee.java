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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.casemgt.entity.Case;
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
@Table(name = "DIV_CASE_FEE")
public class CaseFee extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_FEE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_FEE_SEQ")
    @TableGenerator(name = "CASE_FEE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CASE_FEE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer caseFeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_FEE_TYPE_ID")
    private CaseFeeType caseFeeType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ID")
    private Case casemgt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIV_SCHD_ID")
    private DivSchedule divSchedule;

    @Column(name = "ISSUES_DATE")
    private Date issuesDate;

    @Column(name = "FEE_AMOUNT", length = 16, scale = 2)
    private BigDecimal feeAmount;

    @Column(name = "AMOUNT_PAID", length = 16, scale = 2)
    private BigDecimal amountPaid;

    @Column(name = "STATUS")
    private String status;
}
