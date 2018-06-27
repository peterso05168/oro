package hk.oro.iefas.ws.organization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.organization.dto.DivisionAdminDTO;
import hk.oro.iefas.domain.organization.dto.DivisionDTO;
import hk.oro.iefas.domain.organization.dto.SearchDivisionAdminResultDTO;
import hk.oro.iefas.domain.organization.dto.SearchDivisionCriteriaDTO;
import hk.oro.iefas.domain.organization.entity.DivisionAdmin;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.organization.repository.DivisionAdminRepository;
import hk.oro.iefas.ws.organization.repository.UserProfileRepository;
import hk.oro.iefas.ws.organization.repository.assembler.DivisionAdminDTOAssembler;
import hk.oro.iefas.ws.organization.repository.predicate.DivisionAdminPredicate;
import hk.oro.iefas.ws.organization.service.DivisionAdminService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DivisionAdminServiceImpl implements DivisionAdminService {

    @Autowired
    private DivisionAdminRepository divisionAdminRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private DivisionAdminDTOAssembler divisionAdminDTOAssembler;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<DivisionDTO> getByPostId(Integer postId) {
        log.info("getByPostId() start - and postId = " + postId);
        List<DivisionDTO> result = divisionAdminRepository.findAll(divisionAdminDTOAssembler.toDivision(),
            DivisionAdminPredicate.findByPost(postId));
        log.info("getByPostId end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SearchDivisionAdminResultDTO> findByDivision(SearchDTO<SearchDivisionCriteriaDTO> criteria) {
        log.info("findByDivision() start - and criteria = " + criteria);
        Page<SearchDivisionAdminResultDTO> page = null;
        List<SearchDivisionAdminResultDTO> tempList = null;
        Page<SearchDivisionAdminResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = divisionAdminRepository.findAll(divisionAdminDTOAssembler.toDTO(),
                    DivisionAdminPredicate.findByDivision(criteria.getCriteria().getDivisionId()), pageable);
            }
        }
        if (page != null && CommonUtils.isNotEmpty(page.getContent())) {
            tempList = page.getContent();
            tempList.stream().forEach(item -> {
                String str = userProfileRepository.findUserNameByPostId(item.getPostId());
                item.setUserName(str == null || str.isEmpty() ? "" : str);
            });
            result = new PageImpl<>(tempList, new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
                page.getTotalElements());
        }
        log.info("findByDivision() end - and returnVal = " + result);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer save(DivisionAdminDTO divisionAdminDTO) {
        log.info("save() start - " + divisionAdminDTO);
        DivisionAdmin divisionAdmin = DataUtils.copyProperties(divisionAdminDTO, DivisionAdmin.class);
        divisionAdmin = divisionAdminRepository.save(divisionAdmin);
        log.info("save() end - and DivisionAdminId: " + divisionAdmin.getDivisionAdminId());
        return divisionAdmin.getDivisionAdminId();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public DivisionAdminDTO findOne(Integer divisionAdminId) {
        log.info("findOne() start - and postId = " + divisionAdminId);
        DivisionAdmin divisionAdmin = divisionAdminRepository.findOne(divisionAdminId);
        DivisionAdminDTO result = divisionAdminDTOAssembler.toDTO(divisionAdmin);
        log.info("findOne end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public DivisionAdminDTO findByDivisionAndPost(SearchDivisionCriteriaDTO criteria) {
        log.info("findOne() start - and criteria = " + criteria);
        DivisionAdmin divisionAdmin = divisionAdminRepository.findOne(DivisionAdminPredicate.findByCriteria(criteria));
        DivisionAdminDTO result = divisionAdminDTOAssembler.toDTO(divisionAdmin);
        log.info("findOne end - and returnVal = " + result);
        return result;
    }
}
