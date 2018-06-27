package hk.oro.iefas.ws.dividend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.adjudication.entity.CredType;
import hk.oro.iefas.domain.common.dto.CaseTypeDTO;
import hk.oro.iefas.domain.dividend.dto.CaseFeeTypeDTO;
import hk.oro.iefas.domain.dividend.dto.CreditorTypeDTO;
import hk.oro.iefas.domain.dividend.entity.CaseFeeType;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.adjudication.repository.CredTypeRepository;
import hk.oro.iefas.ws.dividend.repository.CaseFeeTypeRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.CaseFeeTypeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.CreditorTypeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.predicate.CaseFeeTypePredicate;
import hk.oro.iefas.ws.dividend.service.CommonDividendService;
import lombok.extern.log4j.Log4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Log4j
@Service
public class CommonDividendServiceImpl implements CommonDividendService {

    @Autowired
    private CaseFeeTypeRepository caseFeeTypeRepository;

    @Autowired
    private CaseFeeTypeDTOAssembler caseFeeTypeDTOAssembler;

    @Autowired
    private CreditorTypeDTOAssembler creditorTypeDTOAssembler;

    @Autowired
    private CredTypeRepository credTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<CaseFeeTypeDTO> searchORFeeItemList(SearchDTO<CaseTypeDTO> searchDTO) {
        log.info("searchORFeeItemList() start - and param: " + searchDTO);
        Page<CaseFeeTypeDTO> results = null;
        if (searchDTO != null) {
            Pageable pageable = null;
            if (searchDTO.getPage() != null) {
                pageable = searchDTO.getPage().toPageable();
            }

            if (searchDTO.getCriteria() != null) {
                Page<CaseFeeType> page = caseFeeTypeRepository
                    .findAll(CaseFeeTypePredicate.findByCriteria(searchDTO.getCriteria()), pageable);
                if (page != null) {
                    results = caseFeeTypeDTOAssembler.toResultDTO(page);
                }
            }

        }

        log.info("searchORFeeItemList() end - return : " + results);
        return results;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CreditorTypeDTO> searchCreditorType() {
        log.info("searchCreditorType() start -");
        List<CredType> credTypes
            = credTypeRepository.findByStatusIgnoreCaseOrderByCreditorTypeName(CoreConstant.STATUS_ACTIVE);
        List<CreditorTypeDTO> list = creditorTypeDTOAssembler.toDTOList(credTypes);
        log.info("searchCreditorType() end - and return: " + list);
        return list;
    }

}
