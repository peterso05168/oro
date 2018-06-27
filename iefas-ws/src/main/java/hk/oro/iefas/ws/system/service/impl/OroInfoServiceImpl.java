/**
 * 
 */
package hk.oro.iefas.ws.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.oro.iefas.domain.system.dto.OroInfoDTO;
import hk.oro.iefas.ws.system.repository.OroInfoRepository;
import hk.oro.iefas.ws.system.repository.assembler.OroInformationDTOAssembler;
import hk.oro.iefas.ws.system.repository.predicate.OroInformationPredicate;
import hk.oro.iefas.ws.system.service.OroInfoService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class OroInfoServiceImpl implements OroInfoService {

    @Autowired
    private OroInfoRepository oroInfoRepository;

    @Override
    public List<OroInfoDTO> loadOroInformation() {
        log.info("loadOroInformation() start");
        List<OroInfoDTO> result
            = oroInfoRepository.findAll(OroInformationDTOAssembler.toDTO(), OroInformationPredicate.findAll());
        log.info("loadOroInformation() end - " + result);
        return result;
    }

}
