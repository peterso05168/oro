package hk.oro.iefas.ws.funds.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.funds.dto.AccountInvestmentItemDTO;
import hk.oro.iefas.domain.funds.dto.InvestmentInstructionDetailDTO;
import hk.oro.iefas.domain.funds.dto.SearchInvestmentInstructiontCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface InvestItemService {
    Page<AccountInvestmentItemDTO> findByCriteria(SearchDTO<SearchInvestmentInstructiontCriteriaDTO> criteriaDTO);

    InvestmentInstructionDetailDTO searchInvestmentInstruction(Integer invItemId);
}
