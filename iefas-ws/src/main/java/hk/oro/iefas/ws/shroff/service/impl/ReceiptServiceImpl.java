package hk.oro.iefas.ws.shroff.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.ReceiptDTO;
import hk.oro.iefas.domain.shroff.dto.VoucherDTO;
import hk.oro.iefas.domain.shroff.entity.ShrReceipt;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.domain.system.entity.SysSequence;
import hk.oro.iefas.ws.shroff.repository.ReceiptRepository;
import hk.oro.iefas.ws.shroff.repository.VoucherRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.ReceiptDTOAssembler;
import hk.oro.iefas.ws.shroff.service.ReceiptService;
import hk.oro.iefas.ws.system.repository.SysSequenceRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
@Service
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private SysSequenceRepository sysSequenceRepository;

    @Autowired
    private ReceiptDTOAssembler receiptDTOAssembler;

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ShrReceipt saveReceipt(ReceiptDTO receiptDTO) {
        log.info("saveReceipt start - receiptDTO: " + receiptDTO);
        ShrReceipt shrReceipt = null;
        if (receiptDTO != null) {
            if (receiptDTO.getShrVcrInfo() != null) {
                Voucher voucher = voucherRepository.findOne(receiptDTO.getShrVcrInfo().getVoucherId());
                VoucherDTO voucherDTO = DataUtils.copyProperties(voucher, VoucherDTO.class);
                if (voucherDTO != null) {
                    receiptDTO.setShrVcrInfo(voucherDTO);
                    shrReceipt = receiptRepository.save(DataUtils.copyProperties(receiptDTO, ShrReceipt.class));
                }
            }
        }
        log.info("saveReceipt end - " + receiptDTO);
        return shrReceipt;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String generateReceiptNumber() {
        String receiptNumber = null;
        String seqVal = null;
        log.info("generateReceiptNumber start");
        SysSequence sysSequence = sysSequenceRepository.findBySeqCode(ShroffConstant.RECEIPT_CODE);
        if (sysSequence != null) {
            seqVal = sysSequence.getSeqValue();
            seqVal = String.valueOf((Integer.valueOf(seqVal) + 1));
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
            receiptNumber = ShroffConstant.RECEIPT_NUMBER_PREFIX + stringBuilder + "/" + year;
        }
        log.info("generateReceiptNumber end - " + receiptNumber);
        return receiptNumber;
    }

    @Override
    @Transactional(readOnly = true)
    public ReceiptDTO getReceiptDetail(Integer receiptId) {
        log.info("getReceiptDetail start - receiptId: " + receiptId);
        ReceiptDTO receiptDTO = null;
        ShrReceipt shrReceipt;
        if (receiptId != null) {
            shrReceipt = receiptRepository.findOne(receiptId);
            receiptDTO = receiptDTOAssembler.toDTO(shrReceipt);
        }
        log.info("getReceiptDetail end - " + receiptDTO);
        return receiptDTO;
    }
}
