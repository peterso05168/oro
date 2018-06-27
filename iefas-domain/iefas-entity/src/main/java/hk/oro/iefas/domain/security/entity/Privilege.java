package hk.oro.iefas.domain.security.entity;

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
@Table(name = "PRIVILEGE")
public class Privilege {

    @Id
    @Column(name = "PRIVILEGE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PRIVILEGE_SEQ")
    @TableGenerator(name = "PRIVILEGE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "PRIVILEGE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer privilegeId;

    @Column(name = "PRIVILEGE_NAME", length = 100)
    private String privilegeName;

    @Column(name = "PRIVILEGE_DESC", length = 200)
    private String privilegeDesc;

    @Column(name = "PRIVILEGE_TYPE", length = 3)
    private String privilegeType;

    // @OneToMany(mappedBy = "divisionPrivilegeId", fetch = FetchType.LAZY)
    // private List<DivisionPrivilege> divisionPrivileges;
    //
    // @OneToMany(mappedBy = "rolePrivilegeId", fetch = FetchType.LAZY)
    // private List<RolePrivilege> rolePrivileges;
    //
    // @OneToMany(mappedBy = "menuPrivilegeId", fetch = FetchType.LAZY)
    // private List<MenuPrivilege> menuPrivileges;
    //
    // @OneToMany(mappedBy = "reportPrivilegeId", fetch = FetchType.LAZY)
    // private List<ReportPrivilege> reportPrivileges;

    @Column(name = "PRIVILEGE_CODE", length = 10)
    private String privilegeCode;

}
