package hk.oro.iefas.domain.casemgt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author $Author: vicki.huang $
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SearchOutsiderDetailResultVO {
    private Integer outsiderId;
    private String outsiderTypeName;
    private String outsiderName;
    private String status;
}
