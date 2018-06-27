package hk.oro.iefas.ws.bank.service.impl;

import java.math.BigDecimal;
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
import hk.oro.iefas.domain.bank.dto.BankAccountInfoResultDTO;
import hk.oro.iefas.domain.bank.dto.BankInfoDTO;
import hk.oro.iefas.domain.bank.dto.FreeBankTransferDTO;
import hk.oro.iefas.domain.bank.dto.LeapYearParameterDTO;
import hk.oro.iefas.domain.bank.dto.SearchBankAccountInfoCriteriaDTO;
import hk.oro.iefas.domain.bank.entity.BankDeposit;
import hk.oro.iefas.domain.bank.entity.BankInfo;
import hk.oro.iefas.domain.bank.entity.FreeBankTransfer;
import hk.oro.iefas.domain.bank.entity.LeapYearParameter;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.bank.repository.BankDepositRepository;
import hk.oro.iefas.ws.bank.repository.BankInfoRepository;
import hk.oro.iefas.ws.bank.repository.CurrencyRepository;
import hk.oro.iefas.ws.bank.repository.FreeBankTransferRepository;
import hk.oro.iefas.ws.bank.repository.LeapYearParameterRepository;
import hk.oro.iefas.ws.bank.repository.assembler.BankInfoDTOAssembler;
import hk.oro.iefas.ws.bank.repository.predicate.BankAccountPredicate;
import hk.oro.iefas.ws.bank.repository.predicate.BankInfoPredicate;
import hk.oro.iefas.ws.bank.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Autowired
    private BankDepositRepository bankDepositRepository;

    @Autowired
    private LeapYearParameterRepository leapYearParameterRepository;

    @Autowired
    private FreeBankTransferRepository freeBankTransferRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<BankAccountInfoResultDTO> findByCriteria(SearchDTO<SearchBankAccountInfoCriteriaDTO> criteriaDTO) {
        log.info("findByCriteria() start - " + criteriaDTO);
        Pageable pageable = criteriaDTO.getPage().toPageable();

        Page<BankInfo> page
            = bankInfoRepository.findAll(BankAccountPredicate.findByCriteria(criteriaDTO.getCriteria()), pageable);
        Page<BankAccountInfoResultDTO> results = BankInfoDTOAssembler.toResultDTO(page);

        log.info("findByCriteria() end - " + results);
        return results;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(BankInfoDTO bankInfoDTO) {
        log.info("save() start - " + bankInfoDTO);
        Integer bankInfoId;
        // update
        if (bankInfoDTO.getBankInfoId() != null && bankInfoDTO.getBankInfoId().intValue() > 0) {
            BankInfo entity = DataUtils.copyProperties(bankInfoDTO, BankInfo.class);
            bankInfoRepository.save(entity);
            bankInfoId = bankInfoDTO.getBankInfoId();
            // save
        } else {
            bankInfoDTO.setStatus(CoreConstant.STATUS_ACTIVE);
            BankInfo entity = DataUtils.copyProperties(bankInfoDTO, BankInfo.class);
            bankInfoRepository.save(entity);
            bankInfoId = entity.getBankInfoId();

            // add default bank deposit
            BankDeposit bankDeposit = new BankDeposit();
            bankDeposit.setBankInfo(entity);
            bankDeposit.setStatus(CoreConstant.STATUS_ACTIVE);
            bankDeposit.setBankInvestAmount(new BigDecimal(0));
            bankDepositRepository.save(bankDeposit);
        }

        List<LeapYearParameterDTO> leapYearParameters = bankInfoDTO.getLeapYearParameters();
        if (CommonUtils.isNotEmpty(leapYearParameters)) {
            for (LeapYearParameterDTO leapYearParameterDTO : leapYearParameters) {
                if (leapYearParameterDTO.getLeapYearCalculationId() != null
                    && leapYearParameterDTO.getLeapYearCalculationId().intValue() > 0) {
                    // update
                    LeapYearParameter leapYearParameter
                        = DataUtils.copyProperties(leapYearParameterDTO, LeapYearParameter.class);
                    leapYearParameter.setBankInfoId(bankInfoId);
                    leapYearParameter
                        .setCurrency(currencyRepository.findOne(leapYearParameter.getCurrency().getCurcyId()));
                    leapYearParameterRepository.save(leapYearParameter);
                } else {
                    if (CoreConstant.STATUS_ACTIVE.equals(leapYearParameterDTO.getStatus())) {
                        // create
                        LeapYearParameter leapYearParameter
                            = DataUtils.copyProperties(leapYearParameterDTO, LeapYearParameter.class);
                        leapYearParameter.setBankInfoId(bankInfoId);
                        leapYearParameterRepository.save(leapYearParameter);
                    }
                }
            }
        }

        List<FreeBankTransferDTO> freeBankTransfers = bankInfoDTO.getFreeBankTransfers();
        if (CommonUtils.isNotEmpty(freeBankTransfers)) {
            for (FreeBankTransferDTO freeBankTransferDTO : freeBankTransfers) {
                if (freeBankTransferDTO.getFreeBankTransferId() != null
                    && freeBankTransferDTO.getFreeBankTransferId().intValue() > 0) {
                    // update
                    FreeBankTransfer freeBankTransfer
                        = DataUtils.copyProperties(freeBankTransferDTO, FreeBankTransfer.class);
                    freeBankTransferRepository.save(freeBankTransfer);
                } else {
                    if (CoreConstant.STATUS_ACTIVE.equals(freeBankTransferDTO.getStatus())) {
                        // create
                        FreeBankTransfer freeBankTransfer
                            = DataUtils.copyProperties(freeBankTransferDTO, FreeBankTransfer.class);
                        freeBankTransfer.setBankInfoId1(bankInfoId);
                        freeBankTransfer
                            .setBankInfo(DataUtils.copyProperties(freeBankTransferDTO.getBankInfo(), BankInfo.class));
                        freeBankTransferRepository.save(freeBankTransfer);
                    }
                }
            }
        }
        log.info("save() end");
        return bankInfoId;
    }

    @Override
    @Transactional(readOnly = true)
    public BankInfoDTO findOne(Integer bankInfoId) {
        log.info("findOne() start - bankInfoId: " + bankInfoId);
        BankInfo bankInfo = bankInfoRepository.findOne(bankInfoId);
        BankInfoDTO dto = BankInfoDTOAssembler.toDTO(bankInfo);
        log.info("findOne() start - " + dto);
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankInfoDTO> findAll() {
        log.info("findAll() start - ");
        SearchBankAccountInfoCriteriaDTO criteriaDTO = new SearchBankAccountInfoCriteriaDTO();
        criteriaDTO.setStatus(CoreConstant.STATUS_ACTIVE);
        List<BankInfo> bankInfos = (List<BankInfo>)bankInfoRepository
            .findAll(BankAccountPredicate.findByCriteria(criteriaDTO), BankInfoPredicate.orderByBankName());
        List<BankInfoDTO> dtoList = BankInfoDTOAssembler.toDTOs(bankInfos);
        log.info("findOne() start - " + dtoList);
        return dtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByBankCodeAndBankInfoIdNot(String bankCode, Integer bankInfoId) {
        log.info("existsByBankCodeAndBankInfoIdNot() start - bankCode: " + bankCode + ", bankInfoId: " + bankInfoId);
        boolean exists = bankInfoRepository.existsByBankCodeIgnoreCaseAndBankInfoIdNot(bankCode, bankInfoId);
        log.info("existsByBankCodeAndBankInfoIdNot() end - exists: " + exists);
        return exists;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByBankNameAndBankInfoIdNot(String bankName, Integer bankInfoId) {
        log.info("existsByBankNameAndBankInfoIdNot() start - bankName: " + bankName + ", bankInfoId: " + bankInfoId);
        boolean exists = bankInfoRepository.existsByBankNameLikeAndBankInfoIdNot(bankName, bankInfoId);
        log.info("existsByBankNameAndBankInfoIdNot() end - exists: " + exists);
        return exists;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByBankShortNameAndBankInfoIdNot(String bankShortName, Integer bankInfoId) {
        log.info("existsByBankShortNameAndBankInfoIdNot() start - bankShortName: " + bankShortName + ", bankInfoId: "
            + bankInfoId);
        boolean exists = bankInfoRepository.existsByBankShortNameLikeAndBankInfoIdNot(bankShortName, bankInfoId);
        log.info("existsByBankShortNameAndBankInfoIdNot() end - exists: " + exists);
        return exists;
    }

    @Override
    @Transactional(readOnly = true)
    public BankInfoDTO findByBankName(String bankName) {
        log.info("findByBankName() start - bankName: " + bankName);
        BankInfoDTO bankInfoDTO = null;
        if (bankName != null) {
            BankInfo bankInfo = bankInfoRepository.findOne(BankAccountPredicate.findByBankName(bankName.trim()));
            if (bankInfo != null) {
                bankInfoDTO = BankInfoDTOAssembler.toDTO(bankInfo);
            }
        }
        log.info("findByBankName() end - rerturn: " + bankInfoDTO);
        return bankInfoDTO;
    }

}
