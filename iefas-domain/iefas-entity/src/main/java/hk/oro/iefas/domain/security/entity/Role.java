package hk.oro.iefas.domain.security.entity;

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
import hk.oro.iefas.domain.organization.entity.Division;
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
@Table(name = "ROLE")
public class Role extends UpdateTxnKeyable {

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ROLE_SEQ")
    @TableGenerator(name = "ROLE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "ROLE_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer roleId;

    @Column(name = "ROLE_NAME", length = 100)
    private String roleName;

    @Column(name = "ROLE_DESC", length = 100)
    private String roleDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVISION_ID")
    private Division division;

    @Column(name = "STATUS", length = 3)
    private String status;

}