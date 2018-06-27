package hk.oro.iefas.ws.casemgt.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.casemgt.dto.OutsiderDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchOutsiderDetailCriteriaDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchOutsiderDetailResultDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */

public interface OutsiderService {
    Page<SearchOutsiderDetailResultDTO> findByCriteria(SearchDTO<SearchOutsiderDetailCriteriaDTO> criteria);

    OutsiderDTO getOutsiderDetail(Integer OutsiderId);

    Integer saveOutsider(OutsiderDTO outsiderDTO);

    Boolean existsByOutsiderName(SearchOutsiderDetailCriteriaDTO criteriaDTO);
}
