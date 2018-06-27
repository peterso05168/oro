package hk.oro.iefas.ws.bank.service;

import java.util.Date;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.bank.dto.CalculationOfFundsAvailableDTO;
import hk.oro.iefas.domain.bank.dto.SearchPlacingDepositsCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public interface PlacingDepositsService {
    Page<CalculationOfFundsAvailableDTO>
        searchPlacingDepositsList(SearchDTO<SearchPlacingDepositsCriteriaDTO> criteriaDTO);

    CalculationOfFundsAvailableDTO findOne(Integer calculationOfFundsAvailableId);

    Integer save(CalculationOfFundsAvailableDTO calculationOfFundsAvailableDTO);

    Boolean existsByInvestmentDate(Date invtDate);
}
