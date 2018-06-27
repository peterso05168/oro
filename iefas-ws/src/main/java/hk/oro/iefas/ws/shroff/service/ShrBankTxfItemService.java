/**
 * 
 */
package hk.oro.iefas.ws.shroff.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.BankTransferItemResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchBankTransferItemDTO;

/**
 * @version $Revision: 3032 $ $Date: 2018-06-11 18:14:28 +0800 (週一, 11 六月 2018) $
 * @author $Author: marvel.ma $
 */
public interface ShrBankTxfItemService {

    Page<BankTransferItemResultDTO>
        searchBankTransferItem(SearchDTO<SearchBankTransferItemDTO> searchBankTransferItemDTO);

}
