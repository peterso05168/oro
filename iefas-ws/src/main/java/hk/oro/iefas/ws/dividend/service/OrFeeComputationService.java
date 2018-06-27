package hk.oro.iefas.ws.dividend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.dividend.dto.CaseFeeMaintenanceDTO;
import hk.oro.iefas.domain.dividend.dto.CreateOrFeeComputationDTO;
import hk.oro.iefas.domain.dividend.dto.DividendCalculationDTO;
import hk.oro.iefas.domain.dividend.dto.SearchOrFeeComputationCriteriaDTO;
import hk.oro.iefas.domain.dividend.dto.ValidateOrFeeComputationDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 3124 $ $Date: 2018-06-13 17:47:21 +0800 (週三, 13 六月 2018) $
 * @author $Author: vicki.huang $
 */
public interface OrFeeComputationService {

    Page<DividendCalculationDTO> searchORFeeComputationList(SearchDTO<SearchOrFeeComputationCriteriaDTO> criteriaDTO);

    Integer saveORFeeComputation(DividendCalculationDTO dividendCalculationDTO);

    DividendCalculationDTO searchORFeeComputation(CreateOrFeeComputationDTO createORFeeComputationDTO);

    DividendCalculationDTO searchORFeeComputation(Integer orFeeComputationId);

    boolean validateCaseCreatable(ValidateOrFeeComputationDTO validateOrFeeComputationDTO);

    List<CaseFeeMaintenanceDTO> findCaseFeeMainsByType(String caseFeeType);
}
