package hk.oro.iefas.ws.funds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.funds.dto.AccountInvestmentItemDTO;
import hk.oro.iefas.domain.funds.dto.InvestmentInstructionDetailDTO;
import hk.oro.iefas.domain.funds.dto.SearchInvestmentInstructiontCriteriaDTO;
import hk.oro.iefas.domain.funds.entity.InvItem;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.funds.repository.InvItemRepository;
import hk.oro.iefas.ws.funds.repository.assembler.AccountInvestmentItemDTOAssembler;
import hk.oro.iefas.ws.funds.repository.assembler.InvestmentInstructionDetailDTOAssembler;
import hk.oro.iefas.ws.funds.repository.predicate.InvestItemPredicate;
import hk.oro.iefas.ws.funds.service.InvestItemService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3286 $ $Date: 2018-06-25 17:48:56 +0800 (週一, 25 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Service
public class InvestItemServiceImpl implements InvestItemService {
    @Autowired
    private AccountInvestmentItemDTOAssembler accountInvestmentItemDTOAssembler;

    @Autowired
    private InvestmentInstructionDetailDTOAssembler investmentInstructionDetailDTOAssembler;

    @Autowired
    private InvItemRepository invItemRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<AccountInvestmentItemDTO>
        findByCriteria(SearchDTO<SearchInvestmentInstructiontCriteriaDTO> criteriaDTO) {
        log.info("findByCriteria() start - " + criteriaDTO);
        Pageable pageable = criteriaDTO.getPage().toPageable();

        Page<InvItem> page
            = invItemRepository.findAll(InvestItemPredicate.findByCriteria(criteriaDTO.getCriteria()), pageable);
        Page<AccountInvestmentItemDTO> results = accountInvestmentItemDTOAssembler.toResultDTO(page);

        log.info("findByCriteria() end - " + results);
        return results;
    }

    @Override
    @Transactional(readOnly = true)
    public InvestmentInstructionDetailDTO searchInvestmentInstruction(Integer invItemId) {
        log.info("searchInvestmentInstruction() start - invItemId: " + invItemId);
        InvItem invItem = invItemRepository.findOne(invItemId);
        InvestmentInstructionDetailDTO dto = investmentInstructionDetailDTOAssembler.toDTO(invItem);
        log.info("searchInvestmentInstruction() end - " + dto);
        return dto;
    }

}
