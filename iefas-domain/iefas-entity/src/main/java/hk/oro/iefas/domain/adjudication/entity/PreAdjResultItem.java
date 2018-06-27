package hk.oro.iefas.domain.adjudication.entity;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2724 $ $Date: 2018-05-30 14:08:55 +0800 (週三, 30 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Entity
@Table(name = "ADJ_PRE_ADJ_RE_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PreAdjResultItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "PRE_ADJ_RESULT_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PRE_ADJ_RESULT_ITEM_SEQ")
    @TableGenerator(name = "PRE_ADJ_RESULT_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "PRE_ADJ_RESULT_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer preAdjResultItemId;

    @Column(name = "ADJ_RESULT_ID", precision = 15)
    private Integer adjResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADJ_TYPE_ID")
    private AdjType adjType;

    @Column(name = "IS_REJECT_TYPE")
    private String isRejectType;

    @Column(name = "PRE_ADJ_RESULT_TYPE")
    private String preAdjResultType;

    @Column(name = "RESULT_AMOUNT", precision = 16, scale = 2)
    private BigDecimal resultAmount;

    @Column(name = "STATUS", length = 3)
    private String status;

    @OneToMany(mappedBy = "preAdjResultItemId", fetch = FetchType.LAZY)
    private List<PreAdjResultGro> preAdjResultGros;

}