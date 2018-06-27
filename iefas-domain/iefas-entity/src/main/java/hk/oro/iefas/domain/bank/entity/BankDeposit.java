package hk.oro.iefas.domain.bank.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BANK_BANK_DEPOSIT")
public class BankDeposit extends UpdateTxnKeyable {

    @Id
    @Column(name = "BANK_DEPOSIT_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BANK_DEPOSIT_SEQ")
    @TableGenerator(name = "BANK_DEPOSIT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "BANK_DEPOSIT_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer bankDepositId;

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "BANK_INFO_ID", referencedColumnName = "BANK_INFO_ID", unique = true)
    private BankInfo bankInfo;

    @Column(name = "BANK_INVEST_AMOUNT", length = 16, scale = 2)
    private BigDecimal bankInvestAmount;

    @Column(name = "STATUS")
    private String status;

}
