package hk.oro.iefas.domain.casemgt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SearchOutsiderDetailCriteriaDTO {

    private String outsiderName;
    private String status;
    private Integer outsiderTypeId;
    private Integer outsiderId;
}
