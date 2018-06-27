package hk.oro.iefas.ws.shroff.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.*;

import java.util.List;
import java.util.Set;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
public interface ChequeService {

    Page<IncomingChequeResultDTO> searchIncomingChequeByCriteria(SearchDTO<SearchIncomingChequeDTO> criteria);

    ChequeDTO getIncomingChequeDetail(Integer chequeId);

    Integer saveIncomingCheque(ChequeDTO chequeDTO);

    Page<OutgoingChequeResultDTO> searchOutgoingChequeByCriteria(SearchDTO<SearchOutgoingChequeDTO> criteria);

    Integer saveOutgoingCheque(ChequeDTO chequeDTO);

    Boolean existsByChequeNo(SearchIncomingChequeDTO searchIncomingChequeDTO);

    ChequeDTO getByChequeNo(String chequeNo);

    ChequeDTO getOutgoingChequeDetail(Integer outgoingChequeId);

    Integer combineOutgoingCheque(Set<Integer> outgoingChequeIds);

    List<ChequeDTO> getOutgoingChequeListByParentId(Integer parentId);

    Page<ChequeFileResultDTO> searchChequeForChequeFile(SearchDTO<SearchChequeFileDTO> criteria);
}
