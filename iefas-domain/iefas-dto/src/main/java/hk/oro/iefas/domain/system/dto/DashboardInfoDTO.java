/**
 * 
 */
package hk.oro.iefas.domain.system.dto;

import hk.oro.iefas.domain.core.dto.TxnDTO;
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
public class DashboardInfoDTO extends TxnDTO {

    private Integer dashboardId;
    private String dashboardCount;
    private Integer postId;
    private String status;
    private String userType;
    private Boolean updated;
    private DashboardTypeDTO dashboardType;

}
