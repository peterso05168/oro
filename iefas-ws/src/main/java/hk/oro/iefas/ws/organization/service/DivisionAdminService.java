package hk.oro.iefas.ws.organization.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.organization.dto.DivisionAdminDTO;
import hk.oro.iefas.domain.organization.dto.DivisionDTO;
import hk.oro.iefas.domain.organization.dto.SearchDivisionAdminResultDTO;
import hk.oro.iefas.domain.organization.dto.SearchDivisionCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

public interface DivisionAdminService {

    List<DivisionDTO> getByPostId(Integer postId);

    Integer save(DivisionAdminDTO divisionAdminDTO);

    Page<SearchDivisionAdminResultDTO> findByDivision(SearchDTO<SearchDivisionCriteriaDTO> criteria);

    DivisionAdminDTO findOne(Integer divisionAdminId);

    DivisionAdminDTO findByDivisionAndPost(SearchDivisionCriteriaDTO criteria);
}
