package hk.oro.iefas.web.system.systemadministration.divisionprivillege.service;

import java.util.List;

import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.SearchDivisionCriteriaVO;

public interface DivisionClientService {

    List<DivisionVO> findByCriteria(SearchDivisionCriteriaVO criteria);

    DivisionVO findOne(Integer divisionId);
}
