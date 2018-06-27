package hk.oro.iefas.web.dividend.dividendprocess.orfeecomputation.service;

import java.util.List;

import hk.oro.iefas.domain.dividend.vo.CaseFeeMaintenanceVO;
import hk.oro.iefas.domain.dividend.vo.CreateOrFeeComputationVO;
import hk.oro.iefas.domain.dividend.vo.DividendCalculationVO;
import hk.oro.iefas.domain.dividend.vo.ValidateOrFeeComputationVO;

/**
 * @version $Revision: 3124 $ $Date: 2018-06-13 17:47:21 +0800 (週三, 13 六月 2018) $
 * @author $Author: vicki.huang $
 */
public interface OrFeeComputationClientService {
    Integer saveORFeeComputation(DividendCalculationVO dividendCalculationVO);

    Boolean validateCaseCreatable(ValidateOrFeeComputationVO validateOrFeeComputationVO);

    DividendCalculationVO searchORFeeComputation(Integer orFeeComputationId);

    DividendCalculationVO searchORFeeComputation(CreateOrFeeComputationVO createORFeeComputationVO);

    List<CaseFeeMaintenanceVO> findCaseFeeMainsByType(String caseFeeType);
}
