package hk.oro.iefas.domain.bank.entity;

import java.math.BigDecimal;
import java.util.Date;

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
 * @version $Revision: 1088 $ $Date: 2018-02-08 11:15:43 +0800 (週四, 08 二月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BANK_CURRENCY_RATE")
public class CurrencyRate extends UpdateTxnKeyable {

    @Id
    @Column(name = "CURCY_RATE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CURCY_RATE_SEQ")
    @TableGenerator(name = "CURCY_RATE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CURCY_RATE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer curcyRateId;

    @Column(name = "CURCY_ID")
    private Integer curcyId;

    @Column(name = "EFFECTIVE_FROM")
    private Date effectiveFrom;

    @Column(name = "EFFECTIVE_TO")
    private Date effectiveTo;

    @Column(name = "RATE_AMOUNT", length = 16, scale = 2)
    private BigDecimal rateAmount;

    @Column(name = "STATUS", length = 3)
    private String status;

}
