package hk.oro.iefas.domain.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3296 $ $Date: 2018-06-26 11:25:30 +0800 (週二, 26 六月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SYS_PARAM")
public class SystemParameter extends UpdateTxnKeyable {

    @Id
    @Column(name = "SYS_PARAM_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SYS_PARAM_SEQ")
    @TableGenerator(name = "SYS_PARAM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "SYS_PARAM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer systemParameterId;

    @Column(name = "MODULE_CODE")
    private String moduleCode;

    @Column(name = "SUB_MODULE_CODE")
    private String subModuleCode;

    @Column(name = "SYS_PARAM_DESC")
    private String parameterDesc;

    @Column(name = "PARAM_NOTE")
    private String parameterNote;

    @Column(name = "PARAM_VALUE_TYPE")
    private String parameterValueType;

    @Column(name = "SYS_PARAM_VALUE")
    private String parameterValue;
    
    @Column(name = "VISIABLE")
    private Boolean visiable;
    
    @Column(name = "STATUS")
    private String status;

}
