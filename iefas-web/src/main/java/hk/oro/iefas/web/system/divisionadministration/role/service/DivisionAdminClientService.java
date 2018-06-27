package hk.oro.iefas.web.system.divisionadministration.role.service;

import java.util.List;

import hk.oro.iefas.domain.organization.vo.DivisionAdminVO;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.SearchDivisionCriteriaVO;

public interface DivisionAdminClientService {

    List<DivisionVO> getByPostId(Integer postId);

    List<DivisionAdminVO> getByDivisionId(Integer divisionId);

    Integer save(DivisionAdminVO divisionAdminVO);

    DivisionAdminVO findOne(Integer divisionAdminId);

    DivisionAdminVO findByDivisionAndPost(SearchDivisionCriteriaVO criteria);
}
