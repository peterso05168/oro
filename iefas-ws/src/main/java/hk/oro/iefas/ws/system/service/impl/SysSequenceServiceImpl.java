package hk.oro.iefas.ws.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CaseConstant;
import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.system.entity.SysSequence;
import hk.oro.iefas.ws.common.util.RequestContextUtils;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.core.constant.SysSequenceEnum;
import hk.oro.iefas.ws.system.repository.SysSequenceRepository;
import hk.oro.iefas.ws.system.service.SysSequenceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysSequenceServiceImpl implements SysSequenceService {

    @Autowired
    private SysSequenceRepository sysSequenceRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long generateIncrSeq(String seqCode) {
        log.info("generateIncrSeq start seqCode:" + seqCode);
        Long seq = null;
        SysSequence sysSequence = sysSequenceRepository.findBySeqCode(seqCode);
        if (sysSequence != null) {
            seq = Long.valueOf(sysSequence.getSeqValue()) + 1;
            sysSequence.setSeqValue(String.valueOf(seq));
            sysSequenceRepository.save(sysSequence);
        } else {
            seq = 1L;
            sysSequence = new SysSequence();
            sysSequence.setSeqCode(seqCode);
            sysSequence.setSeqValue(String.valueOf(seq));
            sysSequenceRepository.save(sysSequence);
        }
        log.info("generateIncrSeq end return : " + seq);
        return seq;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long generateTxnKeyRefNo() {
        log.info("generateTxnKeyRefNo start ");
        Long txnKey;
        SysSequence txn = sysSequenceRepository.findBySeqCode(SysSequenceEnum.TRANSACTION_KEY.name());
        SimpleDateFormat sdf = new SimpleDateFormat(ModuleLogConstant.DATE_FORMATE_FOR_TXN_KEY);
        String newDateTxnKey = sdf.format(DateUtils.getCurrentDate());
        if (txn != null) {
            String value = txn.getSeqValue();

            if (!value.startsWith(newDateTxnKey)) {
                value = addZeroForNum(newDateTxnKey);
            }
            txnKey = Long.valueOf(value) + 1;
            txn.setSeqValue(String.valueOf(txnKey));
            sysSequenceRepository.save(txn);
        } else {
            txn = new SysSequence();
            txn.setSeqCode(SysSequenceEnum.TRANSACTION_KEY.name());
            txn.setSeqValue(addZeroForNum(newDateTxnKey));
            txnKey = Long.valueOf(txn.getSeqValue());
            sysSequenceRepository.save(txn);
        }
        RequestContextUtils.setTxnKeyRef(txnKey);
        log.info("generateTxnKeyRefNo end return : " + txnKey);
        return txnKey;
    }

    private static String addZeroForNum(String str) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < 14) {
            sb = new StringBuffer();
            sb.append(str).append("0");
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String generateWithheldReasonNo(String seqCode) {
        String seq_val = null;
        SysSequence sysSequence = sysSequenceRepository.findBySeqCode(seqCode);
        if (sysSequence != null) {
            seq_val = sysSequence.getSeqValue();
            char lastCode = seq_val.charAt(seq_val.length() - 1);
            if (lastCode == 'Z') {
                if (seq_val.length() == 10) {
                    return null;
                }
                seq_val += DividendConstant.WITHHELD_CODE_A;
            } else {
                int newCode = (int)lastCode + 1;
                seq_val = seq_val.substring(0, seq_val.length() - 1) + (char)newCode;
            }
            sysSequence.setSeqValue(seq_val);
            sysSequenceRepository.save(sysSequence);
        } else {
            seq_val = DividendConstant.WITHHELD_CODE_A;
            sysSequence = new SysSequence();
            sysSequence.setSeqCode(seqCode);
            sysSequence.setSeqValue(seq_val);
            sysSequenceRepository.save(sysSequence);
        }
        return seq_val;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String generateCommonCreditorSeqNo(String seqCode) {
        log.info("generateCommonCreditorSeqNo start ");
        String seq_val = null;
        SysSequence sysSequence = sysSequenceRepository.findBySeqCode(seqCode);
        if (sysSequence != null) {
            seq_val = sysSequence.getSeqValue();
            seq_val = String.valueOf((Integer.valueOf(seq_val) + 1));
            sysSequence.setSeqValue(seq_val);
            sysSequenceRepository.save(sysSequence);
        } else {
            seq_val = "1";
            sysSequence = new SysSequence();
            sysSequence.setSeqCode(seqCode);
            sysSequence.setSeqValue(seq_val);
            sysSequenceRepository.save(sysSequence);
        }
        String result = String.format("%05d", seq_val);
        log.info("generateCommonCreditorSeqNo end ");
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String generateVoucherSeqNo(String seqCode) {
        log.info("generateVoucherSeqNo start ");
        SysSequence sysSequence = sysSequenceRepository.findBySeqCode(seqCode);
        String nowYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        if (sysSequence != null) {
            String seqYear = sysSequence.getSeqYear();
            if (CommonUtils.isNotBlank(seqYear) && !seqYear.equals(nowYear)) {
                sysSequence.setSeqYear(nowYear);
                sysSequence.setSeqValue("0");
            }

        } else {
            sysSequence = new SysSequence();
            sysSequence.setSeqYear(nowYear);
            sysSequence.setSeqCode(seqCode);
            sysSequence.setSeqValue("0");
        }
        sysSequence.setSeqValue(String.valueOf((Integer.valueOf(sysSequence.getSeqValue()) + 1)));
        sysSequenceRepository.save(sysSequence);

        String seq_val = sysSequence.getSeqYear() + String.format("%06d", Integer.valueOf(sysSequence.getSeqValue()));
        log.info("generateVoucherSeqNo end ");
        return seq_val;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String generateTransferNo(String seqCode) {
        log.info("generateTransferNo start and seqCode = " + seqCode);
        SysSequence sysSequence = sysSequenceRepository.findBySeqCode(seqCode);
        String nowYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        if (sysSequence != null) {
            String seqYear = sysSequence.getSeqYear();
            if (CommonUtils.isNotBlank(seqYear) && !seqYear.equals(nowYear)) {
                sysSequence.setSeqYear(nowYear);
                sysSequence.setSeqValue("0");
            }
        } else {
            sysSequence = new SysSequence();
            sysSequence.setSeqYear(nowYear);
            sysSequence.setSeqCode(seqCode);
            sysSequence.setSeqValue("0");
        }
        sysSequence.setSeqValue(String.valueOf((Integer.valueOf(sysSequence.getSeqValue()) + 1)));
        sysSequenceRepository.save(sysSequence);

        String seq_val = ShroffConstant.TRANSFER_NUMBER_PREFIX + String.format("%05d", Integer.valueOf(sysSequence.getSeqValue())) + sysSequence.getSeqYear();
        log.info("generateTransferNo end and result = " + seq_val);
        return seq_val;
    }

}
