package hk.oro.iefas.ws.shroff.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.bank.dto.CurrencyDTO;
import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import hk.oro.iefas.domain.counter.entity.CtrCaseDeposit;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiveDepositDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiveDepositResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchReceiveDepositDTO;
import hk.oro.iefas.domain.shroff.entity.ShrReceipt;
import hk.oro.iefas.domain.system.entity.SysSequence;
import hk.oro.iefas.ws.bank.service.CurrencyService;
import hk.oro.iefas.ws.casemgt.service.CaseService;
import hk.oro.iefas.ws.shroff.repository.ReceiveDepositRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.ReceiveDepositDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.ReceiveDepositResultDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.predicate.ReceiveDepositPredicate;
import hk.oro.iefas.ws.shroff.service.ReceiptService;
import hk.oro.iefas.ws.shroff.service.ReceiveDepositService;
import hk.oro.iefas.ws.system.repository.SysSequenceRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Service
@Slf4j
public class ReceiveDepositServiceImpl implements ReceiveDepositService {
    @Autowired
    private ReceiveDepositRepository receiveDepositRepository;

    @Autowired
    private SysSequenceRepository sysSequenceRepository;

    @Autowired
    private ReceiveDepositResultDTOAssembler receiveDepositResultDTOAssembler;

    @Autowired
    private ReceiveDepositDTOAssembler receiveDepositDTOAssembler;

    @Autowired
    private CaseService caseService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ReceiptService receiptService;

    @Override
    @Transactional(readOnly = true)
    public Page<ReceiveDepositResultDTO> searchReceiveDepositList(SearchDTO<SearchReceiveDepositDTO> criteria) {
        log.info("searchReceiveDepositList start - criteria: " + criteria);
        Page<CtrCaseDeposit> page;
        Page<ReceiveDepositResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = receiveDepositRepository
                    .findAll(ReceiveDepositPredicate.searchReceiveDepositList(criteria.getCriteria()), pageable);
                result = receiveDepositResultDTOAssembler.toResultDTO(page);
            }
        }
        log.info("searchControlAccountResultList end - " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ReceiveDepositDTO getReceiveDepositDetail(Integer depositId) {
        log.info("getReceiveDepositDetail start - depositId: " + depositId);
        ReceiveDepositDTO result = null;
        CtrCaseDeposit caseDeposit = null;
        if (depositId != null) {
            caseDeposit = receiveDepositRepository.findOne(depositId);
        }
        if (caseDeposit != null)
            result = receiveDepositDTOAssembler.toDTO(caseDeposit);
        log.info("getReceiveDepositDetail end - " + result);
        return result;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveReceiveDeposit(ReceiveDepositDTO receiveDepositDTO) {
        log.info("saveReceiveDeposit start - receiveDepositDTO: " + receiveDepositDTO);
        Integer depositId = null;
        if (receiveDepositDTO != null) {
            if (receiveDepositDTO.getCurcy() != null) {
                Integer currencyId = receiveDepositDTO.getCurcy().getCurcyId();
                CurrencyDTO currencyDTO = currencyService.findOne(currencyId);
                receiveDepositDTO.setCurcy(currencyDTO);
            }
            if (receiveDepositDTO.getCaseInfo() != null) {
                Integer caseId = receiveDepositDTO.getCaseInfo().getCaseId();
                CaseDTO caseDTO = caseService.findOne(caseId);
                receiveDepositDTO.setCaseInfo(caseDTO);
            }
            ShrReceipt shrReceipt = null;
            if (receiveDepositDTO.getShrReceipt() != null) {
                if (receiveDepositDTO.getShrReceipt().getReceiptId() == null)
                    receiveDepositDTO.setShrReceipt(null);
                else {
                    shrReceipt = receiptService.saveReceipt(receiveDepositDTO.getShrReceipt());
                }
            }
            CtrCaseDeposit deposit = DataUtils.copyProperties(receiveDepositDTO, CtrCaseDeposit.class);
            deposit.setShrReceipt(shrReceipt);
            deposit = receiveDepositRepository.save(deposit);
            if (deposit != null)
                depositId = deposit.getDepositId();
        }
        log.info("saveReceiveDeposit end - " + depositId);
        return depositId;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveReceipt(ReceiveDepositDTO receiveDepositDTO) {
        log.info("saveReceipt start - receiveDepositDTO: " + receiveDepositDTO);
        Integer receiptId;
        Integer depositId = null;
        if (receiveDepositDTO != null) {
            ReceiptDTO receiptDTO = receiveDepositDTO.getShrReceipt();
            ShrReceipt shrReceipt = receiptService.saveReceipt(receiptDTO);
            receiptId = shrReceipt.getReceiptId();
            if (receiptId != null) {
                receiptDTO.setReceiptId(receiptId);
                if (receiveDepositDTO.getCurcy() != null) {
                    Integer currencyId = receiveDepositDTO.getCurcy().getCurcyId();
                    CurrencyDTO currencyDTO = currencyService.findOne(currencyId);
                    receiveDepositDTO.setCurcy(currencyDTO);
                }
                if (receiveDepositDTO.getCaseInfo() != null) {
                    Integer caseId = receiveDepositDTO.getCaseInfo().getCaseId();
                    CaseDTO caseDTO = caseService.findOne(caseId);
                    receiveDepositDTO.setCaseInfo(caseDTO);
                }
                CtrCaseDeposit deposit = DataUtils.copyProperties(receiveDepositDTO, CtrCaseDeposit.class);
                deposit.setShrReceipt(shrReceipt);
                deposit = receiveDepositRepository.save(deposit);
                if (deposit != null) {
                    depositId = deposit.getDepositId();
                }
            }
        }
        log.info("saveReceipt end - " + depositId);
        return depositId;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String generateDepositNo() {
        String depositNumber = null;
        String seqVal = null;
        log.info("generateDepositNumber start");
        SysSequence sysSequence = sysSequenceRepository.findBySeqCode(ShroffConstant.DEPOSIT_CODE);
        if (sysSequence != null) {
            seqVal = sysSequence.getSeqValue();
            seqVal = String.valueOf((1 + Integer.valueOf(seqVal)));
            sysSequence.setSeqValue(seqVal);
            sysSequenceRepository.save(sysSequence);
        }
        if (seqVal != null) {
            Integer seq = Integer.valueOf(seqVal);
            seq = seq % 100000;
            StringBuilder stringBuilder = new StringBuilder(ShroffConstant.BASE_NUMBER);
            int i;
            for (i = 1; i <= 5; i++) {
                if ((seq = seq / 10) == 0)
                    break;
            }
            stringBuilder.replace(5 - i, 5, seqVal);
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            depositNumber = ShroffConstant.DEPOSIT_NUMBER_PREFIX + stringBuilder + "/" + year;
        }
        log.info("generateDepositNumber end - " + depositNumber);
        return depositNumber;
    }
}
