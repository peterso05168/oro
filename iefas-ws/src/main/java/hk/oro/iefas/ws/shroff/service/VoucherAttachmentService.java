package hk.oro.iefas.ws.shroff.service;

import java.util.List;

import hk.oro.iefas.domain.shroff.dto.VoucherAttachmentDTO;

/**
 * @version $Revision: 2925 $ $Date: 2018-06-05 21:36:41 +0800 (週二, 05 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface VoucherAttachmentService {

    Integer saveVoucherAttachment(VoucherAttachmentDTO voucherAttachment);
    
    VoucherAttachmentDTO findVoucherAttachment(Integer voucherAttachmentId);
    
    List<VoucherAttachmentDTO> findVoucherAttachmentByVoucher(Integer voucherId);

    Integer deleteVoucherAttachment(VoucherAttachmentDTO voucherAttachment);

}
