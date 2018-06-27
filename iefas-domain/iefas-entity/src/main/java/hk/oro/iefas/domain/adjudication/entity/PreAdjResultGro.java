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
@Table(name = "ADJ_PRE_RESULT_GRO")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PreAdjResultGro extends UpdateTxnKeyable {

    @Id
    @Column(name = "PRE_RESULT_GRO_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PRE_RESULT_GRO_SEQ")
    @TableGenerator(name = "PRE_RESULT_GRO_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "PRE_RESULT_GRO_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private BigDecimal preResultGroId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUND_CODE_ID")
    private GroCode groundCode;

    @Column(name = "PRE_ADJ_RESULT_ITEM_ID", precision = 15)
    private Integer preAdjResultItemId;

    @Column(name = "STATUS", length = 3)
    private String status;

}