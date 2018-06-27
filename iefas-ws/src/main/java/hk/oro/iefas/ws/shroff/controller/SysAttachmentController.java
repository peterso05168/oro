package hk.oro.iefas.ws.shroff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.dto.SysAttachmentDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.SysAttachmentService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2925 $ $Date: 2018-06-05 21:36:41 +0800 (週二, 05 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_SYS_ATTACHMENT)
public class SysAttachmentController {

    @Autowired
    private SysAttachmentService sysAttachmentService;

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_SAVE)
    public Integer saveSysAttachment(@RequestBody SysAttachmentDTO SysAttachment) {
        log.info("saveSysAttachment() start - SysAttachment = " + SysAttachment);
        Integer voucherAttachmentId = sysAttachmentService.saveSysAttachment(SysAttachment);
        log.info("saveSysAttachment() end voucherAttachmentId: " + voucherAttachmentId);
        return voucherAttachmentId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_FIND_ONE)
    public SysAttachmentDTO findSysAttachment(@RequestBody Integer sysAttachmentId) {
        log.info("findSysAttachment() start - SysId: " + sysAttachmentId);
        SysAttachmentDTO dto = sysAttachmentService.getSysAttachmentDetail(sysAttachmentId);
        log.info("findSysAttachment() end - sysAttachmentDTO = " + dto);
        return dto;
    }

}
