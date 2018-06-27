package hk.oro.iefas.ws.casemgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.CaseAccountInfoDTO;
import hk.oro.iefas.domain.casemgt.entity.CaseAccountInfo;
import hk.oro.iefas.domain.shroff.dto.SearchOldCaseAccountCriteriaDTO;
import hk.oro.iefas.ws.bank.repository.CurrencyRepository;
import hk.oro.iefas.ws.casemgt.repository.CaseAccountInfoRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.CaseAccountInfoDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.predicate.CaseAccountInfoPredicate;
import hk.oro.iefas.ws.casemgt.service.CaseAccountService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3174 $ $Date: 2018-06-15 19:54:00 +0800 (週五, 15 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Service
public class CaseAccountServiceImpl implements CaseAccountService {

    @Autowired
    private CaseAccountInfoRepository caseAccountInfoRepository;

    @Autowired
    private CaseAccountInfoDTOAssembler caseAccountInfoDTOAssembler;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    @Transactional(readOnly = true)
    public CaseAccountInfoDTO findOne(Integer caseAccountId) {
        log.info("findOne() start - and caseAccountId = " + caseAccountId);
        CaseAccountInfo caseAccount = caseAccountInfoRepository.findOne(caseAccountId);
        CaseAccountInfoDTO result = caseAccountInfoDTOAssembler.toDTO(caseAccount);
        log.info("findOne end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(CaseAccountInfoDTO caseAccountDTO) {
        log.info("save() start - and CaseAccountInfoDTO = " + caseAccountDTO);
        CaseAccountInfo caseAccount = DataUtils.copyProperties(caseAccountDTO, CaseAccountInfo.class);
        caseAccount.setCurrency(currencyRepository.findOne(caseAccount.getCurrency().getCurcyId()));
        caseAccount = caseAccountInfoRepository.save(caseAccount);
        log.info("save end - and returnVal = " + caseAccount.getCaseAcId());
        return caseAccount.getCaseAcId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CaseAccountInfoDTO> findCaseAccountByCaseId(Integer caseId) {
        log.info("findCaseAccountByCaseId() start - and caseId = " + caseId);
        List<CaseAccountInfo> list = caseAccountInfoRepository.findCaseAccountByCaseId(caseId);
        List<CaseAccountInfoDTO> result = caseAccountInfoDTOAssembler.toDTOList(list);
        log.info("findCaseAccountByCaseId() end - and returnVal = " + result);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public CaseAccountInfoDTO findByAccountNumber(String accountNumber) {
        log.info("findByAccountNumber() start - and AccountNumber = " + accountNumber);
        CaseAccountInfo caseAccountInfo
            = caseAccountInfoRepository.findOne(CaseAccountInfoPredicate.findByAcNumber(accountNumber));
        CaseAccountInfoDTO caseAccountInfoDTO = caseAccountInfoDTOAssembler.toDTO(caseAccountInfo);
        log.info("findByAccountNumber() end - and returnVal = " + caseAccountInfoDTO);
        return caseAccountInfoDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CaseAccountInfoDTO> findOldCaseAccountByAccountType(SearchOldCaseAccountCriteriaDTO criteria) {
        log.info("findOldCaseAccountByAccountType() start - and criteria = " + criteria);
        Iterable<CaseAccountInfo> list = caseAccountInfoRepository.findAll(
            CaseAccountInfoPredicate.findOldAccountByAcType(criteria.getAccountTypeId(), criteria.getCutOffDate()));
        List<CaseAccountInfoDTO> result = caseAccountInfoDTOAssembler.toDTOList(list);
        log.info("findOldCaseAccountByAccountType() end - and returnVal = " + result);
        return result;
    }
}
