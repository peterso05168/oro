/**
 * 
 */
package hk.oro.iefas.ws.shroff.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.search.dto.PageDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.BankTransferItemResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchBankTransferItemDTO;
import hk.oro.iefas.ws.shroff.repository.ShrBankTxfItemRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.ShrBankTxfItemAssembler;
import hk.oro.iefas.ws.shroff.repository.predicate.ShrBankTxfItemPredicate;
import hk.oro.iefas.ws.shroff.service.ShrBankTxfItemService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3033 $ $Date: 2018-06-11 18:14:52 +0800 (週一, 11 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class ShrBankTxfItemServiceImpl implements ShrBankTxfItemService {

    @Autowired
    private ShrBankTxfItemRepository shrBankTxfItemRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<BankTransferItemResultDTO>
        searchBankTransferItem(SearchDTO<SearchBankTransferItemDTO> searchBankTransferItemDTO) {
        log.info("searchBankTransferItem() start - " + searchBankTransferItemDTO);
        Page<BankTransferItemResultDTO> result = null;
        PageDTO page = searchBankTransferItemDTO.getPage();
        if (page != null) {
            PageRequest pageable = page.toPageable();
            result = shrBankTxfItemRepository.findAll(ShrBankTxfItemAssembler.toResultDTO(),
                ShrBankTxfItemPredicate.searchBankTransferItem(searchBankTransferItemDTO.getCriteria()), pageable);
        }
        log.info("searchBankTransferItem() end - " + result);
        return result;
    }

}
