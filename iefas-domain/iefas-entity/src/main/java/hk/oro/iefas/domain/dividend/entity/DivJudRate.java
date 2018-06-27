package hk.oro.iefas.domain.dividend.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "DIV_JUD_RATE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivJudRate extends UpdateTxnKeyable {

    @Id
    @Column(name = "JUD_RATE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "JUD_RATE_SEQ")
    @TableGenerator(name = "JUD_RATE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "JUD_RATE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long judRateId;

    @Column(name = "JUD_RATE")
    private BigDecimal judRate;

    @Temporal(TemporalType.DATE)
    @Column(name = "PERIOD_FROM")
    private Date periodFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "PERIOD_TO")
    private Date periodTo;

    @Column(name = "STATUS", length = 3)
    private String status;

}