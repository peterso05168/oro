package hk.oro.iefas.web.ledger.maintenance.analysiscode.service;

import java.util.List;

import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.domain.shroff.vo.SearchAnalysisCodeCriteriaVO;

/**
 * @version $Revision: 3047 $ $Date: 2018-06-11 21:13:55 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
public interface AnalysisCodeClientService {

    Integer save(AnalysisCodeVO analysisCodeVO);

    AnalysisCodeVO findOne(Integer analysisCodeId);

    List<AnalysisCodeVO> findByAnalysisCode(String analysisCode);

    Boolean existsByAnalysisCode(SearchAnalysisCodeCriteriaVO criteria);

    Boolean isAnalysisCodeExistedByCriteria(SearchAnalysisCodeCriteriaVO criteria);
}
