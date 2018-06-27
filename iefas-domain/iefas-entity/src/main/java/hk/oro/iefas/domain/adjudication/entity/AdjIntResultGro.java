package hk.oro.iefas.domain.adjudication.entity;

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
 * @version $Revision: 2724 $ $Date: 2018-05-30 14:08:55 +0800 (週三, 30 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Entity
@Table(name = "ADJ_INT_RESULT_GRO")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AdjIntResultGro extends UpdateTxnKeyable {

    @Id
    @Column(name = "ADJ_INT_RESULT_GRO_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADJ_INT_RESULT_GRO_SEQ")
    @TableGenerator(name = "ADJ_INT_RESULT_GRO_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ADJ_INT_RESULT_GRO_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long adjIntResultGroId;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to AdjGroCode
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUND_CODE_ID")
    private GroCode adjGroCode;

}