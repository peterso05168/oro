package hk.oro.iefas.ws.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.security.dto.MenuPrivilegeDTO;
import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import hk.oro.iefas.domain.security.dto.ReportPrivilegeDTO;
import hk.oro.iefas.domain.security.dto.RolePrivilegeDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeCriteriaDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeResultDTO;
import hk.oro.iefas.domain.security.entity.RolePrivilege;
import hk.oro.iefas.ws.common.util.CommonDataUtils;
import hk.oro.iefas.ws.security.repository.MenuPrivilegeRepository;
import hk.oro.iefas.ws.security.repository.PrivilegeRepository;
import hk.oro.iefas.ws.security.repository.ReportPrivilegeRepository;
import hk.oro.iefas.ws.security.repository.RolePrivilegeRepository;
import hk.oro.iefas.ws.security.repository.RoleRepository;
import hk.oro.iefas.ws.security.repository.assembler.MenuPrivilegeDTOAssembler;
import hk.oro.iefas.ws.security.repository.assembler.PrivilegeDTOAssembler;
import hk.oro.iefas.ws.security.repository.assembler.ReportPrivilegeDTOAssembler;
import hk.oro.iefas.ws.security.repository.assembler.RoleDTOAssembler;
import hk.oro.iefas.ws.security.repository.assembler.RolePrivilegeDTOAssembler;
import hk.oro.iefas.ws.security.repository.predicate.RolePrivilegePredicate;
import hk.oro.iefas.ws.security.service.RolePrivilegeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class RolePrivilegeServiceImpl implements RolePrivilegeService {

    @Autowired
    private RolePrivilegeRepository rolePrivilegeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private MenuPrivilegeRepository menuPrivilegeRepository;

    @Autowired
    private ReportPrivilegeRepository reportPrivilegeRepository;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SearchPrivilegeResultDTO> findByCriteria(SearchPrivilegeCriteriaDTO criteria) {
        log.info("findByCriteria() start - and criteria = " + criteria);
        List<PrivilegeDTO> list = rolePrivilegeRepository.findAll(RolePrivilegeDTOAssembler.toResultDTO(),
            RolePrivilegePredicate.findByCriteria(criteria));
        List<SearchPrivilegeResultDTO> resultList = null;
        if (CommonUtils.isNotEmpty(list)) {
            resultList = new ArrayList<>();
            for (PrivilegeDTO temp : list) {
                SearchPrivilegeResultDTO dto = new SearchPrivilegeResultDTO();
                List<MenuPrivilegeDTO> menuPrivileges = MenuPrivilegeDTOAssembler
                    .toDTOList(menuPrivilegeRepository.findByPrivilegeId(temp.getPrivilegeId()));
                List<ReportPrivilegeDTO> reportPrivileges = ReportPrivilegeDTOAssembler
                    .toDTOList(reportPrivilegeRepository.findByPrivilegeId(temp.getPrivilegeId()));
                dto.setPrivilege(temp);
                dto.setMenuName(CommonDataUtils.genMenuName(menuPrivileges));
                dto.setReportName(CommonDataUtils.genReportName(reportPrivileges));
                dto.setRoleId(criteria.getRoleId());
                resultList.add(dto);
            }
        }
        log.info("findByCriteria end - and returnVal = " + resultList);
        return resultList;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public RolePrivilegeDTO findOne(Integer rolePrivilegeId) {
        log.info("findOne() start - and rolePrivilegeId = " + rolePrivilegeId);
        RolePrivilege rolePrivilege = rolePrivilegeRepository.findOne(rolePrivilegeId);
        RolePrivilegeDTO result = RolePrivilegeDTOAssembler.toDTO(rolePrivilege);
        log.info("findOne end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(RolePrivilegeDTO rolePrivilegeDTO) {
        log.info("save() start - and rolePrivilegeDTO = " + rolePrivilegeDTO);
        Integer rolePrivilegeId;
        if (rolePrivilegeDTO.getRolePrivilegeId() != null && rolePrivilegeDTO.getRolePrivilegeId() != 0) {
            RolePrivilege rolePrivilege = DataUtils.copyProperties(rolePrivilegeDTO, RolePrivilege.class);
            rolePrivilegeRepository.save(rolePrivilege);
            rolePrivilegeId = rolePrivilege.getRolePrivilegeId();
        } else {
            rolePrivilegeDTO.setStatus(CoreConstant.STATUS_ACTIVE);
            if (rolePrivilegeDTO.getRole().getRoleId() != null && rolePrivilegeDTO.getRole().getRoleId() != 0) {
                rolePrivilegeDTO
                    .setRole(RoleDTOAssembler.toDTO(roleRepository.findOne(rolePrivilegeDTO.getRole().getRoleId())));
            }
            if (rolePrivilegeDTO.getPrivilege().getPrivilegeId() != null
                && rolePrivilegeDTO.getPrivilege().getPrivilegeId() != 0) {
                rolePrivilegeDTO.setPrivilege(PrivilegeDTOAssembler
                    .toDTO(privilegeRepository.findOne(rolePrivilegeDTO.getPrivilege().getPrivilegeId())));
            }

            RolePrivilege rolePrivilege = DataUtils.copyProperties(rolePrivilegeDTO, RolePrivilege.class);
            rolePrivilegeRepository.save(rolePrivilege);
            rolePrivilegeId = rolePrivilege.getRolePrivilegeId();
        }
        log.info("save end - and returnVal = " + rolePrivilegeId);
        return rolePrivilegeId;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public RolePrivilegeDTO findByRoleAndPrivilege(SearchPrivilegeCriteriaDTO criteria) {
        log.info("findByRoleAndPrivilege() start - and criteria = " + criteria);
        RolePrivilege rolePrivilege = rolePrivilegeRepository.findOne(RolePrivilegePredicate.findByCriteria(criteria));
        RolePrivilegeDTO result = RolePrivilegeDTOAssembler.toDTO(rolePrivilege);
        log.info("findByRoleAndPrivilege end - and returnVal = " + result);
        return result;
    }

}
