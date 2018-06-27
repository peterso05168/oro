package hk.oro.iefas.domain.organization.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @version $Revision: 2214 $ $Date: 2018-04-25 14:05:52 +0800 (週三, 25 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"divisions"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DIVISION")
public class Division extends UpdateTxnKeyable {

    @Id
    @Column(name = "DIVISION_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DIVISION_SEQ")
    @TableGenerator(name = "DIVISION_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DIVISION_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer divisionId;

    @Column(name = "DIVISION_NAME", length = 100)
    private String divisionName;

    @Column(name = "PARENT_DIVISION_ID")
    private Integer parentDivisionId;

    @Column(name = "STATUS", length = 3)
    private String status;

    @OneToMany(mappedBy = "parentDivisionId", fetch = FetchType.LAZY)
    private List<Division> divisions;
}
