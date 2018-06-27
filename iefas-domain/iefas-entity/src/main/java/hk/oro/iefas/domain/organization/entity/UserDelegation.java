/**
 * 
 */
package hk.oro.iefas.domain.organization.entity;

import java.util.Date;

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
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_DELEGATION")
public class UserDelegation extends UpdateTxnKeyable {

    @Id
    @Column(name = "USER_DELEGATION_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_DELEGATION_SEQ")
    @TableGenerator(name = "USER_DELEGATION_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "USER_DELEGATION_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer userDelegationId;

    @Column(name = "DELEGRATE_FROM")
    private Integer delegateFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DELEGRATE_TO")
    private Post delegateTo;

    @Column(name = "REMARK", length = 300)
    private String remark;

    @Column(name = "EFFECTIVE_START_DATE")
    private Date effectiveStartDate;

    @Column(name = "EFFECTIVE_END_DATE")
    private Date effectiveEndDate;

    @Column(name = "STATUS", length = 3)
    private String status;
}
