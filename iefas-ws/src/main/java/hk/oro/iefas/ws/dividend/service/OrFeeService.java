package hk.oro.iefas.ws.dividend.service;

import java.util.List;

import hk.oro.iefas.domain.dividend.dto.CalculationMaintenanceDTO;
import hk.oro.iefas.domain.dividend.dto.CaseFeeTypeDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface OrFeeService {

    public CaseFeeTypeDTO searchORFeeItemWithCalculationMethod(Integer caseFeeTypeId);

    public Integer saveORFeeItemWithCalculationMethod(CaseFeeTypeDTO caseFeeTypeDTO);

    public boolean validateSaveORFeeItemWithCalculationMethod(List<CalculationMaintenanceDTO> calculationMaintenances);

    public CaseFeeTypeDTO searchORFeeItemWithAnalysisCodeCharged(Integer caseFeeTypeId);

    public boolean saveORFeeItemWithAnalysisCodeCharged(CaseFeeTypeDTO caseFeeTypeDTO);
}
