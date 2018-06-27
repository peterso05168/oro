package hk.oro.iefas.web.ledger.chequehandling.chequeregistration.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.bank.vo.CurrencyVO;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.common.vo.CaseTypeVO;
import hk.oro.iefas.domain.organization.vo.PostVO;
import hk.oro.iefas.domain.shroff.vo.ChequeVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import hk.oro.iefas.web.ledger.chequehandling.incomingcheque.service.ChequeClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@ViewScoped
public class ChequeRegistrationView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ChequeClientService chequeClientService;

    @Getter
    @Setter
    private ChequeVO chequeVO;

    @Getter
    @Setter
    private CaseNumberVO caseNumberVO;

    @Inject
    private CaseClientService caseClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private CurrencyClientService currencyClientService;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyBasicInfoVOList;
    @Getter
    @Setter
    private CaseVO caseInfo;

    @Getter
    @Setter
    private Date today;

    @Getter
    @Setter
    private StreamedContent downloadFile;

    @Getter
    @Setter
    private UploadedFile uploadedFile;

    @Getter
    @Setter
    private List<ChequeVO> chequeVOList;

    @PostConstruct
    public void init() {
        log.info("==============ChequeRegistrationView init start=============");
        currencyBasicInfoVOList = currencyClientService.findAll();
        today = new Date();
        caseNumberVO = new CaseNumberVO();
        chequeVO = new ChequeVO();
        CaseVO caseVO = new CaseVO();
        caseVO.setPost(new PostVO());
        caseVO.setCaseType(new CaseTypeVO());
        chequeVO.setCaseInfo(caseVO);
        chequeVO.setCurcy(new CurrencyVO());
        chequeVO.setStatus(CoreConstant.STATUS_ACTIVE);
        chequeVO.setChequeTypeId(1);
        log.info("==============incomingChequeEditView init end==============");
    }

    public void create() {
        log.info("save() start");
        if (caseInfo != null) {
            Integer result = chequeClientService.saveIncomingCheque(chequeVO);
            if (result != null)
                Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
            else
                Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
        } else {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EXIST, MsgParamCodeConstant.CASE_NUMBER));
        }
        log.info("save() end");
    }

    public void searchCaseInfo() {
        log.info("searchCaseInfo() start");
        if (CommonUtils.isNotBlank(caseNumberVO.getCaseSequence()) && CommonUtils.isNotBlank(caseNumberVO.getCaseType())
            && CommonUtils.isNotBlank(caseNumberVO.getCaseYear())) {
            caseInfo = caseClientService.findByCaseNumber(caseNumberVO);
            if (caseInfo == null) {
                CaseVO caseVO = new CaseVO();
                caseVO.setPost(new PostVO());
                chequeVO.setCaseInfo(caseVO);
            } else {
                chequeVO.setCaseInfo(caseInfo);
            }
        }
        log.info("searchCaseInfo() end");
    }

    public void upload() {
        log.info("upload() start");
        log.info("upload() end");
    }

    public void confirm() {
        log.info("confirm() start");
        log.info("confirm() end");
    }

    public void reset() {
        log.info("reset() start");
        log.info("reset() end");
    }
}
