package hk.oro.iefas.web.ledger.chequehandling.outgoingcheque.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.organization.vo.ApproverVO;
import hk.oro.iefas.domain.shroff.vo.ChequeVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.chequehandling.incomingcheque.service.ChequeClientService;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3260 $ $Date: 2018-06-22 14:04:36 +0800 (週五, 22 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@ViewScoped
public class OutgoingChequeEditView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer outgoingChequeId;

    @Inject
    private ChequeClientService chequeClientService;

    @Inject
    private PostClientService postClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private List<ApproverVO> approverAVOS;

    @Getter
    @Setter
    private List<ApproverVO> approverBVOS;

    private List<StatusVO> statusVOS;

    @Getter
    @Setter
    private ChequeVO chequeVO;

    @Getter
    @Setter
    private List<ChequeVO> childCheques;

    @Getter
    @Setter
    private boolean toSubmit;

    @Getter
    @Setter
    private boolean toApprove;

    @Getter
    @Setter
    private boolean toReject;

    @Getter
    @Setter
    private boolean toPrint;

    @Getter
    @Setter
    private String statusName;

    @PostConstruct
    public void init() {
        log.info("============== OutgoingChequeEditView init start ==============");
        outgoingChequeId = Faces.getRequestParameter("outgoingChequeId", Integer.class);
        approverAVOS = postClientService.getAApprover(SecurityUtils.getCurrenDivisionId());
        approverBVOS = postClientService.getBApprover(SecurityUtils.getCurrenDivisionId());
        statusVOS = appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.CQO.name());
        if (outgoingChequeId != null) {
            chequeVO = chequeClientService.getOutgoingChequeDetail(outgoingChequeId);
        }
        if (chequeVO != null) {
            childCheques = chequeClientService.getOutgoingChequeByParentId(chequeVO.getChequeId());
            menuStatus();
        }
        initStatusName();
        log.info("============== OutgoingChequeEditView init end ================");
    }

    private void initStatusName() {
        if (chequeVO != null) {
            for (StatusVO statusVO : statusVOS) {
                if (statusVO.getStatusVal().equals(chequeVO.getStatus())) {
                    statusName = statusVO.getStatusName();
                    break;
                }
            }
        }
    }

    public void save() {
        log.info("save start");
        Integer chequeId = chequeClientService.saveOutgoingCheque(chequeVO);
        if (chequeId == null) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
        } else {
            chequeVO = chequeClientService.getOutgoingChequeDetail(chequeId);
            initStatusName();
            menuStatus();
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        }
        log.info("save end");
    }

    public void submit() {
        log.info("submit start");
        ApproverVO approverA = null;
        ApproverVO approverB = null;
        if (chequeVO.getGroupaApproverId() != null) {
            for (ApproverVO approverVO : approverAVOS) {
                if (approverVO.getPostId().equals(chequeVO.getGroupaApproverId())) {
                    approverA = approverVO;
                    break;
                }
            }
        }
        if (chequeVO.getGroupbApproverId() != null) {
            for (ApproverVO approverVO : approverBVOS) {
                if (approverVO.getPostId().equals(chequeVO.getGroupbApproverId())) {
                    approverB = approverVO;
                    break;
                }
            }
        }
        Integer totalAmount;
        if (approverA == null || approverB == null) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
            log.info("submit end");
            return;
        } else if (chequeVO.getChequeAmount() != null) {
            totalAmount = chequeVO.getChequeAmount().intValue();
            if (approverA.getRank() == null || approverB.getRank() == null
                || totalAmount > approverA.getRank().getPaymentLimit()
                || totalAmount > approverB.getRank().getPaymentLimit()) {
                Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
                log.info("submit end");
                return;
            }
        }
        chequeVO.setStatus(CoreConstant.CHEQUE_STATUS_SUBMITTED);
        save();
        log.info("submit end");
    }

    public void approve() {
        log.info("approve start");
        chequeVO.setStatus(CoreConstant.CHEQUE_STATUS_APPROVED);
        save();
        log.info("approve end");
    }

    public void reject() {
        log.info("reject start");
        chequeVO.setStatus(CoreConstant.CHEQUE_STATUS_REJECTED);
        save();
        log.info("reject end");
    }

    public void print() {
        log.info("print start");
        Date currentBusinessDate = appResourceUtils.getBusinessDate();
        if (chequeVO.getChequeDate() != null && chequeVO.getChequeDate().before(currentBusinessDate)) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_OUTGOING_CHEQUE_DATE_ERROR));
            log.info("print end");
            return;
        }
        chequeVO.setStatus(CoreConstant.CHEQUE_STATUS_GENERATED);
        save();
        log.info("print end");
    }

    private void menuStatus() {
        String status = chequeVO.getStatus();
        toSubmit = CoreConstant.COMMON_STATUS_DRAFT.equals(status) || CoreConstant.CHEQUE_STATUS_REJECTED.equals(status)
            || CoreConstant.CHEQUE_STATUS_COMBINED.equals(status);
        toApprove = CoreConstant.CHEQUE_STATUS_SUBMITTED.equals(status);
        toReject = CoreConstant.CHEQUE_STATUS_SUBMITTED.equals(status);
        toPrint
            = CoreConstant.CHEQUE_STATUS_APPROVED.equals(status) || CoreConstant.CHEQUE_STATUS_CANCELLED.equals(status);
    }
}
