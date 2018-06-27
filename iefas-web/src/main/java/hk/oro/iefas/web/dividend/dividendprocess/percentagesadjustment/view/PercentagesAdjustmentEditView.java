package hk.oro.iefas.web.dividend.dividendprocess.percentagesadjustment.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.dividend.vo.AdjudicationTypeVO;
import hk.oro.iefas.domain.dividend.vo.ApprovedAdjucationResultItemVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleItemVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleVO;
import hk.oro.iefas.domain.dividend.vo.PercentagesAdjustmentVO;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.dividendprocess.percentagesadjustment.service.PercentagesAdjustmentClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
@Slf4j
@Named
@ViewScoped
public class PercentagesAdjustmentEditView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PercentagesAdjustmentClientService percentagesAdjustmentClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private PercentagesAdjustmentVO percentagesAdjustmentVO;

    @Getter
    @Setter
    private CaseNumberVO caseNumberVO;

    @Getter
    @Setter
    private Map<String, ApprovedAdjucationResultItemVO> apprAdjResultItemMap;

    @Getter
    @Setter
    private List<DividendScheduleItemVO> dividendScheduleItemVOs;

    @Getter
    @Setter
    private DividendScheduleVO dividendScheduleVO;

    @PostConstruct
    public void init() {
        log.info("init - start");
        this.caseNumberVO = Faces.getFlashAttribute(DividendWebConstant.PAYMENT_PERCENTAGE_PARAM_CASE_NUMBER);
        Integer adjudicationResultId
            = Faces.getFlashAttribute(DividendWebConstant.PAYMENT_PERCENTAGE_PARAM_ADJRESULTID);
        if (adjudicationResultId != null && adjudicationResultId.intValue() > 0) {
            this.percentagesAdjustmentVO
                = percentagesAdjustmentClientService.searchPercentagesAdjustmentVO(adjudicationResultId);
        }
        endueAppAdjResultItems();
        searchDividentSchedule();
        log.info("init - end");
    }

    public void save() {
        log.info("save - start");
        if (apprAdjResultItemMap != null && !apprAdjResultItemMap.isEmpty()) {
            List<ApprovedAdjucationResultItemVO> appAdjResultItemsList = apprAdjResultItemMap.keySet().stream()
                .map(t -> apprAdjResultItemMap.get(t)).collect(Collectors.toList());
            if (appAdjResultItemsList != null && this.percentagesAdjustmentVO != null) {
                this.percentagesAdjustmentVO.setAppAdjResultItem(appAdjResultItemsList);
                if (percentagesAdjustmentClientService.savePercentagesAdjustment(this.percentagesAdjustmentVO)) {
                    Integer adjudicationResultId = this.percentagesAdjustmentVO.getAdjudicationResultId();
                    this.percentagesAdjustmentVO = null;
                    this.percentagesAdjustmentVO
                        = percentagesAdjustmentClientService.searchPercentagesAdjustmentVO(adjudicationResultId);
                    endueAppAdjResultItems();
                    searchDividentSchedule();
                    Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, MsgCodeConstant.MSG_SAVE_FAIL, ""));
                }
            }
        }
        log.info("save - end");
    }

    public void endueAppAdjResultItems() {
        log.info("endueAppAdjResultItems - start");
        if (this.percentagesAdjustmentVO != null && this.percentagesAdjustmentVO.getAppAdjResultItem() != null) {
            this.apprAdjResultItemMap = new HashMap<String, ApprovedAdjucationResultItemVO>();
            this.percentagesAdjustmentVO.getAppAdjResultItem().forEach(t -> {
                AdjudicationTypeVO adjudicationTypeVO = t.getAdjudicationType();
                if (adjudicationTypeVO != null && !adjudicationTypeVO.getAdjudicationTypeName().trim().isEmpty()) {
                    apprAdjResultItemMap.put(adjudicationTypeVO.getAdjudicationTypeName(), t);
                }
            });
        }
        log.info("endueAppAdjResultItems - end");
    }

    public void searchDividentSchedule() {
        log.info("searchDividentSchedule - start");
        if (this.percentagesAdjustmentVO != null) {
            List<DividendScheduleVO> dividendScheduleVOList = percentagesAdjustmentVO.getDividendSchedule();
            if (dividendScheduleVOList != null) {
                this.dividendScheduleItemVOs = new ArrayList<>();
                dividendScheduleVOList.forEach(t -> {
                    if (t.getDividendScheduleItems() != null) {
                        this.dividendScheduleVO = t;
                        dividendScheduleItemVOs.addAll(t.getDividendScheduleItems());
                    }
                });
                if (this.dividendScheduleItemVOs.size() > 0) {
                    this.dividendScheduleItemVOs = this.dividendScheduleItemVOs.stream()
                        .filter(t -> t.getAdjResult().getAdjucationId()
                            .equals(this.percentagesAdjustmentVO.getAdjudicationResultId())
                            && t.getStatus().equals(DividendConstant.STATUS_CONFIRM))
                        .collect(Collectors.toList());
                }
            }
        }
        log.info("searchDividentSchedule - end");
    }

    public void changeDistributedAmount() {
        log.info("changeDistributedAmount - start");
        if (apprAdjResultItemMap != null && !apprAdjResultItemMap.isEmpty()) {
            this.apprAdjResultItemMap.entrySet().forEach(t -> {
                ApprovedAdjucationResultItemVO appAdjResultItemVO = t.getValue();
                if (appAdjResultItemVO.getAdmittedAmount() != null
                    && appAdjResultItemVO.getAdmittedAmount().compareTo(BigDecimal.ZERO) != 0) {
                    BigDecimal percent = BigDecimal.valueOf(100);
                    BigDecimal percentPaid = appAdjResultItemVO.getAmountPaid()
                        .divide(appAdjResultItemVO.getAdmittedAmount(), 2, RoundingMode.HALF_UP);
                    if (percentPaid.compareTo(percent) > 0) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            appResourceUtils.getMessage(MsgCodeConstant.MSG_CHANGE_DISTRIBUTEAMOUNT_ERROR), ""));
                        return;
                    }
                    appAdjResultItemVO.setPercentPaid(percentPaid);
                }
            });
        }
        log.info("changeDistributedAmount - end");
    }
}
