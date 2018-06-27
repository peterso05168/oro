package hk.oro.iefas.ws.casemgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.casemgt.dto.CommonCreditorSectionDTO;
import hk.oro.iefas.domain.casemgt.entity.CommonCreditorSection;
import hk.oro.iefas.ws.casemgt.repository.CommonCreditorSectionRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.CommonCreditorSectionDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.predicate.CommonCreditorSectionPredicate;
import hk.oro.iefas.ws.casemgt.service.CommonCreditorSectionService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class CommonCreditorSectionServiceImpl implements CommonCreditorSectionService {

    @Autowired
    private CommonCreditorSectionRepository commonCreditorSectionRepository;

    @Autowired
    private CommonCreditorSectionDTOAssembler commonCreditorSectionDTOAssembler;

    @Override
    @Transactional(readOnly = true)
    public List<CommonCreditorSectionDTO> searchCommonCreditorSectionByName(String commonCreditorSectionName) {
        log.info("searchCommonCreditor() start - commonCreditorSectionName: " + commonCreditorSectionName);
        Iterable<CommonCreditorSection> commonCreditorSection = commonCreditorSectionRepository
            .findAll(CommonCreditorSectionPredicate.findBySectionName(commonCreditorSectionName));
        List<CommonCreditorSectionDTO> dto = commonCreditorSectionDTOAssembler.toDTOList(commonCreditorSection);
        log.info("searchCommonCreditor() end - " + dto);
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommonCreditorSectionDTO> searchCommonCreditorSectionBySeqNo(String commonCreditorSectionSeqNo) {
        log.info(
            "searchCommonCreditorSectionBySeqNo() start - commonCreditorSectionSeqNo: " + commonCreditorSectionSeqNo);
        Iterable<CommonCreditorSection> commonCreditorSection = commonCreditorSectionRepository
            .findAll(CommonCreditorSectionPredicate.findBySectionSeqNo(commonCreditorSectionSeqNo));
        List<CommonCreditorSectionDTO> dto = commonCreditorSectionDTOAssembler.toDTOList(commonCreditorSection);
        log.info("searchCommonCreditorSectionBySeqNo() end - " + dto);
        return dto;
    }
}
