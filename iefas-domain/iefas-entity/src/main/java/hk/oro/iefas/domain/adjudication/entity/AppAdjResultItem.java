package hk.oro.iefas.domain.adjudication.entity;

import java.math.BigDecimal;

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
@Entity
@Table(name = "ADJ_APP_RESULT_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AppAdjResultItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "APP_RESULT_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "APP_RESULT_ITEM_SEQ")
    @TableGenerator(name = "APP_RESULT_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "APP_RESULT_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer appResultItemId;

    @Column(name = "ADJ_RESULT_ID", precision = 15)
    private Integer adjResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADJ_TYPE_ID")
    private AdjType adjType;

    @Column(name = "ADM_AMOUNT", precision = 16, scale = 2)
    private BigDecimal admAmount;

    @Column(name = "AMOUNT_PAID", precision = 16, scale = 2)
    private BigDecimal amountPaid;

    @Column(name = "PERCENT_PAID", precision = 16, scale = 2)
    private BigDecimal percentPaid;

    @Column(name = "STATUS", length = 3)
    private String status;

}