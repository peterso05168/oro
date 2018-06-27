package hk.oro.iefas.ws.shroff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.dto.VoucherAttachmentDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.VoucherAttachmentService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2925 $ $Date: 2018-06-05 21:36:41 +0800 (週二, 05 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_VOUCHER_ATTACHMENT)
public class VoucherAttachmentController {

    @Autowired
    private VoucherAttachmentService voucherAttachmentService;

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_SAVE)
    public Integer saveVoucherAttachment(@RequestBody VoucherAttachmentDTO VoucherAttachment) {
        log.info("saveVoucherAttachment() start - VoucherAttachment = " + VoucherAttachment);
        Integer voucherAttachmentId = voucherAttachmentService.saveVoucherAttachment(VoucherAttachment);
        log.info("saveVoucherAttachment() end voucherAttachmentId: " + voucherAttachmentId);
        return voucherAttachmentId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_DELETE)
    @PostMapping(value = RequestUriConstant.URI_DELETE_VOUCHER_ATTACHMENT)
    public Integer deleteVoucherAttachment(@RequestBody VoucherAttachmentDTO VoucherAttachment) {
        log.info("deleteVoucherAttachment() start - VoucherAttachment = " + VoucherAttachment);
        Integer voucherAttachmentId = voucherAttachmentService.deleteVoucherAttachment(VoucherAttachment);
        log.info("deleteVoucherAttachment() end voucherAttachmentId: " + voucherAttachmentId);
        return voucherAttachmentId;
    }
    
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_FIND_ONE)
    public VoucherAttachmentDTO findVoucherAttachment(@RequestBody Integer voucherAttachmentId) {
        log.info("findVoucherAttachment() start - VoucherId: " + voucherAttachmentId);
        VoucherAttachmentDTO dto = voucherAttachmentService.findVoucherAttachment(voucherAttachmentId);
        log.info("findVoucherAttachment() end - VoucherAttachmentDTO = " + dto);
        return dto;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_FIND_VOUCHER_ATTACHMENT_BY_VOUCHER)
    public List<VoucherAttachmentDTO> findVoucherAttachmentByVoucher(@RequestBody Integer voucherId) {
        log.info("findVoucherAttachment() start - VoucherId: " + voucherId);
        List<VoucherAttachmentDTO> dto = voucherAttachmentService.findVoucherAttachmentByVoucher(voucherId);
        log.info("findVoucherAttachment() end - VoucherAttachmentDTO = " + dto);
        return dto;
    }
}
