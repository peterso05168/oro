package hk.oro.iefas.domain.organization.entity;

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
 * @version $Revision: 2169 $ $Date: 2018-04-23 16:58:23 +0800 (週一, 23 四月 2018) $
 * @author $Author: Garrett.han $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RANK")
public class Rank extends UpdateTxnKeyable {

    @Id
    @Column(name = "RANK_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RANK_SEQ")
    @TableGenerator(name = "RANK_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "RANK_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer rankId;

    @Column(name = "RANK_NAME", length = 100)
    private String rankName;

    @Column(name = "RANK_DESC", length = 200)
    private String rankDesc;

    @Column(name = "VOUCHER_LIMIT")
    private Integer voucherLimit;

    @Column(name = "PAYMENT_LIMIT")
    private Integer paymentLimit;

    @Column(name = "RANK_LEVEL")
    private Integer rankLevel;

    @Column(name = "NARRATIVE_CODE")
    private String narrativeCode;

    @Column(name = "STATUS")
    private String status;
}
