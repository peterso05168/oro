package hk.oro.iefas.domain.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2373 $ $Date: 2018-05-08 18:38:31 +0800 (週二, 08 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class StatusVO {

    private Integer statusId;
    private String statusName;
    private String statusVal;

}
