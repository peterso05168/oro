package hk.oro.iefas.web.ledger.chequehandling.incomingcheque.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.ShroffConstant;
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
public class IncomingChequeEditView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer chequeId;

    @Inject
    private ChequeClientService chequeClientService;

    @Inject
    private CurrencyClientService currencyClientService;

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

    @Getter
    @Setter
    private CaseVO caseInfo;

    @Getter
    @Setter
    private Date today;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyBasicInfoVOS;

    @PostConstruct
    public void init() {
        log.info("==============incomingChequeEditView init start=============");
        currencyBasicInfoVOS = currencyClientService.findAll();
        today = new Date();
        chequeId = Faces.getRequestParameter("chequeId", Integer.class);
        caseNumberVO = new CaseNumberVO();
        if (chequeId == null || (chequeVO = chequeClientService.getIncomingChequeDetail(chequeId)) == null) {
            initChequeVO();
        } else {
            caseInfo = chequeVO.getCaseInfo();
            if (caseInfo != null) {
                caseNumberVO.setCaseYear(caseInfo.getCaseYear());
                caseNumberVO.setCaseSequence(caseInfo.getCaseNo());
                if (caseInfo.getCaseType() != null) {
                    caseNumberVO.setCaseType(caseInfo.getCaseType().getCaseTypeCode());
                }
            }
        }
        chequeVO.setChequeTypeId(ShroffConstant.CQ_INCOMING);
        log.info("==============incomingChequeEditView init end==============");
    }

    public void save() {
        log.info("save() start");
        if (caseInfo == null) {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EXIST, MsgParamCodeConstant.CASE_NUMBER));
            return;
        }
        Integer result = chequeClientService.saveIncomingCheque(chequeVO);
        if (result != null) {
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
            chequeVO = chequeClientService.getIncomingChequeDetail(result);
        } else
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
        log.info("save() end");
    }

    private void initChequeVO() {
        chequeVO = new ChequeVO();
        CaseVO caseVO = new CaseVO();
        caseVO.setPost(new PostVO());
        caseVO.setCaseType(new CaseTypeVO());
        chequeVO.setCaseInfo(caseVO);
        chequeVO.setCurcy(new CurrencyVO());
        chequeVO.setStatus(CoreConstant.STATUS_ACTIVE);
    }

    public void searchCaseInfo() {
        log.info("searchCaseInfo() start");
        if (CommonUtils.isNotBlank(caseNumberVO.getCaseSequence()) && CommonUtils.isNotBlank(caseNumberVO.getCaseType())
            && CommonUtils.isNotBlank(caseNumberVO.getCaseYear())) {
            caseInfo = caseClientService.findByCaseNumber(caseNumberVO);
            if (caseInfo != null) {
                chequeVO.setCaseInfo(caseInfo);
            } else {
                CaseVO caseVO = new CaseVO();
                caseVO.setPost(new PostVO());
                chequeVO.setCaseInfo(caseVO);
            }
        }
        log.info("searchCaseInfo() end");
    }
}
