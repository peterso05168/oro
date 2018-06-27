/**
 * 
 */
package hk.oro.iefas.ws.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.system.dto.SysWfInitialStatusDTO;
import hk.oro.iefas.domain.system.entity.SysWfInitialStatus;
import hk.oro.iefas.ws.system.repository.SysWfInitialStatusRepository;
import hk.oro.iefas.ws.system.repository.assembler.SysWfInitialStatusDTOAssembler;
import hk.oro.iefas.ws.system.service.SysWfInitialStatusService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class SysWfInitialStatusServiceImpl implements SysWfInitialStatusService {

    @Autowired
    private SysWfInitialStatusRepository sysWfInitialStatusRepository;

    @Autowired
    private SysWfInitialStatusDTOAssembler sysWfInitialStatusDTOAssembler;

    @Transactional(readOnly = true)
    @Override
    public SysWfInitialStatusDTO findByPrivilegeCode(String privilegeCode) {
        log.info("findByPrivilegeId() start - PrivilegeCode: " + privilegeCode);
        SysWfInitialStatus sysWfInitialStatus
            = sysWfInitialStatusRepository.findByPrivilegePrivilegeCode(privilegeCode);
        SysWfInitialStatusDTO sysWfInitialStatusDTO = sysWfInitialStatusDTOAssembler.toDTO(sysWfInitialStatus);
        log.info("findByPrivilegeId() end - " + sysWfInitialStatusDTO);
        return sysWfInitialStatusDTO;
    }

}
