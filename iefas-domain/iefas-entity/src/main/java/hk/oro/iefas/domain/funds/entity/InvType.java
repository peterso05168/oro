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
@Table(name = "FUND_INV_TYPE")
public class InvType extends UpdateTxnKeyable {

    @Id
    @Column(name = "INVEST_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Invest_Type_SEQ")
    @TableGenerator(name = "Invest_Type_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "INVEST_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer investmentTypeId;

    @Column(name = "INVEST_TYPE_NAME", length = 50)
    private String investmentTypeDesc;

    @Column(name = "INVEST_TYPE_CODE", length = 50)
    private String investTypeCode;

    @Column(name = "DISPLAY_OPTION", length = 3)
    private String displayOption;

    @Column(name = "STATUS", length = 3)
    private String status;

}
