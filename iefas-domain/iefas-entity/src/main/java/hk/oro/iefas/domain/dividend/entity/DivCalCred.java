package hk.oro.iefas.domain.dividend.entity;

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
 * @version $Revision: 2945 $ $Date: 2018-06-06 15:38:06 +0800 (週三, 06 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Entity
@Table(name = "DIV_DIV_CAL_CRED")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivCalCred extends UpdateTxnKeyable {

    @Id
    @Column(name = "DIV_CAL_CRED_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DIV_CAL_CRED_SEQ")
    @TableGenerator(name = "DIV_CAL_CRED_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DIV_CAL_CRED_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer divCalCredId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDITOR_TYPE_ID")
    private CredType credType;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "DIV_CAL_ID")
    private Integer divCalId;

}