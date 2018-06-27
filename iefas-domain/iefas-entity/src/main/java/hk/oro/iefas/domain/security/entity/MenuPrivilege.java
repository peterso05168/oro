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
@Table(name = "MENU_PRIVILEGE")
public class MenuPrivilege {

    @Id
    @Column(name = "MENU_PRIVILEGE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MENU_PRIVILEGE_SEQ")
    @TableGenerator(name = "MENU_PRIVILEGE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "MENU_PRIVILEGE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer menuPrivilegeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRIVILEGE_ID")
    private Privilege privilege;

}
