package hk.oro.iefas.ws.shroff.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.ControlAccountDTO;
import hk.oro.iefas.domain.shroff.dto.ControlAccountResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchControlAccountDTO;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
public interface ControlAccountService {
    Page<ControlAccountResultDTO> searchControlAccountResultList(SearchDTO<SearchControlAccountDTO> criteria);

    ControlAccountDTO getControlAccountDetail(Integer ctlAcId);

    Integer saveControlAccount(ControlAccountDTO controlAccountDTO);

    List<ControlAccountDTO> findAllControlAccounts();

    Boolean existsByControlAccountName(SearchControlAccountDTO criteria);

    Boolean existsByControlAccountCode(SearchControlAccountDTO criteria);
}
