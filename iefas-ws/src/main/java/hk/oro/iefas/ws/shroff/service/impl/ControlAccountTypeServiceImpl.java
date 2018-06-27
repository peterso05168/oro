package hk.oro.iefas.ws.shroff.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.oro.iefas.domain.shroff.dto.ControlAccountTypeDTO;
import hk.oro.iefas.domain.shroff.entity.ShrCtlAcType;
import hk.oro.iefas.ws.shroff.repository.ControlAccountTypeRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.ControlAccountTypeDTOAssembler;
import hk.oro.iefas.ws.shroff.service.ControlAccountTypeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Service
@Slf4j
public class ControlAccountTypeServiceImpl implements ControlAccountTypeService {

    @Autowired
    private ControlAccountTypeRepository controlAccountTypeRepository;

    @Autowired
    private ControlAccountTypeDTOAssembler controlAccountTypeDTOAssembler;

    @Override
    @Transactional(readOnly = true)
    public List<ControlAccountTypeDTO> findAll() {
        log.info("findAll() start");
        List<ControlAccountTypeDTO> controlAccountTypeDTOS = null;
        List<ShrCtlAcType> shrCtlAcTypes = controlAccountTypeRepository.findAll();
        if (shrCtlAcTypes != null) {
            controlAccountTypeDTOS = controlAccountTypeDTOAssembler.toDTOList(shrCtlAcTypes);
        }
        log.info("findAll() end");
        return controlAccountTypeDTOS;
    }
}
