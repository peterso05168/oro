package hk.oro.iefas.domain.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @version $Revision: 2404 $ $Date: 2018-05-10 17:55:24 +0800 (週四, 10 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FUND_FREE_BANK_TXF")
public class FreeBankTransfer extends UpdateTxnKeyable {

    @Id
    @Column(name = "FREE_BANK_TRANSFER_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FREE_BANK_TRANSFER_SEQ")
    @TableGenerator(name = "FREE_BANK_TRANSFER_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "FREE_BANK_TRANSFER_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer freeBankTransferId;

    @Column(name = "BANK_INFO_1_ID")
    private Integer bankInfoId1;

    @ManyToOne
    @JoinColumn(name = "BANK_INFO_2_ID")
    private BankInfo bankInfo;

    @Column(name = "STATUS")
    private String status;
}
