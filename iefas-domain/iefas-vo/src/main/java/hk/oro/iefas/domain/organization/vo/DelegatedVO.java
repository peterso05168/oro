/**
 * 
 */
package hk.oro.iefas.domain.organization.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2214 $ $Date: 2018-04-25 14:05:52 +0800 (週三, 25 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelegatedVO {

    private String loginName;
    private String engFullName;
    private String post;
    private Integer postId;
    private Integer divisionId;
}
