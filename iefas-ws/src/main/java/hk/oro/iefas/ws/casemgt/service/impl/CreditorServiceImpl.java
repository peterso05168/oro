package hk.oro.iefas.ws.casemgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.casemgt.entity.CaseCred;
import hk.oro.iefas.ws.casemgt.repository.CaseCredRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.CreditorDTOAssembler;
import hk.oro.iefas.ws.casemgt.service.CreditorService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Service
public class CreditorServiceImpl implements CreditorService {

    @Autowired
    private CaseCredRepository creditorRepository;

    @Autowired
    private CreditorDTOAssembler creditorDTOAssembler;

    @Override
    @Transactional(readOnly = true)
    public List<CreditorDTO> findCreditorByCaseNumber(String caseNumber) {
        log.info("findCreditorByCaseNumber() start - and caseNumber = " + caseNumber);
        List<CaseCred> list = creditorRepository.findCreditorByCaseNumber(caseNumber);
        List<CreditorDTO> result = creditorDTOAssembler.toDTOList(list);
        log.info("findCreditorByCaseNumber() end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public CreditorDTO findCreditorById(Integer creditorId) {
        log.info("findCreditorById() start - and creditorId = " + creditorId);
        CaseCred creditor = creditorRepository.findOne(creditorId);
        CreditorDTO result = creditorDTOAssembler.toDTO(creditor);
        log.info("findCreditorById() end - and returnVal = " + result);
        return result;
    }
}
