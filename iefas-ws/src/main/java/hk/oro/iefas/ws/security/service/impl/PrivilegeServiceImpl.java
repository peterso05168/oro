package hk.oro.iefas.ws.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.security.dto.MenuPrivilegeDTO;
import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import hk.oro.iefas.domain.security.dto.ReportPrivilegeDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeResultDTO;
import hk.oro.iefas.domain.security.entity.Privilege;
import hk.oro.iefas.ws.common.util.CommonDataUtils;
import hk.oro.iefas.ws.security.repository.MenuPrivilegeRepository;
import hk.oro.iefas.ws.security.repository.PrivilegeRepository;
import hk.oro.iefas.ws.security.repository.ReportPrivilegeRepository;
import hk.oro.iefas.ws.security.repository.assembler.MenuPrivilegeDTOAssembler;
import hk.oro.iefas.ws.security.repository.assembler.PrivilegeDTOAssembler;
import hk.oro.iefas.ws.security.repository.assembler.ReportPrivilegeDTOAssembler;
import hk.oro.iefas.ws.security.service.PrivilegeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private MenuPrivilegeRepository menuPrivilegeRepository;

    @Autowired
    private ReportPrivilegeRepository reportPrivilegeRepository;

    @Override
    public Integer save(PrivilegeDTO privilegeDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PrivilegeDTO findOne(Integer privilegeId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PrivilegeDTO> findByPostId(Integer postId) {
        log.info("findByPost() start = PostId: " + postId);
        List<PrivilegeDTO> result = privilegeRepository.findByPostId(postId);
        log.info("findByPost end - " + result);
        return result;
    }

    @Override
    public List<SearchPrivilegeResultDTO> findAll() {
        log.info("findByCriteria() start - ");
        List<Privilege> privileges = privilegeRepository.findAll();
        List<PrivilegeDTO> list = PrivilegeDTOAssembler.toDTOList(privileges);
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
                resultList.add(dto);
            }
        }
        return resultList;
    }
}
