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
import hk.oro.iefas.domain.security.dto.DivisionPrivilegeDTO;
import hk.oro.iefas.domain.security.dto.MenuPrivilegeDTO;
import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import hk.oro.iefas.domain.security.dto.ReportPrivilegeDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeCriteriaDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeResultDTO;
import hk.oro.iefas.domain.security.entity.DivisionPrivilege;
import hk.oro.iefas.ws.common.util.CommonDataUtils;
import hk.oro.iefas.ws.organization.repository.DivisionRepository;
import hk.oro.iefas.ws.organization.repository.assembler.DivisionDTOAssembler;
import hk.oro.iefas.ws.security.repository.DivisionPrivilegeRepository;
import hk.oro.iefas.ws.security.repository.MenuPrivilegeRepository;
import hk.oro.iefas.ws.security.repository.PrivilegeRepository;
import hk.oro.iefas.ws.security.repository.ReportPrivilegeRepository;
import hk.oro.iefas.ws.security.repository.assembler.DivisionPrivilegeDTOAssembler;
import hk.oro.iefas.ws.security.repository.assembler.MenuPrivilegeDTOAssembler;
import hk.oro.iefas.ws.security.repository.assembler.PrivilegeDTOAssembler;
import hk.oro.iefas.ws.security.repository.assembler.ReportPrivilegeDTOAssembler;
import hk.oro.iefas.ws.security.repository.predicate.DivisionPrivilegePredicate;
import hk.oro.iefas.ws.security.service.DivisionPrivilegeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class DivisionPrivilegeServiceImpl implements DivisionPrivilegeService {

    @Autowired
    private DivisionPrivilegeRepository divisionPrivilegeRepository;

    @Autowired
    private MenuPrivilegeRepository menuPrivilegeRepository;

    @Autowired
    private ReportPrivilegeRepository reportPrivilegeRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SearchPrivilegeResultDTO> findByCriteria(SearchPrivilegeCriteriaDTO criteria) {
        log.info("findByCriteria() start - " + criteria);
        List<PrivilegeDTO> list = divisionPrivilegeRepository.findAll(DivisionPrivilegeDTOAssembler.toResultDTO(),
            DivisionPrivilegePredicate.findByCriteria(criteria));
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
                dto.setDivisionId(criteria.getDivisionId());
                dto.setMenuName(CommonDataUtils.genMenuName(menuPrivileges));
                dto.setReportName(CommonDataUtils.genReportName(reportPrivileges));
                resultList.add(dto);
            }
        }
        return resultList;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public DivisionPrivilegeDTO findByDivisionAndPrivilege(SearchPrivilegeCriteriaDTO criteria) {
        log.info("findByDivisionAndPrivilege() start - and criteria = " + criteria);
        DivisionPrivilege divisionPrivilege
            = divisionPrivilegeRepository.findOne(DivisionPrivilegePredicate.findByDivisionAndPrivilege(criteria));
        DivisionPrivilegeDTO result = DivisionPrivilegeDTOAssembler.toDTO(divisionPrivilege);
        log.info("findByDivisionAndPrivilege end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(DivisionPrivilegeDTO divisionPrivilegeDTO) {
        log.info("save() start - and divisionPrivilegeDTO = " + divisionPrivilegeDTO);
        Integer divisionPrivilegeId;
        if (divisionPrivilegeDTO.getDivisionPrivilegeId() != null
            && divisionPrivilegeDTO.getDivisionPrivilegeId() != 0) {
            DivisionPrivilege divisionPrivilege
                = DataUtils.copyProperties(divisionPrivilegeDTO, DivisionPrivilege.class);
            divisionPrivilegeRepository.save(divisionPrivilege);
            divisionPrivilegeId = divisionPrivilege.getDivisionPrivilegeId();
        } else {
            divisionPrivilegeDTO.setStatus(CoreConstant.STATUS_ACTIVE);
            if (divisionPrivilegeDTO.getDivision().getDivisionId() != null
                && divisionPrivilegeDTO.getDivision().getDivisionId() != 0) {
                divisionPrivilegeDTO.setDivision(DivisionDTOAssembler
                    .toDTO(divisionRepository.findOne(divisionPrivilegeDTO.getDivision().getDivisionId())));
            }
            if (divisionPrivilegeDTO.getPrivilege().getPrivilegeId() != null
                && divisionPrivilegeDTO.getPrivilege().getPrivilegeId() != 0) {
                divisionPrivilegeDTO.setPrivilege(PrivilegeDTOAssembler
                    .toDTO(privilegeRepository.findOne(divisionPrivilegeDTO.getPrivilege().getPrivilegeId())));
            }

            DivisionPrivilege divisionPrivilege
                = DataUtils.copyProperties(divisionPrivilegeDTO, DivisionPrivilege.class);
            divisionPrivilegeRepository.save(divisionPrivilege);
            divisionPrivilegeId = divisionPrivilege.getDivisionPrivilegeId();
        }
        log.info("save end - and returnVal = " + divisionPrivilegeId);
        return divisionPrivilegeId;
    }

}
