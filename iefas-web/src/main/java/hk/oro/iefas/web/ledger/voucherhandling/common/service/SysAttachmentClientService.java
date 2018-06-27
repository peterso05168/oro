package hk.oro.iefas.web.ledger.voucherhandling.common.service;

import hk.oro.iefas.domain.shroff.vo.SysAttachmentVO;

/**
 * @version $Revision: 2925 $ $Date: 2018-06-05 21:36:41 +0800 (週二, 05 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface SysAttachmentClientService {

    Integer saveSysAttachment(SysAttachmentVO sysAttachment);
    
    SysAttachmentVO getSysAttachmentDetail(Integer sysAttachmentId);
}
