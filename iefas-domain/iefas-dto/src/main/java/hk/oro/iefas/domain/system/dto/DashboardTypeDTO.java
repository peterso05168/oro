/**
 * 
 */
package hk.oro.iefas.domain.system.dto;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DashboardTypeDTO extends TxnDTO {

    private Integer dashboardTypeId;
    private String dashboardTypeDesc;
    private String forwardToUrl;
    private Integer functionAction;
    private String status;
    private PrivilegeDTO privilege;

}
