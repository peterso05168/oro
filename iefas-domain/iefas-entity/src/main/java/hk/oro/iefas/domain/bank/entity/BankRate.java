package hk.oro.iefas.domain.bank.entity;

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
@Table(name = "BANK_BANK_RATE")
public class BankRate extends UpdateTxnKeyable {

    @Id
    @Column(name = "BANK_RATE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BANK_RATE_SEQ")
    @TableGenerator(name = "BANK_RATE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "BANK_RATE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer bankRateId;

    @Column(name = "BANK_RATE_FILE_NAME", length = 25)
    private String bankRateFileName;

    @Column(name = "INVEST_DATE")
    private Date investDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURCY_ID")
    private Currency currency;

    @Column(name = "STATUS", length = 3)
    private String status;
}
