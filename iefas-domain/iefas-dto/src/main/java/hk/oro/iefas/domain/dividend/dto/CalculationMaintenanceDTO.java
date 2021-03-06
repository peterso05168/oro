package hk.oro.iefas.domain.dividend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CalculationMaintenanceDTO {

    private Long id;
    private CalculationInfoDTO calculationInfo;
    private List<CaseFeeMaintenanceDTO> caseFeeMaintenances = new ArrayList<>();
}
