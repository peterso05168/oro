/**
 * 
 */
package hk.oro.iefas.ws.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.oro.iefas.domain.system.dto.SystemParameterDTO;
import hk.oro.iefas.domain.system.entity.SystemParameter;
import hk.oro.iefas.ws.system.repository.SystemParameterRepository;
import hk.oro.iefas.ws.system.repository.assembler.SystemParameterDTOAssembler;
import hk.oro.iefas.ws.system.repository.predicate.SystemParameterPredicate;
import hk.oro.iefas.ws.system.service.SystemParameterService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class SystemParameterServiceImpl implements SystemParameterService {

    @Autowired
    private SystemParameterRepository systemParameterRepository;

    @Override
    public List<SystemParameterDTO> findAllSystemParameters() {
        log.info("findAll() start");
        List<SystemParameterDTO> result = systemParameterRepository.findAll(SystemParameterDTOAssembler.toDTO(),
            SystemParameterPredicate.findAll());
        log.info("findAll() end - " + result);
        return result;
    }

    @Override
    public String getDateFormat() {
        log.info("getDateFormat() start");
        SystemParameter param = systemParameterRepository.findOne(SystemParameterPredicate.findDateFormat());
        String result = param.getParameterValue();
        log.info("getDateFormat() end - " + result);
        return result;
    }

}
