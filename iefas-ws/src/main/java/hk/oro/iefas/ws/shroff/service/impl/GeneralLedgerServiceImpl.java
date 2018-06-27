package hk.oro.iefas.ws.shroff.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.shroff.dto.GeneralLedgerDTO;
import hk.oro.iefas.domain.shroff.entity.ShrGeneralLedger;
import hk.oro.iefas.ws.shroff.repository.GeneralLedgerRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.GeneralLedgerDTOAssembler;
import hk.oro.iefas.ws.shroff.service.GeneralLedgerService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @version $Revision$ $Date$
 * @author $Author$
 */
@Slf4j
@Service
public class GeneralLedgerServiceImpl implements GeneralLedgerService {
    
    @Autowired
    private GeneralLedgerRepository generalLedgerRepository;
    
    @Autowired
    private GeneralLedgerDTOAssembler generalLedgerDTOAssembler;

    @Override
    public Integer saveGeneralLedger(GeneralLedgerDTO generalLedger) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public GeneralLedgerDTO findGeneralLedgerByControlAcId(Integer controlAccountId) {
        log.info("findGeneralLedger() start - controlAccountId: " + controlAccountId);
        
        ShrGeneralLedger shrgeneralLedger = generalLedgerRepository.findBycontrolAcId(controlAccountId);
        GeneralLedgerDTO generalLedgerDTO = generalLedgerDTOAssembler.toDTO(shrgeneralLedger);
        
        log.info("findGeneralLedger() end - GeneralLedgerDTO: " + generalLedgerDTO);
        return generalLedgerDTO;
    }

//    @Transactional(readOnly = true)
//    @Override
//    public GeneralLedgerDTO findGeneralLedger(BigDecimal generalLedgerId) {
//        log.info("findGeneralLedger() start - generalLedgerId: " + generalLedgerId);
//        
//        ShrGeneralLedger shrgeneralLedger = generalLedgerRepository.findOne(generalLedgerId);
//        GeneralLedgerDTO generalLedgerDTO = generalLedgerDTOAssembler.toDTO(shrgeneralLedger);
//        
//        log.info("findGeneralLedger() end - GeneralLedgerDTO: " + generalLedgerDTO);
//        return generalLedgerDTO;
//    }

}
