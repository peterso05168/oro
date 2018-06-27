package hk.oro.iefas.web.ledger.voucherhandling.common.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.vo.VoucherAttachmentVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.VoucherAttachmentClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2941 $ $Date: 2018-06-06 14:56:42 +0800 (週三, 06 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Service
public class VoucherAttachmentClientServiceImpl extends BaseClientService implements VoucherAttachmentClientService {

    @Override
    public Integer saveVoucherAttachment(VoucherAttachmentVO voucherAttachment) {
        log.info("saveVoucherAttachment() start -  voucher Attachment " + voucherAttachment);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_VOUCHER_ATTACHMENT, voucherAttachment, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveVoucherAttachment() end VoucherId: " + body);
        return body;
    }

    @Override
    public Integer deleteVoucherAttachment(VoucherAttachmentVO voucherAttachment) {
        log.info("deleteVoucherAttachment() start -  voucher Attachment " + voucherAttachment);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_DELETE_VOUCHER_ATTACHMENT, voucherAttachment, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("deleteVoucherAttachment() end VoucherId: " + body);
        return body;
    }

    @Override
    public List<VoucherAttachmentVO> findVoucherAttachmentByVoucher(Integer voucherId) {
        log.info("findVoucherAttachmentByVoucher() start - and voucherId = " + voucherId);
        ResponseEntity<List<VoucherAttachmentVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_FIND_VOUCHER_ATTACHMENT_BY_VOUCHER, HttpMethod.POST,
                new HttpEntity<Integer>(voucherId), new ParameterizedTypeReference<List<VoucherAttachmentVO>>() {});
        List<VoucherAttachmentVO> result = responseEntity.getBody();
        log.info("findVoucherAttachmentByVoucher() end - " + result);
        return result;
    }

    @Override
    public VoucherAttachmentVO findVoucherAttachmentDetail(Integer voucherAttachmentId) {
        log.info("findVoucherAttachmentDetail() start - voucherAttachmentId: " + voucherAttachmentId);
        ResponseEntity<VoucherAttachmentVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_FIND_VOUCHER_ATTACHMENT, voucherAttachmentId, VoucherAttachmentVO.class);
        VoucherAttachmentVO result = responseEntity.getBody();
        log.info("findVoucherAttachmentDetail() end - " + result);
        return result;
    }

}
