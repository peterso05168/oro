package hk.oro.iefas.web.ledger.voucherhandling.common.service;

import java.util.List;

import hk.oro.iefas.domain.shroff.vo.VoucherAttachmentVO;

/**
 * @version $Revision: 2925 $ $Date: 2018-06-05 21:36:41 +0800 (週二, 05 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface VoucherAttachmentClientService {

    Integer saveVoucherAttachment(VoucherAttachmentVO voucherAttachment);
    
    Integer deleteVoucherAttachment(VoucherAttachmentVO voucherAttachment);
    
    List<VoucherAttachmentVO> findVoucherAttachmentByVoucher(Integer voucherId);
    
    VoucherAttachmentVO findVoucherAttachmentDetail(Integer voucherAttachmentId);
}
