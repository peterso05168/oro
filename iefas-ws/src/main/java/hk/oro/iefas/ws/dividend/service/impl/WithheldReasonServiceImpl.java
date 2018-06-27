package hk.oro.iefas.ws.dividend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.dividend.dto.SearchWithheldReasonDTO;
import hk.oro.iefas.domain.dividend.dto.WithheldReasonDTO;
import hk.oro.iefas.domain.dividend.entity.DivWithheldReasonType;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.dividend.repository.WithheldReasonRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.WithheldReasonDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.predicate.WithheldReasonPredicate;
import hk.oro.iefas.ws.dividend.service.WithheldReasonService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3087 $ $Date: 2018-06-12 18:02:20 +0800 (週二, 12 六月 2018) $
 * @author $Author: noah.liang $
 */

@Slf4j
@Service
public class WithheldReasonServiceImpl implements WithheldReasonService {

    @Autowired
    private WithheldReasonRepository withheldReasonRepository;

    @Autowired
    private WithheldReasonDTOAssembler withheldReasonAssembler;

    @Override
    @Transactional(readOnly = true)
    public Page<WithheldReasonDTO> searchWithheldReason(SearchDTO<SearchWithheldReasonDTO> withheldReasonCriteria) {
        log.info("searchWithheldReason - start searchWithheldReasonDTO =" + withheldReasonCriteria);
        Page<WithheldReasonDTO> result = null;
        if (withheldReasonCriteria != null) {
            Pageable pageable = null;
            if (withheldReasonCriteria.getPage() != null) {
                String sortField = withheldReasonCriteria.getPage().getSortField();
                if (sortField == null) {
                    withheldReasonCriteria.getPage().setSortField("withheldRsnCode");
                }
                pageable = withheldReasonCriteria.getPage().toPageable();
            }
            if (withheldReasonCriteria.getCriteria() != null) {
                Page<DivWithheldReasonType> page = withheldReasonRepository
                    .findAll(WithheldReasonPredicate.findByCriteria(withheldReasonCriteria.getCriteria()), pageable);
                if (page != null) {
                    result = withheldReasonAssembler.toResultDTO(page);
                }
            }
        }
        log.info("searchWithheldReason - end result =" + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveWithheldReason(WithheldReasonDTO withheldReason) {
        log.info("saveWithheldReason - start WithheldReasonDTO =" + withheldReason);
        DivWithheldReasonType divWithheldReasonType = null;
        if (withheldReason.getWithheldReasonId() != null && withheldReason.getWithheldReasonId() > 0) {
            divWithheldReasonType = withheldReasonRepository.findOne(withheldReason.getWithheldReasonId());
        } else {
            divWithheldReasonType = new DivWithheldReasonType();
        }
        divWithheldReasonType.setWithheldRsnCode(withheldReason.getWithheldReasonCode());
        divWithheldReasonType.setWithheldRsnDesc(withheldReason.getWithheldReasonDescription());
        divWithheldReasonType.setWithheldRsnDescChi(withheldReason.getWithheldReasonDescriptionChinese());
        divWithheldReasonType.setStatus(withheldReason.getStatus());
        Integer result = withheldReasonRepository.save(divWithheldReasonType).getWithheldRsnTypeId();
        log.info("saveWithheldReason - end and result = " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public WithheldReasonDTO searchByWithheldReasonId(Integer withheldReasonId) {
        log.info("searchByWithheldReasonId - start");
        WithheldReasonDTO withheldReasonDTO = null;
        if (withheldReasonId != null && withheldReasonId > 0) {
            DivWithheldReasonType divWithheldReasonType = withheldReasonRepository.findOne(withheldReasonId);
            if (divWithheldReasonType != null) {
                withheldReasonDTO = withheldReasonAssembler.toDTO(divWithheldReasonType);
            }
        }
        log.info("searchByWithheldReasonId - end and return =" + withheldReasonDTO);
        return withheldReasonDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WithheldReasonDTO> searchWithheldReasonList() {
        log.info("searchWithheldReasonList - start");
        List<DivWithheldReasonType> divWithheldReasonTypeList
            = (List<DivWithheldReasonType>)withheldReasonRepository.findAll(WithheldReasonPredicate.findByStatus());
        List<WithheldReasonDTO> result = withheldReasonAssembler.toDTOList(divWithheldReasonTypeList);
        log.info("searchWithheldReasonList - end and return =" + result);
        return result;
    }

};