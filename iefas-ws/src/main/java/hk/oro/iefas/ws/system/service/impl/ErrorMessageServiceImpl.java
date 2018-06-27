/**
 * 
 */
package hk.oro.iefas.ws.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.system.dto.ErrorMessageDTO;
import hk.oro.iefas.ws.system.repository.ErrorMessageRepository;
import hk.oro.iefas.ws.system.repository.assembler.ErrorMessageDTOAssembler;
import hk.oro.iefas.ws.system.service.ErrorMessageService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class ErrorMessageServiceImpl implements ErrorMessageService {

    @Autowired
    private ErrorMessageRepository errorMessageRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ErrorMessageDTO> findAll() {
        log.info("findAll() start");
        List<ErrorMessageDTO> result = errorMessageRepository.findAll(ErrorMessageDTOAssembler.toDTO(), null);
        log.info("findAll() end - " + result);
        return result;
    }

}
