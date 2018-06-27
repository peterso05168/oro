/**
 * 
 */
package hk.oro.iefas.ws.shroff.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.shroff.dto.JournalTypeDTO;
import hk.oro.iefas.domain.shroff.entity.JournalType;
import hk.oro.iefas.ws.shroff.repository.JournalTypeRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.JournalTypeDTOAssembler;
import hk.oro.iefas.ws.shroff.service.JournalTypeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Service
public class JournalTypeServiceImpl implements JournalTypeService {

    @Autowired
    private JournalTypeRepository journalTypeRepository;

    @Autowired
    private JournalTypeDTOAssembler journalTypeDTOAssembler;

    @Transactional(readOnly = true)
    @Override
    public List<JournalTypeDTO> findAll() {
        log.info("findAll() start");
        List<JournalType> all = journalTypeRepository.findAll();
        List<JournalTypeDTO> dtoList = journalTypeDTOAssembler.toDTOList(all);
        log.info("findAll() end - " + dtoList);
        return dtoList;
    }

}
