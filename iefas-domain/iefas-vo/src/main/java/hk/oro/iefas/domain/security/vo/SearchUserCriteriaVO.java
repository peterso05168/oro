/**
 * 
 */
package hk.oro.iefas.domain.security.vo;

import java.util.List;

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
public class SearchUserCriteriaVO {

    private String loginName;
    private String englishName;
    private String EmailAddress;
    private Integer divisionId;
    private Integer postId;
    private String recordStatus;
    private List<Integer> divisionIds;
}
