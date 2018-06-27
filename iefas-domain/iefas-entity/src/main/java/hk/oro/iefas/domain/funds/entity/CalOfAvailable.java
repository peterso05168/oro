package hk.oro.iefas.domain.funds.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "FUND_AVAILABLE")
public class CalOfAvailable extends UpdateTxnKeyable {

    @Id
    @Column(name = "FUNDS_AVAILABLE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FUNDS_AVAILABLE_SEQ")
    @TableGenerator(name = "FUNDS_AVAILABLE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "FUNDS_AVAILABLE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer calculationOfFundsAvailableId;

    @Column(name = "SUBMITTED_BY", length = 100)
    private String submittedBy;

    @Column(name = "INVEST_DATE")
    private Date investmentDate;

    @Column(name = "APPROVED_BY", length = 100)
    private String approvedBy;

    @Column(name = "AVAI_AMOUNT", length = 16, scale = 2)
    private BigDecimal availableAmount;

    @OneToMany(mappedBy = "calculationOfFundsAvailableId", fetch = FetchType.LAZY)
    private List<CalOfAvailableItem> calculationOfFundsAvailableItems;

    @Column(name = "STATUS", length = 3)
    private String status;
}
