package hk.oro.iefas.domain.dividend.vo;

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
@NoArgsConstructor
@AllArgsConstructor
public class SysApprovalWfVO {
    private String action;
    private String privilegeCode;
    private String actionByPerson;
    private Integer actionByPostId;
    private Integer divisionId;
    private String remark;
}
