package hk.oro.iefas.web.ledger.voucherhandling.paymentvoucher.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.shroff.vo.SysAttachmentVO;
import hk.oro.iefas.domain.shroff.vo.VoucherAttachmentVO;
import hk.oro.iefas.domain.shroff.vo.VoucherVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.SysAttachmentClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.VoucherAttachmentClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
public class UploadSupportingDocView extends BaseBean {

    private static final long serialVersionUID = 1L;

    public static final String REQUEST_PARAM_VOUCHER_ID = "voucherId";
    public static final String REQUEST_PARAM_VOUCHER_ATTACHMENT_ID = "voucherAttachmentId";

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Inject
    private VoucherAttachmentClientService voucherAttachmentClientService;

    @Inject
    private SysAttachmentClientService sysAttachmentClientService;

    @Getter
    @Setter
    private UploadedFile file;

    @Getter
    @Setter
    private SysAttachmentVO attachment;

    @Getter
    @Setter
    private VoucherAttachmentVO voucherAttachment;

    @Getter
    @Setter
    private String fileName;

    @Getter
    @Setter
    private Integer voucherId;

    @Getter
    @Setter
    private Integer voucherAttachmentId;

    @PostConstruct
    public void init() {
        log.info("init() start ");
        voucherAttachmentId = Faces.getRequestParameter(REQUEST_PARAM_VOUCHER_ATTACHMENT_ID, Integer.class);
        voucherId = Faces.getRequestParameter(REQUEST_PARAM_VOUCHER_ID, Integer.class);
        if (voucherAttachmentId != null) {
            voucherAttachment = voucherAttachmentClientService.findVoucherAttachmentDetail(voucherAttachmentId);
            attachment = sysAttachmentClientService.getSysAttachmentDetail(voucherAttachment.getAttachmentId());
        } else {
            voucherAttachment = new VoucherAttachmentVO();
            attachment = new SysAttachmentVO();
            VoucherVO voucher = new VoucherVO();
            voucher.setVoucherId(voucherId);
            voucherAttachment.setVoucher(voucher);
            voucherAttachment.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        log.info("init() end ");
    }

    public void uploadFile() {
        log.info("uploadFile() start ");
        if (CommonUtils.isNotEmpty(file.getContents())) {
            voucherAttachment.setFileName(file.getFileName());
            attachment.setContent(file.getContents());
            if (voucherAttachment.getAttachmentId() != null) {
                attachment.setIsUpdated(true);
            } else {
                attachment.setStatus(CoreConstant.STATUS_ACTIVE);
            }
        }
        log.info("uploadFile() end");
    }

    public void saveAndUpload() {
        log.info("saveAndUpload() start ");
        if (voucherId == null || voucherId == 0) {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EXIST, MsgParamCodeConstant.VOUCHER));
        } else {
            if ((attachment.getIsUpdated() != null && attachment.getIsUpdated())
                || attachment.getAttachmentId() == null) {
                Integer attachmentId = sysAttachmentClientService.saveSysAttachment(attachment);
                voucherAttachment.setAttachmentId(attachmentId);
                Integer result = voucherAttachmentClientService.saveVoucherAttachment(voucherAttachment);
                if (result != null) {
                    RequestContext.getCurrentInstance().closeDialog(null);
                } else {
                    Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
                }
            } else {
                Integer result = voucherAttachmentClientService.saveVoucherAttachment(voucherAttachment);
                if (result != null) {
                    RequestContext.getCurrentInstance().closeDialog(null);
                } else {
                    Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
                }
            }
        }
        log.info("saveAndUpload() end ");
    }

}
