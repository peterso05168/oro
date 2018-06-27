package hk.oro.iefas.domain.shroff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SearchControlAccountDTO {
    private Integer ctlAcId;
    private String ctlAcCode;
    private String ctlAcName;
    private String balanceNature;
    private Integer accountTypeId;
    private String status;
}
