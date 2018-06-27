package hk.oro.iefas.web.dividend.maintenance.orsfees.service;

import java.util.List;

import hk.oro.iefas.domain.dividend.vo.CalculationMaintenanceVO;
import hk.oro.iefas.domain.dividend.vo.CaseFeeTypeVO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface OrFeeClientService {

    public CaseFeeTypeVO searchORFeeItemWithCalculationMethod(Integer caseFeeTypeId);

    public Boolean validatesSaveORFeeItemWithCalculationMethod(List<CalculationMaintenanceVO> calculationMaintenances);

    public Integer saveORFeeItemWithCalculationMethod(CaseFeeTypeVO caseFeeTypeVO);

    public CaseFeeTypeVO searchORFeeItemWithAnalysisCodeCharged(Integer caseFeeTypeId);
}
