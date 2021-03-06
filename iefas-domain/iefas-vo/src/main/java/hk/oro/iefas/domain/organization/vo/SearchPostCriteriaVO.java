/**
 * 
 */
package hk.oro.iefas.domain.organization.vo;

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
public class SearchPostCriteriaVO {

    private Integer postId;
    private Integer divisionId;
    private String postTitle;
    private Integer rankId;
    private String status;
}
