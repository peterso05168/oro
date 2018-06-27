package hk.oro.iefas.domain.security.vo;

import hk.oro.iefas.domain.core.vo.TxnVO;
import hk.oro.iefas.domain.organization.vo.PostVO;
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
public class PostRoleVO extends TxnVO {

    private Integer postRoleId;
    private PostVO post;
    private RoleVO role;
    private String status;

}
