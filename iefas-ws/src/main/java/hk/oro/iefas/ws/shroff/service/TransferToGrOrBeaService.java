package hk.oro.iefas.ws.shroff.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.SearchTransferToGrOrBeaCriteriaDTO;
import hk.oro.iefas.domain.shroff.dto.SearchTransferToGrOrBeaResultDTO;
import hk.oro.iefas.domain.shroff.dto.TransferToGrOrBeaDTO;

/**
 * @version $Revision: 3174 $ $Date: 2018-06-15 19:54:00 +0800 (週五, 15 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface TransferToGrOrBeaService {

    TransferToGrOrBeaDTO getTransferToGrOrBeaDetail(Integer transferId);

    Page<SearchTransferToGrOrBeaResultDTO> findByCriteria(SearchDTO<SearchTransferToGrOrBeaCriteriaDTO> criteria);

    Integer saveTransferToGrOrBea(TransferToGrOrBeaDTO transfer);
}
