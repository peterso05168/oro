package hk.oro.iefas.domain.bank.entity;

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
 * @version $Revision: 981 $ $Date: 2018-01-31 10:26:00 +0800 (週三, 31 一月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BANK_CURRENCY")
public class Currency extends UpdateTxnKeyable {

    @Id
    @Column(name = "CURCY_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CURCY_SEQ")
    @TableGenerator(name = "CURCY_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "CURCY_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer curcyId;

    @Column(name = "CURCY_NAME", length = 100)
    private String curcyName;

    @Column(name = "CURCY_CODE", length = 5)
    private String curcyCode;

    @Column(name = "STATUS", length = 3)
    private String status;

    @OneToMany(mappedBy = "curcyId", fetch = FetchType.LAZY)
    private List<CurrencyRate> currencyRates;

}
