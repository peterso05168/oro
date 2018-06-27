package hk.oro.iefas.domain.dividend.entity;

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
import hk.oro.iefas.domain.shroff.entity.ShrCheque;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3085 $ $Date: 2018-06-12 17:09:16 +0800 (週二, 12 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Entity
@Table(name = "DIV_DIVIDEND_CHEQUE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DividendCheque extends UpdateTxnKeyable {

    @Id
    @Column(name = "DIV_CHEQUE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DIV_CHEQUE_SEQ")
    @TableGenerator(name = "DIV_CHEQUE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DIV_CHEQUE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer dividendChequeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHEQUE_ID")
    private ShrCheque shrCheque;

    @Column(name = "REISSUES_REASON", length = 200)
    private String reissuesReason;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "DIV_SCHEDULE_ITEM_ID")
    private Integer divScheduleItemId;

}