package hk.oro.iefas.ws.shroff.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.VoucherAttachmentDTO;
import hk.oro.iefas.domain.shroff.entity.ShrVcrAttachment;
import hk.oro.iefas.domain.system.entity.SysAttachment;
import hk.oro.iefas.ws.shroff.repository.SysAttachmentRepository;
import hk.oro.iefas.ws.shroff.repository.VoucherAttachmentRepository;
import hk.oro.iefas.ws.shroff.repository.VoucherRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.VoucherAttachmentDTOAssembler;
import hk.oro.iefas.ws.shroff.service.VoucherAttachmentService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2925 $ $Date: 2018-06-05 21:36:41 +0800 (週二, 05 六月 2018) $
 * @author $Author: dante.fang $
 */
@Service
@Slf4j
public class VoucherAttachmentServiceImpl implements VoucherAttachmentService {

    @Autowired
    private VoucherAttachmentRepository voucherAttachmentRepository;

    @Autowired
    private SysAttachmentRepository sysAttachmentRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherAttachmentDTOAssembler voucherAttachmentDTOAssembler;

    @Override
    @Transactional(readOnly = true)
    public VoucherAttachmentDTO findVoucherAttachment(Integer voucherAttachmentId) {
        log.info("findVoucherAttachment start - voucherAttachmentId: " + voucherAttachmentId);
        ShrVcrAttachment attachment = voucherAttachmentRepository.findOne(voucherAttachmentId);
        VoucherAttachmentDTO dto = voucherAttachmentDTOAssembler.toDTO(attachment);
        log.info("findVoucherAttachment end - result = " + dto);
        return dto;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveVoucherAttachment(VoucherAttachmentDTO voucherAttachment) {
        log.info("saveVoucherAttachment start - voucherAttachment: " + voucherAttachment);
        SysAttachment sysAttachment = null;
        if (voucherAttachment.getAttachmentId() != null) {
            sysAttachment = sysAttachmentRepository.findOne(voucherAttachment.getAttachmentId());
        }

        ShrVcrAttachment attachment = DataUtils.copyProperties(voucherAttachment, ShrVcrAttachment.class);
        attachment.setVoucher(voucherRepository.findOne(voucherAttachment.getVoucher().getVoucherId()));
        attachment.setAttachment(sysAttachment);
        attachment = voucherAttachmentRepository.save(attachment);
        log.info("saveVoucherAttachment end - VoucherAttachmentId = " + attachment.getVoucherAttachmentId());
        return attachment.getVoucherAttachmentId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteVoucherAttachment(VoucherAttachmentDTO voucherAttachment) {
        log.info("deleteVoucherAttachment start - voucherAttachment: " + voucherAttachment);
        SysAttachment sysAttachment = sysAttachmentRepository.findOne(voucherAttachment.getAttachmentId());
        if (sysAttachment != null) {
            sysAttachment.setStatus(CoreConstant.STATUS_DELETE);
            sysAttachment = sysAttachmentRepository.save(sysAttachment);
        }

        ShrVcrAttachment attachment = DataUtils.copyProperties(voucherAttachment, ShrVcrAttachment.class);
        attachment.setVoucher(voucherRepository.findOne(voucherAttachment.getVoucher().getVoucherId()));
        attachment.setAttachment(sysAttachment);
        attachment.setStatus(CoreConstant.STATUS_DELETE);
        attachment = voucherAttachmentRepository.save(attachment);
        log.info("deleteVoucherAttachment end - VoucherAttachmentId = " + attachment.getVoucherAttachmentId());
        return attachment.getVoucherAttachmentId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherAttachmentDTO> findVoucherAttachmentByVoucher(Integer voucherId) {
        log.info("findVoucherAttachmentByVoucher start - voucherId: " + voucherId);
        List<ShrVcrAttachment> attachmentList = voucherAttachmentRepository.findVoucherAttachmentByVoucher(voucherId);
        List<VoucherAttachmentDTO> result = voucherAttachmentDTOAssembler.toDTOList(attachmentList);
        log.info("findVoucherAttachmentByVoucher end - attachmentList: " + result);
        return result;
    }
}
