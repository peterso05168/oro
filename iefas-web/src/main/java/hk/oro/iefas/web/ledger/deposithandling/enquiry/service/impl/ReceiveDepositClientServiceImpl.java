package hk.oro.iefas.web.ledger.deposithandling.enquiry.service.impl;

import java.math.BigDecimal;

import javax.inject.Named;

import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.vo.ReceiptVO;
import hk.oro.iefas.domain.shroff.vo.ReceiveDepositVO;
import hk.oro.iefas.domain.shroff.vo.VoucherVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.deposithandling.enquiry.service.ReceiveDepositClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
public class ReceiveDepositClientServiceImpl extends BaseClientService implements ReceiveDepositClientService {

    @Override
    public Integer saveReceiveDeposit(ReceiveDepositVO receiveDepositVO) {
        log.info("saveReceiveDeposit start - receiveDepositVO: " + receiveDepositVO);
        ResponseEntity<Integer> resultEntity;
        if (receiveDepositVO.getShrReceipt() != null && receiveDepositVO.getShrReceipt().getReceiptId() != null) {
            initReceipt(receiveDepositVO);
        }
        resultEntity = this.postForEntity(RequestUriConstant.CLIENT_URI_RECEIPT_DEPOSIT_SAVE_DEPOSIT, receiveDepositVO,
            Integer.class);
        Integer depositId = null;
        if (resultEntity != null) {
            depositId = resultEntity.getBody();
        }
        log.info("saveReceiveDeposit end - " + depositId);
        return depositId;
    }

    @Override
    public ReceiveDepositVO getReceiveDepositDetail(Integer depositId) {
        log.info("getReceiveDepositDetail start - depositId : " + depositId);
        ReceiveDepositVO result = null;
        if (depositId != null) {
            ResponseEntity<ReceiveDepositVO> resultEntity = null;
            resultEntity = this.postForEntity(RequestUriConstant.CLIENT_URI_RECEIPT_DEPOSIT_GET_DEPOSIT_DETAIL,
                depositId, ReceiveDepositVO.class);
            if (resultEntity != null)
                result = resultEntity.getBody();
        }
        log.info("getReceiveDepositDetail end - " + result);
        return result;
    }

    @Override
    public Integer printReceipt(ReceiveDepositVO receiveDepositVO) {
        log.info("printReceipt start - receiveDepositVO: " + receiveDepositVO);
        Integer depositId = null;
        initReceipt(receiveDepositVO);
        receiveDepositVO.setStatus(CoreConstant.VOUCHER_STATUS_REVERSED);
        ResponseEntity<Integer> responseEntity
            = this.postForEntity(RequestUriConstant.CLIENT_URI_RECEIPT_SAVE_RECEIPT, receiveDepositVO, Integer.class);
        if (responseEntity != null) {
            depositId = responseEntity.getBody();
        }
        log.info("printReceipt end - " + depositId);
        return depositId;
    }

    private void initReceipt(ReceiveDepositVO receiveDepositVO) {
        if(receiveDepositVO.getShrReceipt() == null) {
            receiveDepositVO.setShrReceipt(new ReceiptVO());
        }
        ReceiptVO receiptVO = receiveDepositVO.getShrReceipt();
        if (receiptVO.getReceiptNo() == null) {
            receiptVO.setReceiptNo(generateReceiptNumber());
        }
        receiptVO.setChequeNo(receiveDepositVO.getChequeNo());
        receiptVO.setContactNo(receiveDepositVO.getContactNo());
        receiptVO.setPayerName(receiveDepositVO.getPayer());
        receiptVO.setPaymentTypeId(new BigDecimal(receiveDepositVO.getPaymentTypeId()));
        receiptVO.setReceiptAmount(receiveDepositVO.getDepositAmount());
        receiptVO.setReceiveDate(receiveDepositVO.getReceiveDate());
        if (receiptVO.getStatus() == null) {
            receiptVO.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        VoucherVO voucherVO = new VoucherVO();
        if (receiveDepositVO.getVoucherId() != null)
            voucherVO.setVoucherId(receiveDepositVO.getVoucherId().intValue());
        receiptVO.setShrVcrInfo(voucherVO);
    }

    @Override
    public String generateReceiptNumber() {
        log.info("generateReceiptNumber start");
        String receiptNumber = null;
        ResponseEntity<String> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_RECEIPT_GENERATE_RECEIPT_NUMBER, null, String.class);
        if (responseEntity != null) {
            receiptNumber = responseEntity.getBody();
        }
        log.info("generateReceiptNumber end - " + receiptNumber);
        return receiptNumber;
    }

    @Override
    public String generateDepositNumber() {
        log.info("generateDepositNumber start");
        String depositNumber = null;
        ResponseEntity<String> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_RECEIVE_DEPOSIT_GENERATE_DEPOSIT_NUMBER, null, String.class);
        if (responseEntity != null) {
            depositNumber = responseEntity.getBody();
        }
        log.info("generateDepositNumber end - " + depositNumber);
        return depositNumber;
    }
}
