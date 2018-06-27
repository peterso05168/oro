package hk.oro.iefas.domain.funds.entity;

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
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FUND_FUND_AVA_ITEM_TYPE")
public class AvaItemType extends UpdateTxnKeyable {

    @Id
    @Column(name = "FUND_AVL_ITEM_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FUND_AVL_ITEM_TYPE_SEQ")
    @TableGenerator(name = "FUND_AVL_ITEM_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "FUND_AVL_ITEM_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer fundsAvailableItemTypeId;

    @Column(name = "FUND_AVL_ITEM_NAME", length = 100)
    private String fundsAvailableItemName;

    @Column(name = "STATUS", length = 3)
    private String status;

}
