/**
 * 
 */
package hk.oro.iefas.ws.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.system.dto.ErrorMessageParamDTO;
import hk.oro.iefas.ws.system.repository.ErrorMessageParamRepository;
import hk.oro.iefas.ws.system.repository.assembler.ErrorMessageParamDTOAssembler;
import hk.oro.iefas.ws.system.service.ErrorMessageParamService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class ErrorMessageParamServiceImpl implements ErrorMessageParamService {

    @Autowired
    private ErrorMessageParamRepository errorMessageParamRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ErrorMessageParamDTO> findAll() {
        log.info("findAll() start");
        List<ErrorMessageParamDTO> result
            = errorMessageParamRepository.findAll(ErrorMessageParamDTOAssembler.toDTO(), null);
        log.info("findAll() end - " + result);
        return result;
    }

}
