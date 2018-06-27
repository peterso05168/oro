package hk.oro.iefas.domain.shroff.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import hk.oro.iefas.domain.funds.entity.InvItem;
import hk.oro.iefas.domain.funds.entity.InvType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_CTL_AC database table.
 * 
 */
@Entity
@Table(name = "SHR_CTL_AC")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrCtlAc extends UpdateTxnKeyable {

    @Id
    @Column(name = "CTL_AC_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CTL_AC_SEQ")
    @TableGenerator(name = "CTL_AC_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "CTL_AC_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer ctlAcId;

    @Column(name = "BALANCE_NATURE", length = 3)
    private String balanceNature;

    @Column(name = "CTL_AC_CODE", length = 100)
    private String ctlAcCode;

    @Column(name = "CTL_AC_NAME", length = 100)
    private String ctlAcName;

    @Column(name = "CURCY_ID", precision = 15)
    private Integer curcyId;

    @Column(name = "ON_HOLD_AMOUNT_CR", precision = 16, scale = 2)
    private BigDecimal onHoldAmountCr;

    @Column(name = "ON_HOLD_AMOUNT_DR", precision = 16, scale = 2)
    private BigDecimal onHoldAmountDr;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to FundInvItem
    @OneToMany(mappedBy = "accountInvestmentItemId", fetch = FetchType.LAZY)
    private List<InvItem> fundInvItems;

    // bi-directional many-to-one association to FundInvType
    @OneToMany(mappedBy = "investmentTypeId", fetch = FetchType.LAZY)
    private List<InvType> fundInvTypes;

    // bi-directional many-to-one association to ShrCtlAcType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTL_AC_TYPE_ID")
    private ShrCtlAcType shrCtlAcType;

    @Column(name = "BALANCE", precision = 16, scale = 2)
    private BigDecimal balance;

    @Column(name = "LIQUID_CASH_AMOUNT", precision = 16, scale = 2)
    private BigDecimal liquidCashAmount;

    @Column(name = "INVESTMENT_AMOUNT", precision = 16, scale = 2)
    private BigDecimal investmentAmount;

}