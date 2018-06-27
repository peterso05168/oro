package hk.oro.iefas.web.ledger.paymenthandling.transfertogrorbea;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.casemgt.vo.CaseAccountInfoVO;
import hk.oro.iefas.domain.shroff.vo.PaymentVoucherAccountItemVO;
import hk.oro.iefas.domain.shroff.vo.PaymentVoucherBasicInformationVO;
import hk.oro.iefas.domain.shroff.vo.PaymentVoucherDetailVO;
import hk.oro.iefas.domain.shroff.vo.SearchOldCaseAccountCriteriaVO;
import hk.oro.iefas.domain.shroff.vo.TransferToGrOrBeaItemVO;
import hk.oro.iefas.domain.shroff.vo.TransferToGrOrBeaVO;
import hk.oro.iefas.domain.shroff.vo.VoucherVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseAccountClientService;
import hk.oro.iefas.web.common.constant.ShroffWebConstant;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.paymenthandling.transfertogrorbea.service.TransferToGrOrBeaClientService;
import hk.oro.iefas.web.ledger.paymenthandling.transfertogrorbea.service.TransferToGrOrBeaItemClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.VoucherClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3278 $ $Date: 2018-06-25 16:36:21 +0800 (週一, 25 六月 2018) $
 * @author $Author: dante.fang $
 */
