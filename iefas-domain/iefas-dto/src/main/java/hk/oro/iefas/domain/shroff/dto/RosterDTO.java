package hk.oro.iefas.domain.shroff.dto;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import hk.oro.iefas.domain.organization.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RosterDTO extends TxnDTO {
    private Integer rosterId;
    private Date onDutyDate;
    private String status;
    private PostDTO groupAPost;
    private PostDTO groupBPost;
}
