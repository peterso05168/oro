package hk.oro.iefas.ws.shroff.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.SysAttachmentDTO;
import hk.oro.iefas.domain.system.entity.SysAttachment;
import hk.oro.iefas.ws.shroff.repository.SysAttachmentRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.SysAttachmentDTOAssembler;
import hk.oro.iefas.ws.shroff.service.SysAttachmentService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2932 $ $Date: 2018-06-06 11:16:32 +0800 (週三, 06 六月 2018) $
 * @author $Author: dante.fang $
 */
@Service
@Slf4j
public class SysAttachmentServiceImpl implements SysAttachmentService {

    @Autowired
    private SysAttachmentRepository sysAttachmentRepository;

    @Autowired
    private SysAttachmentDTOAssembler sysAttachmentDTOAssembler;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveSysAttachment(SysAttachmentDTO sysAttachment) {
        log.info("saveSysAttachment start - sysAttachment: " + sysAttachment);
        SysAttachment attachment = DataUtils.copyProperties(sysAttachment, SysAttachment.class);
        if (sysAttachment.getAttachmentId() == null || sysAttachment.getIsUpdated()) {
            attachment = sysAttachmentRepository.save(attachment);
        }
        log.info("saveSysAttachment end - SysAttachmentId = " + attachment.getAttachmentId());
        return attachment.getAttachmentId();
    }

    @Override
    @Transactional(readOnly = true)
    public SysAttachmentDTO getSysAttachmentDetail(Integer sysAttachmentId) {
        log.info("getSysAttachmentDetail start - sysAttachmentId: " + sysAttachmentId);
        SysAttachment attachment = sysAttachmentRepository.findOne(sysAttachmentId);
        SysAttachmentDTO dto = sysAttachmentDTOAssembler.toDTO(attachment);
        dto.setContent(attachment.getContent());
        log.info("getSysAttachmentDetail end - result = " + dto);
        return dto;
    }
}
