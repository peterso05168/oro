package hk.oro.iefas.domain.bank.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "BANK_BANK_DEPOSIT_TYPE")
public class BankDepositType extends UpdateTxnKeyable {

    @Id
    @Column(name = "BANK_DEPOSIT_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BANK_DEPOSIT_TYPE_SEQ")
    @TableGenerator(name = "BANK_DEPOSIT_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "BANK_DEPOSIT_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer bankDepositTypeId;

    @Column(name = "DEPOSIT_NAME", length = 50)
    private String depositName;

    @Column(name = "DEPOSIT_CODE", length = 50)
    private String depositCode;

    @Column(name = "CAL_METHOD", length = 3)
    private String calMethod;

    @Column(name = "MIN_VALUE", length = 15)
    private BigDecimal minValue;

    @Column(name = "MAX_VALUE", length = 15)
    private BigDecimal maxValue;

    @Column(name = "STATUS", length = 3)
    private String status;
}
