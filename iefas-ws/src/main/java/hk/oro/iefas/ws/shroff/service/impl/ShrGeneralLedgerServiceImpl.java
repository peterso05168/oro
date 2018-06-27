/**
 * 
 */
package hk.oro.iefas.ws.shroff.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.shroff.entity.ShrGeneralLedger;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.ws.shroff.repository.ShrGeneralLedgerRepository;
import hk.oro.iefas.ws.shroff.repository.VoucherRepository;
import hk.oro.iefas.ws.shroff.service.ShrGeneralLedgerService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3259 $ $Date: 2018-06-22 11:29:09 +0800 (週五, 22 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class ShrGeneralLedgerServiceImpl implements ShrGeneralLedgerService {

    @Autowired
    private ShrGeneralLedgerRepository shrGeneralLedgerRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public Integer saveGeneralLedger(String analysisCode, Integer voucherId, BigDecimal credit, BigDecimal debit,
        BigDecimal balance, Integer caseAcId, Integer controlAcId, String particulars) {
        log.info(
            "saveGeneralLedger() start - AnalysisCode: " + analysisCode + ", VoucherId: " + voucherId + ", Credit: "
                + credit + ", Balance: " + balance + ", CaseAcId: " + caseAcId + ", ControlAcId: " + controlAcId);
        ShrGeneralLedger shrGeneralLedger = new ShrGeneralLedger();
        shrGeneralLedger.setAnalysisCode(analysisCode);
        if (voucherId != null) {
            Voucher voucher = voucherRepository.findOne(voucherId);
            shrGeneralLedger.setShrVcrInfo(voucher);
            shrGeneralLedger.setPostingDate(voucher.getSubmissionDate());
            shrGeneralLedger.setWorkingDate(voucher.getVoucherDate());
        }
        shrGeneralLedger.setStatus(CoreConstant.STATUS_ACTIVE);
        shrGeneralLedger.setCredit(credit);
        shrGeneralLedger.setDebit(debit);
        shrGeneralLedger.setBalance(balance);
        shrGeneralLedger.setCaseAcId(caseAcId);
        shrGeneralLedger.setControlAcId(controlAcId);
        shrGeneralLedger.setParticulars(particulars);
        shrGeneralLedger = shrGeneralLedgerRepository.save(shrGeneralLedger);
        log.info("saveGeneralLedger() end - " + shrGeneralLedger);
        return shrGeneralLedger.getGeneralLedgerId();
    }

}
