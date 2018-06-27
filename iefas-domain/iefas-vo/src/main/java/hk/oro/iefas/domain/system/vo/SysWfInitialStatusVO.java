/**
 * 
 */
package hk.oro.iefas.domain.system.vo;

import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.security.vo.PrivilegeVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWfInitialStatusVO {

    private Integer initialStatusId;
    private PrivilegeVO privilege;
    private ApplicationCodeTableVO status;
}
