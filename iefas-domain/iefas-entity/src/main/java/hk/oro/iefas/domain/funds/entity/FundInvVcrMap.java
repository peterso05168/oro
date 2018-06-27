package hk.oro.iefas.domain.funds.entity;

import java.math.BigDecimal;

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
@Table(name = "FUND_INV_VCR_MAP")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class FundInvVcrMap extends UpdateTxnKeyable {

    @Id
    @Column(name = "INV_VCR_MAP_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "INV_VCR_MAP_SEQ")
    @TableGenerator(name = "INV_VCR_MAP_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "INV_VCR_MAP_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long invVcrMapId;

    @Column(name = "INVEST_ITEM_ID")
    private BigDecimal investItemId;

    @Column(name = "MATURITY_VCR_ID")
    private BigDecimal maturityVcrId;

    @Column(name = "PLACED_VCR_ID")
    private BigDecimal placedVcrId;

    @Column(name = "STATUS", length = 3)
    private String status;

}