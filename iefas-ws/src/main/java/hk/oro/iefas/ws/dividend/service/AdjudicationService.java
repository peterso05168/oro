package hk.oro.iefas.ws.dividend.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.dividend.dto.AdjucationDTO;
import hk.oro.iefas.domain.dividend.dto.AdjucationResultDTO;
import hk.oro.iefas.domain.dividend.dto.SearchAdjudicationCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 3021 $ $Date: 2018-06-10 16:06:34 +0800 (週日, 10 六月 2018) $
 * @author $Author: vicki.huang $
 */
public interface AdjudicationService {

    Page<AdjucationResultDTO> searchPreAdjudicationList(SearchDTO<SearchAdjudicationCriteriaDTO> criteriaDTO);

    Page<AdjucationResultDTO> searchOrdAdjudicationList(SearchDTO<SearchAdjudicationCriteriaDTO> criteriaDTO);

    AdjucationDTO searchAdjudication(Integer adjucationId);

    Integer saveAdjudication(AdjucationDTO adjucationDTO) throws Exception;
}
