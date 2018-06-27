/**
 * 
 */
package hk.oro.iefas.domain.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3145 $ $Date: 2018-06-14 18:24:19 +0800 (週四, 14 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionVO {

    private Boolean saveable;
    private Boolean submitable;
    private Boolean approvable;
    private Boolean rejectable;
    private Boolean deletable;
    private Boolean submitFor2ndApprovable;
    private Boolean verifiable;
    private Boolean reversible;

}
