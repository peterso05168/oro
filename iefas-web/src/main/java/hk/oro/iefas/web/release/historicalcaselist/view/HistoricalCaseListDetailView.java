package hk.oro.iefas.web.release.historicalcaselist.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.PrivilegeConstant;
import hk.oro.iefas.core.constant.WorkFlowAction;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.release.vo.HistoricalCaseListDetailVO;
import hk.oro.iefas.domain.release.vo.RelHistListItemVO;
import hk.oro.iefas.domain.release.vo.RelHistListVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherAccountItemVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherBasicInformationVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherDetailVO;
import hk.oro.iefas.domain.system.vo.HolidayVO;
import hk.oro.iefas.domain.system.vo.ImportHolidayResultVO;
import hk.oro.iefas.domain.system.vo.SearchHolidayVO;
import hk.oro.iefas.domain.system.vo.SysWfInitialStatusVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.release.historicalcaselist.service.HistoricalCaseListClientService;
import hk.oro.iefas.web.system.systemadministration.holiday.service.HolidayClientService;
import hk.oro.iefas.web.system.systemadministration.holiday.view.HolidayLazyDataModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2487 $ $Date: 2018-05-18 17:54:15 +0800 (Fri, 18 May 2018) $
 * @author $Author: cwen $
 */
@Slf4j
@Named
@ViewScoped
public class HistoricalCaseListDetailView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Autowired
    private HolidayClientService holidayClientService;

    @Autowired
    private HistoricalCaseListClientService historicalCaseListClientService;

    @Inject
    private CommonConstantClientService commonConstantClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private SearchHolidayVO criteria = new SearchHolidayVO();

    @Getter
    @Setter
    private HolidayVO addHolidayVO;

    @Getter
    @Setter
    private RelHistListVO histCaseListBasicInformation;

    @Getter
    @Setter
    private RelHistListItemVO histCaseListItem;

    @Getter
    @Setter
    private List<RelHistListItemVO> histCaseListItemList;

    @Getter
    @Setter
    private List<RelHistListItemVO> deletehistCaseListItemList;

    @Getter
    @Setter
    private LazyDataModel<HolidayVO> holidayResultDataModel;

    @Getter
    @Setter
    private Integer holidayId;

    @Getter
    @Setter
    private Integer historicalCaseListId;

    @Getter
    @Setter
    private Date today;

    @Getter
    @Setter
    private String dateFormat;

    @Setter
    @Getter
    private UploadedFile uploadedFile;

    @Setter
    @Getter
    private ImportHolidayResultVO uploadResultVo;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @PostConstruct
    private void init() {
        log.info("====== HistoricalCaseListDetailView init ======");

        this.historicalCaseListId = Faces.getRequestParameter("historicalCaseListId", Integer.class);

        log.debug("historicalCaseListId: " + this.historicalCaseListId);

        if (this.historicalCaseListId != null) {
            HistoricalCaseListDetailVO historicalCaseListDetailVO
                = this.historicalCaseListClientService.findHistCaseListDetail(this.historicalCaseListId);

            this.histCaseListBasicInformation = historicalCaseListDetailVO.getHistoricalCaseListInformation();
            this.histCaseListItemList = historicalCaseListDetailVO.getHistoricalCaseListDetailItems();

            /*this.currentWorkFlowRule = this.receiptVoucherSysWorkFlowRuleClientService.
                findSysWorkFlowRule(this.receiptVoucherBasicInformation.getWorkflowId());
            
            if (receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getCurrencyId() != null) {
                this.caseBtnDisable = false;
            }*/
        } else {
            this.histCaseListBasicInformation = new RelHistListVO();
            // this.receiptVoucherBasicInformation.setPreparerId(SecurityUtils.getCurrentPostId());

            // this.sysWfInitialStatusVO =
            // this.receiptVoucherSysWfInitialStatusClientService.findByPrivilegeCode(PrivilegeConstant.PRIVILEGE_RVM);
            // this.receiptVoucherBasicInformation.setStatus(this.sysWfInitialStatusVO.getStatus().getCodeValue());
            this.histCaseListItemList = new ArrayList<RelHistListItemVO>();

            // this.caseBtnDisable = true;
        }

        this.histCaseListItem = new RelHistListItemVO();
        this.deletehistCaseListItemList = new ArrayList<RelHistListItemVO>();

        statusVOs = commonConstantClientService.searchStatusList();
        today = DateUtils.getCurrentDay();
        addHolidayVO = new HolidayVO();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        criteria.setYear(cal.get(Calendar.YEAR));
        dateFormat = appResourceUtils.getDateFormat();
    }

    public void deleteHistCaseListItem() {
        log.info("deleteHistCaseListItem() start");
        if (this.histCaseListItem != null) {
            this.histCaseListItem.setStatus(CoreConstant.STATUS_INACTIVE);
            if (this.histCaseListItem.getHistCaseListItemId() != null) {
                this.deletehistCaseListItemList.add(this.histCaseListItem);
            }
            this.histCaseListItemList.remove(this.histCaseListItem);

            Integer voucherItemNo = this.histCaseListItem.getHistCaseListItemId();

            for (int i = voucherItemNo - 1; i < this.histCaseListItemList.size(); i++) {
                this.histCaseListItemList.get(i).setHistCaseListItemId(i + 1);;
            }
        }
        this.histCaseListItem = new RelHistListItemVO();
        log.info("deleteHistCaseListItem() end");
    }

    public void saveAccount() {
        log.info("saveAccount() start");
        /*if (this.histCaseListItem.getAccountId() == null) {
            Messages.addError("accountCreateForm:msgs",
                this.appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EXIST, MsgParamCodeConstant.CASE_ACCOUNT));
            return;
        }*/

        this.histCaseListItem.setStatus(CoreConstant.STATUS_ACTIVE);
        if (this.histCaseListItem.getHistCaseListItemId() == null) {
            this.histCaseListItem.setHistCaseListItemId(this.histCaseListItemList.size() + 1);
        }
        if (this.histCaseListItem.getHistCaseListItemNo() == null) {
            this.histCaseListItem.setHistCaseListItemNo(this.histCaseListItemList.size() + 1);
        }
        if (!this.histCaseListItemList.contains(this.histCaseListItem)) {
            this.histCaseListItemList.add(this.histCaseListItem);
        }
        this.histCaseListItem = new RelHistListItemVO();

        super.hideComponent("addDialog");
        log.info("saveAccount() end");
    }

    public void save() {
        log.info("save() start");

        HistoricalCaseListDetailVO historicalCaseListDetailVO = new HistoricalCaseListDetailVO();
        historicalCaseListDetailVO.setHistoricalCaseListInformation(this.histCaseListBasicInformation);
        historicalCaseListDetailVO.setHistoricalCaseListDetailItems(new ArrayList<RelHistListItemVO>());

        if (this.histCaseListItemList == null || this.histCaseListItemList.size() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_VOUCHER_ACCOUNT_ITEM_REQUIRED), ""));
            return;
        }

        // BigDecimal totalAmount = new BigDecimal("0");
        if (CommonUtils.isNotEmpty(this.histCaseListItemList)) {
            historicalCaseListDetailVO.getHistoricalCaseListDetailItems().addAll(this.histCaseListItemList);
            /*for (RelHistListItemVO item : this.histCaseListItemList) {
                totalAmount = totalAmount.add(item.getVoucherAmount());
            }*/
        }
        // this.histCaseListBasicInformation.setVoucherTotalAmount(totalAmount);

        if (CommonUtils.isNotEmpty(this.deletehistCaseListItemList)) {
            historicalCaseListDetailVO.getHistoricalCaseListDetailItems().addAll(deletehistCaseListItemList);
        }

        Integer historicalCaseListId = null;
        if (this.historicalCaseListId != null) {
            historicalCaseListId
                = this.historicalCaseListClientService.saveHistoricalCaseList(historicalCaseListDetailVO);
        } else {
            /*SysWfInitialStatusVO sysWfInitialStatusVO = this.receiptVoucherSysWfInitialStatusClientService
                .findByPrivilegeCode(PrivilegeConstant.PRIVILEGE_RVM);
            
            SysWorkFlowRuleVO saveWorkflowRule = null;
            for (SysWorkFlowRuleVO workflowRule : this.sysWorkFlowRuleVOs) {
                WorkFlowAction workFlowAction = WorkFlowAction.getByCode(workflowRule.getAction().getCodeValue());
                if (workFlowAction != null && workFlowAction.equals(WorkFlowAction.Save)
                    && workflowRule.getBeforeStatus().getCodeValue()
                        .equals(sysWfInitialStatusVO.getStatus().getCodeValue())
                    && workflowRule.getAfterStatus().getCodeValue()
                        .equals(sysWfInitialStatusVO.getStatus().getCodeValue())) {
                    saveWorkflowRule = workflowRule;
                    break;
                }
            }*/

            /*if (saveWorkflowRule != null) {
                this.histCaseListBasicInformation.setWorkflowId(saveWorkflowRule.getRuleId());
            }*/

            historicalCaseListId
                = this.historicalCaseListClientService.saveHistoricalCaseList(historicalCaseListDetailVO);
        }
        this.loadDetail(historicalCaseListId);

        Messages.addGlobalInfo(this.appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        log.info("save() end");
    }

    private void loadDetail(Integer voucherId) {
        HistoricalCaseListDetailVO historicalCaseListDetailVO
            = this.historicalCaseListClientService.findHistCaseListDetail(this.historicalCaseListId);
        this.histCaseListBasicInformation = historicalCaseListDetailVO.getHistoricalCaseListInformation();
        this.histCaseListItemList = historicalCaseListDetailVO.getHistoricalCaseListDetailItems();
    }

    public void reset() {
        log.info("reset() start");
        criteria = new SearchHolidayVO();
        holidayResultDataModel = null;
        log.info("reset() end");
    }
}
