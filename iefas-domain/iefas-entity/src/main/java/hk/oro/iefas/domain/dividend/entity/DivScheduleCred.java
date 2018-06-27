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

import hk.oro.iefas.domain.adjudication.entity.CredType;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3087 $ $Date: 2018-06-12 18:02:20 +0800 (週二, 12 六月 2018) $
 * @author $Author: noah.liang $
 */
@Entity
@Table(name = "DIV_SCHEDULE_CRED")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivScheduleCred extends UpdateTxnKeyable {

    @Id
    @Column(name = "SCHEDULE_CRED_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DIV_SCHEDULE_CRED_SEQ")
    @TableGenerator(name = "DIV_SCHEDULE_CRED_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "SCHEDULE_CRED_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer scheduleCredId;

    @Column(name = "DIV_SCHD_ID", precision = 15)
    private BigDecimal divSchdId;

    @Column(length = 200)
    private String remark;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to AdjCredType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDITOR_TYPE_ID")
    private CredType adjCredType;

}