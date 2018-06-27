package hk.oro.iefas.domain.adjudication.entity;

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
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Entity
@Table(name = "ADJ_ADJ_ACC_NUMBER")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AdjAccNumber extends UpdateTxnKeyable {

    @Id
    @Column(name = "ADJ_ACC_NUMBER_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADJ_ACC_NUMBER_SEQ")
    @TableGenerator(name = "ADJ_ACC_NUMBER_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ADJ_ACC_NUMBER_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long adjucationAccountId;

    @Column(name = "ADJ_ACC_NUMBER", length = 30)
    private String adjucationAccount;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to AdjResult
    @Column(name = "ADJ_RESULT_ID", precision = 15)
    private Integer adjResultId;

}