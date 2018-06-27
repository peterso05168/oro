package hk.oro.iefas.domain.security.dto;

import javax.validation.constraints.Size;

import hk.oro.iefas.domain.core.dto.TxnDTO;
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
@NoArgsConstructor
@AllArgsConstructor
public class RolePrivilegeDTO extends TxnDTO {

    private Integer rolePrivilegeId;

    private PrivilegeDTO privilege;

    private RoleDTO role;

    @Size(max = 3)
    private String status;

}
