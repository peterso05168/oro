package hk.oro.iefas.web.ledger.voucherhandling.common.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.vo.SysAttachmentVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.SysAttachmentClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2925 $ $Date: 2018-06-05 21:36:41 +0800 (週二, 05 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Service
public class SysAttachmentClientServiceImpl extends BaseClientService implements SysAttachmentClientService {

    @Override
    public Integer saveSysAttachment(SysAttachmentVO sysAttachment) {
        log.info("saveSysAttachment() start -  Attachment " + sysAttachment);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_SYS_ATTACHMENT, sysAttachment, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveSysAttachment() end SysId: " + body);
        return body;
    }

    @Override
    public SysAttachmentVO getSysAttachmentDetail(Integer sysAttachmentId) {
        log.info("findSysAttachmentDetail() start - sysAttachmentId: " + sysAttachmentId);
        ResponseEntity<SysAttachmentVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_FIND_SYS_ATTACHMENT, sysAttachmentId, SysAttachmentVO.class);
        SysAttachmentVO result = responseEntity.getBody();
        log.info("findSysAttachmentDetail() end - " + result);
        return result;
    }
}
