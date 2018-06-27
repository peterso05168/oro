package hk.oro.iefas.domain.adjudication.entity;

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
 * @version $Revision: 2908 $ $Date: 2018-06-05 17:35:06 +0800 (週二, 05 六月 2018) $
 * @author $Author: noah.liang $
 */
@Entity
@Table(name = "ADJ_RESULT_INT_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AdjResultIntItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "ADJ_INT_RESULT_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADJ_INT_RESULT_ITEM_SEQ")
    @TableGenerator(name = "ADJ_INT_RESULT_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ADJ_INT_RESULT_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer adjIntResultItemId;

    @Column(name = "ACCOUNT_NUMBER", length = 30)
    private String accountNumber;

    @Column(name = "INT_TR_ADJ_ID")
    private Integer adjIntTrAdjId;

    @Column(name = "PRINCIPLE_AMOUNT")
    private BigDecimal principleAmount;

    @Column(name = "FROM_DATE")
    private Date fromDate;

    @Column(name = "TO_DATE")
    private Date toDate;

    @Column(name = "JUDGMENT_RATE")
    private BigDecimal judgmentRate;

    @Column(name = "INTEREST_AMOUNT")
    private BigDecimal interest;

    @Column(name = "STATUS", length = 3)
    private String status;
}