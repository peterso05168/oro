package hk.oro.iefas.ws.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.bank.dto.CurrencyBasicInfoDTO;
import hk.oro.iefas.domain.bank.dto.CurrencyDTO;
import hk.oro.iefas.domain.bank.dto.CurrencyRateDTO;
import hk.oro.iefas.domain.bank.dto.CurrencyResultDTO;
import hk.oro.iefas.domain.bank.dto.CurrencySearchDTO;
import hk.oro.iefas.domain.bank.entity.Currency;
import hk.oro.iefas.domain.bank.entity.CurrencyRate;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.bank.repository.CurrencyRateRepository;
import hk.oro.iefas.ws.bank.repository.CurrencyRepository;
import hk.oro.iefas.ws.bank.repository.assembler.CurrencyDTOAssembler;
import hk.oro.iefas.ws.bank.repository.predicate.CurrencyPredicate;
import hk.oro.iefas.ws.bank.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCurcyCodeAndCurcyIdNot(String curcyCode, Integer curcyId) {
        log.info("existsByCurcyCodeAndCurcyIdNot() start - curcyCode: " + curcyCode + ", curcyId: " + curcyId);
        boolean exists = currencyRepository.existsByCurcyCodeIgnoreCaseAndCurcyIdNot(curcyCode, curcyId);
        log.info("existsByCurcyCodeAndCurcyIdNot() end - exists: " + exists);
        return exists;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CurrencyResultDTO> findByCriteria(SearchDTO<CurrencySearchDTO> searchDTO) {
        log.info("findByCriteria() start - " + searchDTO);
        Pageable pageable = searchDTO.getPage().toPageable();

        Page<Currency> page
            = currencyRepository.findAll(CurrencyPredicate.findByCriteria(searchDTO.getCriteria()), pageable);
        Page<CurrencyResultDTO> results = CurrencyDTOAssembler.toResultDTO(page);

        log.info("findByCriteria() end - " + results);
        return results;
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyDTO findOne(Integer curcyId) {
        log.info("findOne() start - curcyId: " + curcyId);
        Currency currency = currencyRepository.findOne(curcyId);
        CurrencyDTO dto = CurrencyDTOAssembler.toDTO(currency);
        log.info("findOne() end - " + dto);
        return dto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(CurrencyDTO currencyDTO) {
        log.info("save() start - " + currencyDTO);

        Currency entity = DataUtils.copyProperties(currencyDTO, Currency.class);
        currencyRepository.save(entity);

        List<CurrencyRateDTO> currencyRates = currencyDTO.getCurrencyRates();
        if (CommonUtils.isNotEmpty(currencyRates)) {
            for (CurrencyRateDTO cr : currencyRates) {
                cr.setCurcyId(entity.getCurcyId());
                if (cr.getCurcyRateId() != null && cr.getCurcyRateId().intValue() > 0) {
                    CurrencyRate currencyRate = DataUtils.copyProperties(cr, CurrencyRate.class);
                    currencyRateRepository.save(currencyRate);
                } else {
                    if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(cr.getStatus())) {
                        CurrencyRate currencyRate = DataUtils.copyProperties(cr, CurrencyRate.class);
                        currencyRateRepository.save(currencyRate);
                    }
                }
            }
        }

        log.info("save() end");
        return entity.getCurcyId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyBasicInfoDTO> findAll() {
        log.info("findAll() start - ");
        List<Currency> currencies = (List<Currency>)currencyRepository.findAll(CurrencyPredicate.findAll(),
            CurrencyPredicate.sortByCurrencyName());
        List<CurrencyBasicInfoDTO> currencyBasicInfoDTOs = CurrencyDTOAssembler.toDTOs(currencies);
        log.info("findAll() end - " + currencyBasicInfoDTOs);
        return currencyBasicInfoDTOs;
    }

}
