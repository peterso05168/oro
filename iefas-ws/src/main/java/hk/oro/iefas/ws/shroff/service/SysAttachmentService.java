package hk.oro.iefas.ws.shroff.service;

import hk.oro.iefas.domain.shroff.dto.SysAttachmentDTO;

/**
 * @version $Revision: 2925 $ $Date: 2018-06-05 21:36:41 +0800 (週二, 05 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface SysAttachmentService {

    Integer saveSysAttachment(SysAttachmentDTO voucherAttachment);

    SysAttachmentDTO getSysAttachmentDetail(Integer sysAttachmentId);

}
