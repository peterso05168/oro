package hk.oro.iefas.ws.casemgt.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchCaseDetailCriteriaDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchCaseDetailResultDTO;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2768 $ $Date: 2018-05-31 16:03:24 +0800 (週四, 31 五月 2018) $
 * @author $Author: garrett.han $
 */
public interface CaseService {

    Boolean validateExistsByCaseNo(String caseNo);

    Page<SearchCaseDetailResultDTO> findByCriteria(SearchDTO<SearchCaseDetailCriteriaDTO> criteria);

    CaseDTO findOne(Integer caseId);

    CaseDTO findByCaseNumber(CaseNumberDTO caseNumber);

    CaseDTO findByWholeCaseNumber(String wholeCaseNumber);
}
