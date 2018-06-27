package hk.oro.iefas.domain.shroff.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2745 $ $Date: 2018-05-30 20:19:44 +0800 (週三, 30 五月 2018) $
 * @author $Author: garrett.han $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SearchRosterVO {
    private Integer rosterId;
    private Date onDutyDate;
    private String groupName;
    private Integer groupId;
    private String status;
}
