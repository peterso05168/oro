/**
 * 
 */
package hk.oro.iefas.domain.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (Thu, 24 May 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRejectReasonDTO {
    private Integer rejectReasonId;
    private String moduleCode;
    private String rejectReasonDesc;
    private String status;

}
