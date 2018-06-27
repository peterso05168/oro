package hk.oro.iefas.ws.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.organization.dto.SearchRoleCriteriaDTO;
import hk.oro.iefas.domain.organization.dto.SearchRoleResultDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.security.dto.RoleDTO;
import hk.oro.iefas.domain.security.dto.RoleSummaryDTO;
import hk.oro.iefas.domain.security.entity.Role;
import hk.oro.iefas.ws.organization.repository.DivisionRepository;
import hk.oro.iefas.ws.organization.repository.assembler.DivisionDTOAssembler;
import hk.oro.iefas.ws.security.repository.RoleRepository;
import hk.oro.iefas.ws.security.repository.assembler.RoleDTOAssembler;
import hk.oro.iefas.ws.security.repository.predicate.RolePredicate;
import hk.oro.iefas.ws.security.service.RoleService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(RoleDTO roleDTO) {
        log.info("save() start - and roleDTO = " + roleDTO);
        Integer roleId;
        if (roleDTO.getRoleId() != null && roleDTO.getRoleId() != 0) {
            Role role = DataUtils.copyProperties(roleDTO, Role.class);
            roleRepository.save(role);
            roleId = role.getRoleId();
        } else {
            roleDTO.setStatus(CoreConstant.STATUS_ACTIVE);
            if (roleDTO.getDivision().getDivisionId() != null && roleDTO.getDivision().getDivisionId() > 0) {
                roleDTO.setDivision(
                    DivisionDTOAssembler.toDTO(divisionRepository.findOne(roleDTO.getDivision().getDivisionId())));
            }
            Role role = DataUtils.copyProperties(roleDTO, Role.class);
            roleRepository.save(role);
            roleId = role.getRoleId();
        }
        log.info("save end - and returnVal = " + roleId);
        return roleId;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SearchRoleResultDTO> findByCriteria(SearchDTO<SearchRoleCriteriaDTO> criteria) {
        log.info("findByCriteria() start - " + criteria);
        Page<SearchRoleResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                result = roleRepository.findAll(RoleDTOAssembler.toResultDTO(),
                    RolePredicate.findByCriteria(criteria.getCriteria()), pageable, RolePredicate.order());
            }

        }
        log.info("findByCriteria() end - " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public RoleDTO findOne(Integer roleId) {
        log.info("findOne() start - and roleId = " + roleId);
        Role role = roleRepository.findOne(roleId);
        RoleDTO result = RoleDTOAssembler.toDTO(role);
        log.info("findOne end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsByRoleName(String roleName, Integer roleId) {
        log.info("existsByRoleName() start - roleName: " + roleName + ", roleId: " + roleId);
        boolean result = roleRepository.existsByRoleNameIgnoreCaseAndRoleIdNot(roleName, roleId);
        log.info("existsByRoleName() end - result: " + result);
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<RoleSummaryDTO> findByPostId(Integer postId) {
        log.info("findByPostId() start - postId: " + postId);
        List<RoleSummaryDTO> roleSummaryList = roleRepository.findByPostId(postId);
        log.info("findByPostId() end - " + roleSummaryList);
        return roleSummaryList;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<SearchRoleResultDTO> findByDivisionId(Integer divisionId) {
        log.info("findByDivisionId() start - divisionId: " + divisionId);
        List<SearchRoleResultDTO> resultList = roleRepository.findByDivisionId(divisionId);
        log.info("findByDivisionId() end - " + resultList);
        return resultList;
    }

}
