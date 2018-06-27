/**
 * 
 */
package hk.oro.iefas.domain.system.vo;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3299 $ $Date: 2018-06-26 14:19:36 +0800 (週二, 26 六月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SystemParameterVO extends TxnVO {

    private Integer systemParameterId;

    private String moduleCode;

    private String subModuleCode;

    private String parameterDesc;

    private String parameterNote;

    private String parameterValueType;

    private String parameterValue;

    private Boolean visiable;

    private String status;
}