@ViewScoped
@Slf4j
@Named
public class TransferToGrOrBeaEditView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private TransferToGrOrBeaClientService transferToGrOrBeaClientService;

    @Inject
    private TransferToGrOrBeaItemClientService transferToGrOrBeaItemClientService;

    @Inject
    private CaseAccountClientService caseAccountClientService;

    @Inject
    private VoucherClientService voucherClientService;

    @Getter
    @Setter
    private TransferToGrOrBeaVO transferVO;

    @Getter
    @Setter
    private PaymentVoucherDetailVO paymentVoucherVO;

    @Getter
    @Setter
    private Integer transferId;

    @Getter
    @Setter
    private Integer accountTypeId;

    @Getter
    @Setter
    private List<CaseAccountInfoVO> accountList;

    @Getter
    @Setter
    private List<CaseAccountInfoVO> selectAccountList = new ArrayList<CaseAccountInfoVO>();

    @Getter
    @Setter
    private List<TransferToGrOrBeaItemVO> transferItemList;

    @PostConstruct
    public void init() {
        log.info("init() start ");
        transferId = Faces.getRequestParameter("transferId", Integer.class);
        accountTypeId = Faces.getFlashAttribute(ShroffWebConstant.ACCOUNT_TYPE_ID);
        transferVO = Faces.getFlashAttribute(ShroffWebConstant.TRANSFER_VO);
        accountList
            = caseAccountClientService.findOldCaseAccountByAccountType(new SearchOldCaseAccountCriteriaVO(accountTypeId,
                transferVO != null && transferVO.getCutOffDate() != null ? transferVO.getCutOffDate() : null));
        if (transferId != null) {
            transferVO = transferToGrOrBeaClientService.getTransferToGrOrBeaDetail(transferId);
            transferItemList = transferToGrOrBeaItemClientService.findTransferToGrOrBeaItemByTransfer(transferId);
            genSelectedAccountList();
        } else {
            if (transferVO == null) {
                transferVO = new TransferToGrOrBeaVO();
            }
            transferVO.setStatus(CoreConstant.STATUS_ACTIVE);
            transferItemList = new ArrayList<TransferToGrOrBeaItemVO>();
            paymentVoucherVO = new PaymentVoucherDetailVO();
            paymentVoucherVO.setPaymentVoucherBasicInformation(new PaymentVoucherBasicInformationVO());
        }
        log.info("init() end ");
    }

    private TransferToGrOrBeaItemVO transformAccountToTransferItem(CaseAccountInfoVO account) {
        TransferToGrOrBeaItemVO result = new TransferToGrOrBeaItemVO();
        result.setAcId(account.getCaseAcId());
        result.setAcTypeId(account.getCaseAccountType().getCaseAcTypeId());
        result.setTranAmount(account.getLiquidCashAmount());
        result.setStatus(CoreConstant.STATUS_ACTIVE);
        result.setTransferId(transferId != null && transferId != 0 ? transferId : null);
        return result;
    }

    private PaymentVoucherAccountItemVO transformAccountToPaymentVoucherItem(CaseAccountInfoVO account,
        Integer itemNo) {
        PaymentVoucherAccountItemVO result = new PaymentVoucherAccountItemVO();
        result.setVoucherItemNo(itemNo);
        result.setAccount(account);
        result.setAmount(account.getLiquidCashAmount());
        result.setStatus(CoreConstant.STATUS_ACTIVE);
        return result;
    }

    private void genSelectedAccountList() {
        if (CommonUtils.isNotEmpty(transferItemList)) {
            for (CaseAccountInfoVO account : accountList) {
                for (TransferToGrOrBeaItemVO item : transferItemList) {
                    if (item.getAcId().equals(account.getCaseAcId())) {
                        selectAccountList.add(account);
                        break;
                    }
                }
            }
        }
    }

    public void calculateTotalAmount() {
        log.info("calculateTotalAmount start");
        transferVO.setTotalAmount(selectAccountList.stream().reduce(BigDecimal.ZERO,
            (sum, item) -> sum = sum.add(item.getLiquidCashAmount()), (a, b) -> a = a.add(b)));
        paymentVoucherVO.getPaymentVoucherBasicInformation().setVoucherTotalAmount(selectAccountList.stream()
            .reduce(BigDecimal.ZERO, (sum, item) -> sum = sum.add(item.getLiquidCashAmount()), (a, b) -> a = a.add(b)));
        log.info("calculateTotalAmount end");
    }

    public void confirm() {
        log.info("confirm start");
        if (CommonUtils.isEmpty(selectAccountList)) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_MUST_SELECT_ONE_TRANSFER_ITEM));
        } else {
            calculateTotalAmount();
            List<PaymentVoucherAccountItemVO> voucherItemVOs = new ArrayList<>();
            for (int i = 0; i < accountList.size(); i++) {
                voucherItemVOs.add(transformAccountToPaymentVoucherItem(accountList.get(i), i));
            }
            paymentVoucherVO.setPaymentVoucherItems(voucherItemVOs);
            paymentVoucherVO.getPaymentVoucherBasicInformation().setStatus(CoreConstant.STATUS_ACTIVE);
            paymentVoucherVO.getPaymentVoucherBasicInformation().setIsHardCopy(false);
            paymentVoucherVO.getPaymentVoucherBasicInformation().setPreparerId(SecurityUtils.getCurrentPostId());
            Integer voucherId = voucherClientService.savePaymentVoucher(paymentVoucherVO);

            if (voucherId != null) {
                VoucherVO temp = new VoucherVO();
                temp.setVoucherId(voucherId);
                transferVO.setVoucher(temp);
                transferVO.setProcessDate(appResourceUtils.getBusinessDate());
                Integer resultTransferId = transferToGrOrBeaClientService.saveTransferToGrOrBea(transferVO);

                if (resultTransferId != null) {
                    selectAccountList.stream()
                        .forEach(item -> transferItemList.add(transformAccountToTransferItem(item)));
                    transferItemList.stream().forEach(item -> {
                        item.setTransferId(resultTransferId);
                        transferToGrOrBeaItemClientService.saveTransferToGrOrBeaItem(item);
                    });
                    Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
                    transferVO = transferToGrOrBeaClientService.getTransferToGrOrBeaDetail(resultTransferId);
                    transferItemList
                        = transferToGrOrBeaItemClientService.findTransferToGrOrBeaItemByTransfer(resultTransferId);
                } else {
                    Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
                }
            } else {
                Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
            }
        }
        log.info("confirm end");
    }

}
