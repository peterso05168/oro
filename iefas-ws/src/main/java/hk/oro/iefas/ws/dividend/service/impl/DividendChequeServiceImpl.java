package hk.oro.iefas.ws.dividend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.bank.entity.Currency;
import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.dividend.dto.DividendChequeDTO;
import hk.oro.iefas.domain.dividend.dto.SearchDividendChequeDTO;
import hk.oro.iefas.domain.dividend.entity.DividendCheque;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.ChequeDTO;
import hk.oro.iefas.domain.shroff.entity.ShrCheque;
import hk.oro.iefas.domain.shroff.entity.ShrChequeType;
import hk.oro.iefas.ws.bank.repository.CurrencyRepository;
import hk.oro.iefas.ws.bank.repository.predicate.CurrencyPredicate;
import hk.oro.iefas.ws.casemgt.repository.CaseRepository;
import hk.oro.iefas.ws.dividend.repository.DividendChequeRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.DividendChequeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.predicate.DividendChequePredicate;
import hk.oro.iefas.ws.dividend.service.DividendChequeService;
import hk.oro.iefas.ws.shroff.repository.ChequeRepository;
import hk.oro.iefas.ws.shroff.repository.ShrChequeTypeRepository;
import hk.oro.iefas.ws.shroff.repository.predicate.ShrChequeTypePredicate;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3021 $ $Date: 2018-06-10 16:06:34 +0800 (Sun, 10 Jun 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Service
public class DividendChequeServiceImpl implements DividendChequeService {
    @Autowired
    private DividendChequeDTOAssembler dividendChequeDTOAssembler;

    @Autowired
    private DividendChequeRepository dividendChequeRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private ChequeRepository chequeRepository;

    @Autowired
    private ShrChequeTypeRepository shrChequeTypeRepository;

    @Autowired
    private CaseRepository caseRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<DividendChequeDTO> searchDividendChequeList(SearchDTO<SearchDividendChequeDTO> criteriaDTO) {
        log.info("searchDividendChequeList start criteriaDTO : " + criteriaDTO);
        SearchDividendChequeDTO criteria = criteriaDTO.getCriteria();
        Pageable pageable = criteriaDTO.getPage().toPageable();
        Page<DividendCheque> page
            = dividendChequeRepository.findAll(DividendChequePredicate.findByCriteria(criteria), pageable);
        Page<DividendChequeDTO> resultDTO = dividendChequeDTOAssembler.toResultDTO(page);
        log.info("searchPreAdjudicationList() end and return : " + resultDTO);
        return resultDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public DividendChequeDTO searchDividendCheque(Integer dividendChequeId) {
        log.info("searchDividendCheque start dividendChequeId : " + dividendChequeId);
        DividendCheque dividendCheque = dividendChequeRepository.findOne(dividendChequeId);
        DividendChequeDTO dto = dividendChequeDTOAssembler.toDTO(dividendCheque);
        log.info("searchDividendCheque end return : " + dto);
        return dto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveDividendCheque(DividendChequeDTO dividendChequeDTO) {
        log.info("saveDividendCheque start dividendChequeDTO : " + dividendChequeDTO);
        DividendCheque entity = DataUtils.copyProperties(dividendChequeDTO, DividendCheque.class);
        entity.setShrCheque(chequeRepository.findOne(dividendChequeDTO.getCheque().getChequeId()));
        entity.setDivScheduleItemId(dividendChequeDTO.getDividendScheduleItem().getDividendScheduleItemId());
        // update module
        if (dividendChequeDTO.getDividendChequeId() != null && dividendChequeDTO.getDividendChequeId().intValue() > 0) {
            // entity = dividendChequeRepository.findOne(dividendChequeDTO.getDividendChequeId());
            // create module
        } else {

        }
        entity = dividendChequeRepository.save(entity);
        log.info("saveDividendCheque end return : " + entity.getDividendChequeId());
        return entity.getDividendChequeId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveDividendChequeList(List<DividendChequeDTO> dividendChequeList) {
        log.info("saveDividendCheque start  and dividendChequeList : " + dividendChequeList);
        boolean result = true;
        // create
        final String CURYCODE = "HKD";
        final String CHEQUETYPE = "Dividend Cheque";
        Currency currency = currencyRepository.findOne(CurrencyPredicate.findByCuryCode(CURYCODE));
        ShrChequeType shrChequeType
            = shrChequeTypeRepository.findOne(ShrChequeTypePredicate.findByChequeTypeDesc(CHEQUETYPE));
        for (DividendChequeDTO dividendCheque : dividendChequeList) {
            ChequeDTO chequeDTO = dividendCheque.getCheque();
            if (chequeDTO != null) {
                Integer chequeNo = chequeDTO.getChequeNo();
                if (chequeNo != null) {
                    ShrCheque shrCheque = DataUtils.copyProperties(chequeDTO, ShrCheque.class);
                    shrCheque.setCurcy(currency);
                    CreditorDTO creditorDTO = dividendCheque.getDividendScheduleItem().getCreditor();
                    if (creditorDTO != null) {
                        Integer caseId = creditorDTO.getCaseInfo().getCaseId();
                        Case caseInfo = caseRepository.findOne(caseId);
                        shrCheque.setCaseInfo(caseInfo);
                    }
                    if (shrChequeType != null) {
                        Long chequeTypeId = shrChequeType.getChequeTypeId();
                        shrCheque.setChequeTypeId(chequeTypeId.intValue());
                    }
                    shrCheque = chequeRepository.save(shrCheque);
                    DividendCheque entity = DataUtils.copyProperties(dividendCheque, DividendCheque.class);
                    entity.setShrCheque(shrCheque);
                    entity.setDivScheduleItemId(dividendCheque.getDividendScheduleItem().getDividendScheduleItemId());
                    Integer dividendChequeId = dividendChequeRepository.save(entity).getDividendChequeId();
                    result = result && dividendChequeId > 0;
                }
            }
        }
        log.info("saveDividendCheque end return : result =" + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DividendChequeDTO> searchDividendChequeList(List<Integer> divScheItemIdList) {
        log.info("searchDividendChequeList() start - and divScheItemIdList =" + divScheItemIdList);
        List<DividendChequeDTO> result = null;
        if (divScheItemIdList != null && !divScheItemIdList.isEmpty()) {
            result = new ArrayList<>();
            for (Integer divScheduleId : divScheItemIdList) {
                DividendCheque dividendCheque
                    = dividendChequeRepository.findOne(DividendChequePredicate.findByDivScheduleItemId(divScheduleId));
                if (dividendCheque != null) {
                    result.add(dividendChequeDTOAssembler.toDTO(dividendCheque));
                }
            }
        }
        log.info("searchDividendChequeList() end - and return: result =" + result);
        return result;
    }
}
