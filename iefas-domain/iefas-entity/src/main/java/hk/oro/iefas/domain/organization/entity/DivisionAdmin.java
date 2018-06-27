package hk.oro.iefas.domain.organization.entity;

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
@Table(name = "DIVISION_ADMIN")
public class DivisionAdmin extends UpdateTxnKeyable {

    @Id
    @Column(name = "DIVISION_ADMIN_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DIVISION_ADMIN_SEQ")
    @TableGenerator(name = "DIVISION_ADMIN_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DIVISION_ADMIN_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer divisionAdminId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVISION_ID")
    private Division division;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Column(name = "REMARK", length = 300)
    private String remark;

    @Column(name = "STATUS", length = 3)
    private String status;

}
