package hk.oro.iefas.domain.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeVO {

    private Integer privilegeId;
    private String privilegeName;
    private String privilegeDesc;
    private String privilegeType;
    private String privilegeCode;

}
