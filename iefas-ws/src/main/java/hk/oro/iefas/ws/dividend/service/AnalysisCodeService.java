package hk.oro.iefas.ws.dividend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.AnalysisCodeDTO;
import hk.oro.iefas.domain.shroff.dto.SearchAnalysisCodeCriteriaDTO;
import hk.oro.iefas.domain.shroff.dto.SearchAnalysisCodeResultDTO;

/**
 * @version $Revision: 3059 $ $Date: 2018-06-11 21:25:56 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
public interface AnalysisCodeService {
    
    Boolean isAnalysisCodeExistedByCriteria(SearchAnalysisCodeCriteriaDTO criteria);

    List<AnalysisCodeDTO> findByAnalysisCode(String analysisCode);

    Integer save(AnalysisCodeDTO analysisCodeDTO);

    AnalysisCodeDTO findOne(Integer analysisCodeId);

    Page<SearchAnalysisCodeResultDTO> findByCriteria(SearchDTO<SearchAnalysisCodeCriteriaDTO> criteria);

    Boolean existsByAnalysisCode(SearchAnalysisCodeCriteriaDTO criteria);
}
