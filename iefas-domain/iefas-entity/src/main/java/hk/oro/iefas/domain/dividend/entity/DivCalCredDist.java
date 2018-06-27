package hk.oro.iefas.domain.dividend.entity;

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

import hk.oro.iefas.domain.adjudication.entity.AdjType;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2786 $ $Date: 2018-05-31 17:43:02 +0800 (週四, 31 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Entity
@Table(name = "DIV_CAL_CRED_DIST")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivCalCredDist extends UpdateTxnKeyable {

    @Id
    @Column(name = "CAL_CRED_DIS_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CAL_CRED_DIS_SEQ")
    @TableGenerator(name = "CAL_CRED_DIS_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CAL_CRED_DIS_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer calCredDisId;

    @Column(name = "DIST_AMOUNT", precision = 16, scale = 2)
    private BigDecimal distAmount;

    @Column(name = "DIV_CAL_ID", precision = 15)
    private Integer divCalId;

    @Column(name = "PERCENT", precision = 16, scale = 5)
    private BigDecimal percent;

    @Column(name = "STATUS", length = 3)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADJ_TYPE_ID")
    private AdjType adjType;

}