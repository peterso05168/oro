package hk.oro.iefas.web.dividend.maintenance.orfeetobecharged.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.vo.CaseFeeToBeChargedVO;
import hk.oro.iefas.domain.dividend.vo.CaseFeeTypeVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.maintenance.orfeetobecharged.service.OrFeeToBeChargedClientService;
import hk.oro.iefas.web.dividend.maintenance.orsfees.service.OrFeeClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named(value = "feeToBeChargedEditView")
@ViewScoped
public class OrFeeToBeChargedEditView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private OrFeeClientService oRFeeClientService;

    @Autowired
    private CommonClientService commonClientService;

    @Autowired
    private OrFeeToBeChargedClientService oRFeeToBeChargedClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private List<AnalysisCodeVO> analysisCodeVOs;

    @Getter
    @Setter
    private CaseFeeTypeVO caseFeeTypeVO;

    @Getter
    @Setter
    private List<CaseFeeToBeChargedVO> caseFeeToBeChargedList;

    @Getter
    @Setter
    private CaseFeeToBeChargedVO caseFeeToBeChargedVO;

    private Map<Integer, AnalysisCodeVO> analysisCodeVOMenu;

    @PostConstruct
    public void init() {
        log.info("ORFeeToBeChargedEditView init - start");
        Integer caseFeeTypeId = Faces.getRequestParameter("caseFeeTypeId", Integer.class);
        if (caseFeeTypeId != null && caseFeeTypeId.intValue() > 0) {
            this.caseFeeTypeVO = oRFeeClientService.searchORFeeItemWithAnalysisCodeCharged(caseFeeTypeId);
        }
        toAddShowCaseFeeToBeChargedList();
        this.analysisCodeVOs = commonClientService.searchAnalysisCodeList();

        if (analysisCodeVOs != null) {
            this.analysisCodeVOMenu = new HashMap<>();
            commonClientService.searchAnalysisCodeList().forEach(t -> analysisCodeVOMenu.put(t.getAnalysisCodeId(), t));
        }
        this.caseFeeToBeChargedVO = new CaseFeeToBeChargedVO();
        this.caseFeeToBeChargedVO.setAnalysisCode(analysisCodeVOMenu.get(1));
        log.info("ORFeeToBeChargedEditView init - end");
    }

    public void addAnalysisCodeItem() {
        log.info("addAnalysisCodeItem - start");
        Integer caseFeeToBeChargedId = this.caseFeeToBeChargedVO.getCaseFeeToBeChargedId();
        if (caseFeeToBeChargedId != null && caseFeeToBeChargedId > 0) {
            List<CaseFeeToBeChargedVO> caseFeeToBeChargedVOList = this.caseFeeTypeVO.getCaseFeeToBeChargeds();
            Integer analysisCodeId = this.caseFeeToBeChargedVO.getAnalysisCode().getAnalysisCodeId();
            if (caseFeeToBeChargedVOList.contains(this.caseFeeToBeChargedVO)) {
                hideComponent("editDialog");
                return;
            }
            Integer checkResult = checkAnalysisCodeLink(analysisCodeId);
            if (checkResult >= 0) {
                this.caseFeeTypeVO.getCaseFeeToBeChargeds().get(checkResult).setStatus(CoreConstant.STATUS_ACTIVE);
                for (CaseFeeToBeChargedVO c : this.caseFeeTypeVO.getCaseFeeToBeChargeds()) {
                    if (c.getCaseFeeToBeChargedId().equals(this.caseFeeToBeChargedVO.getCaseFeeToBeChargedId())) {
                        c.setStatus(CoreConstant.STATUS_INACTIVE);
                        break;
                    }
                }
                toAddShowCaseFeeToBeChargedList();
                hideComponent("editDialog");
                return;
            } else if (checkResult.intValue() == -1) {
                AnalysisCodeVO analysisCodeVO = null;
                for (AnalysisCodeVO a : analysisCodeVOs) {
                    if (a.getAnalysisCodeId().equals(analysisCodeId)) {
                        analysisCodeVO = a;
                        break;
                    }
                }
                for (CaseFeeToBeChargedVO c : this.caseFeeTypeVO.getCaseFeeToBeChargeds()) {
                    if (c.getCaseFeeToBeChargedId().equals(caseFeeToBeChargedId)) {
                        c.setAnalysisCode(analysisCodeVO);
                        break;
                    }
                }
                toAddShowCaseFeeToBeChargedList();
                hideComponent("editDialog");
                return;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_CREATE_OFEE_ANALYSIS_CODE_ERROR), ""));
            }
        } else {
            Integer analysisCodeId = this.getCaseFeeToBeChargedVO().getAnalysisCode().getAnalysisCodeId();
            if (!addORFeeItemWithAnalysisCodeChargedValidate(analysisCodeId)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_CREATE_OFEE_ANALYSIS_CODE_ERROR), ""));
            } else {
                boolean flag = true;
                for (CaseFeeToBeChargedVO c : caseFeeTypeVO.getCaseFeeToBeChargeds()) {
                    if (c.getAnalysisCode().getAnalysisCodeId().equals(analysisCodeId)) {
                        c.setStatus(CoreConstant.STATUS_ACTIVE);
                        this.caseFeeToBeChargedList.add(c);
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (AnalysisCodeVO a : analysisCodeVOs) {
                        if (a.getAnalysisCodeId().equals(analysisCodeId)) {
                            this.caseFeeToBeChargedVO.setAnalysisCode(a);
                            this.caseFeeToBeChargedVO.setStatus(CoreConstant.STATUS_ACTIVE);
                            break;
                        }
                    }
                    caseFeeToBeChargedList.add(this.caseFeeToBeChargedVO);
                    caseFeeTypeVO.getCaseFeeToBeChargeds().add(this.caseFeeToBeChargedVO);
                }
                hideComponent("editDialog");
            }
        }
        log.info("addAnalysisCodeItem - end");
    }

    public void toEditAnalysis() {
        log.info("toEditAnalysis - start");
        Integer caseFeeToBeChargedId = Faces.getRequestParameter("caseFeeToBeChargedId", Integer.class);
        if (caseFeeToBeChargedId != null && caseFeeToBeChargedId.intValue() > 0) {
            CaseFeeToBeChargedVO caseFeCharged
                = DataUtils.copyProperties(this.caseFeeToBeChargedVO, CaseFeeToBeChargedVO.class);
            this.caseFeeToBeChargedVO = caseFeCharged;
            showComponent("editDialog");
        } else {
            this.caseFeeToBeChargedVO = new CaseFeeToBeChargedVO();
            this.caseFeeToBeChargedVO.setAnalysisCode(new AnalysisCodeVO());
        }
        log.info("toEditAnalysis - end");
    }

    public void saveORFeeItemWithAnalysisCodeCharged() {
        log.info("save - start");
        if (this.caseFeeTypeVO != null) {
            boolean result = oRFeeToBeChargedClientService.saveORFeeItemWithAnalysisCodeCharged(this.caseFeeTypeVO);
            if (result) {
                Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS, ""));
            }
        }
        log.info("save - end ");
    }

    public boolean addORFeeItemWithAnalysisCodeChargedValidate(Integer analysisId) {
        log.info("saveORFeeItemWithAnalysisCodeChargedValidate - start and analysisId=" + analysisId);
        boolean result = true;
        if (this.caseFeeToBeChargedList != null) {
            for (CaseFeeToBeChargedVO c : this.caseFeeToBeChargedList) {
                if (c.getAnalysisCode().getAnalysisCodeId().equals(analysisId)) {
                    result = false;
                    break;
                }
            }
        }
        log.info("saveORFeeItemWithAnalysisCodeChargedValidate - end and result =" + result);
        return result;
    }

    public Integer checkAnalysisCodeLink(Integer analysisId) {
        log.info("checkAnalysisCodeLink - start and analysisId=" + analysisId);
        Integer result = -1;
        List<CaseFeeToBeChargedVO> caseFeeToBeChargedVOList = this.caseFeeTypeVO.getCaseFeeToBeChargeds();
        if (this.caseFeeTypeVO != null && caseFeeToBeChargedVOList != null) {
            for (CaseFeeToBeChargedVO c : caseFeeToBeChargedVOList) {
                if (c.getAnalysisCode().getAnalysisCodeId().equals(analysisId)
                    && c.getStatus().equals(CoreConstant.STATUS_ACTIVE)) {
                    result = -2;
                    break;
                }
                if (c.getAnalysisCode().getAnalysisCodeId().equals(analysisId)) {
                    result = caseFeeToBeChargedVOList.indexOf(c);
                    break;
                }
            }
        }
        log.info("checkAnalysisCodeLink - end and result =" + result);
        return result;
    }

    public void changeStatus() {
        log.info("changeStatus - start");
        Integer caseFeeToBeChargedId = Faces.getRequestParameter("caseFeeToBeChargedId", Integer.class);
        Integer indexOfCaseFeeCharge = caseFeeTypeVO.getCaseFeeToBeChargeds().indexOf(this.caseFeeToBeChargedVO);
        if (caseFeeToBeChargedId != null && caseFeeToBeChargedId.intValue() > 0) {
            caseFeeTypeVO.getCaseFeeToBeChargeds().get(indexOfCaseFeeCharge).setStatus(CoreConstant.STATUS_INACTIVE);
        } else {
            caseFeeTypeVO.getCaseFeeToBeChargeds()
                .remove(caseFeeTypeVO.getCaseFeeToBeChargeds().get(indexOfCaseFeeCharge));
        }
        toAddShowCaseFeeToBeChargedList();
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_DELETE_SUCCESS, ""));
        log.info("changeStatus - end");
    }

    public void toAddShowCaseFeeToBeChargedList() {
        log.info("toAddShowCaseFeeToBeChargedList - start");
        if (this.caseFeeToBeChargedList != null) {
            caseFeeToBeChargedList.clear();
        }
        if (this.caseFeeTypeVO != null && this.caseFeeTypeVO.getCaseFeeToBeChargeds() != null) {
            this.caseFeeToBeChargedList = this.caseFeeTypeVO.getCaseFeeToBeChargeds().stream()
                .filter(t -> t.getStatus().equals(CoreConstant.STATUS_ACTIVE)).collect(Collectors.toList());
        }
        log.info("toAddShowCaseFeeToBeChargedList - end");
    }

    public void changeAnalysisName() {
        log.info("changeAnalysisName - start");
        Integer analysisId = this.caseFeeToBeChargedVO.getAnalysisCode().getAnalysisCodeId();
        this.caseFeeToBeChargedVO.setAnalysisCode(null);
        if (analysisId != null) {
            AnalysisCodeVO analysisCodeVO = analysisCodeVOMenu.get(analysisId);
            if (analysisCodeVO != null) {
                this.caseFeeToBeChargedVO.setAnalysisCode(analysisCodeVO);
            }
        }
        log.info("changeAnalysisName - end");
    }
}
