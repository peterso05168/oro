package hk.oro.iefas.web.ledger.deposithandling.enquiry.view;

import java.io.Serializable;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.shroff.vo.ReceiveDepositResultVO;
import hk.oro.iefas.domain.shroff.vo.ReceiveDepositVO;
import hk.oro.iefas.domain.shroff.vo.SearchReceiveDepositVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import hk.oro.iefas.web.ledger.deposithandling.enquiry.service.ReceiveDepositClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@ViewScoped
public class ReceiveDepositEnquirySearchView extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private SearchReceiveDepositVO criteria;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyBasicInfoVOS;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private ReceiveDepositClientService receiveDepositClientService;

    @Getter
    @Setter
    private LazyDataModel<ReceiveDepositResultVO> lazyDataModel;

    @Getter
    @Setter
    private List<StatusVO> statusVOS;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private Map<Integer, CaseVO> cases;

    private Set<Integer> errorDepositIds;

    @Inject
    private CaseClientService caseClientService;

    @Getter
    @Setter
    private String caseNumber;

    @PostConstruct
    public void init() {
        log.info("================ EnquirySearchView init start ===============");
        cases = new HashMap<>();
        errorDepositIds = new HashSet<>();
        criteria = new SearchReceiveDepositVO();
        currencyBasicInfoVOS = currencyClientService.findAll();
        statusVOS = appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.DPS.name());
        log.info("================ EnquirySearchView init end ================");
    }

    public void search() {
        log.info("search start");
        lazyDataModel
            = new SimpleLazyDataModel<>(RequestUriConstant.CLIENT_URI_RECEIPT_DEPOSIT_SEARCH_RECEIPT_DEPOSIT, criteria);
        log.info("search end");
    }

    public void reset() {
        log.info("reset start");
        criteria = new SearchReceiveDepositVO();
        lazyDataModel = null;
        log.info("reset end");
    }

    public void update() {
        log.info("update start");
        if (!errorDepositIds.isEmpty()) {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EXIST, MsgParamCodeConstant.CASE_NUMBER));
            return;
        }
        Set<Integer> depositIds = cases.keySet();
        for (Integer id : depositIds) {
            ReceiveDepositVO receiveDepositVO = receiveDepositClientService.getReceiveDepositDetail(id);
            if (receiveDepositVO != null) {
                receiveDepositVO.setCaseInfo(cases.get(id));
                receiveDepositClientService.saveReceiveDeposit(receiveDepositVO);
            }
        }
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        log.info("update end");
    }

    public void searchCaseInfo(Integer depositId) {
        log.info("searchCaseInfo start");
        if (CommonUtils.isNotBlank(caseNumber)) {
            CaseVO caseVO = caseClientService.findByWholeCaseNumber(caseNumber);
            if (caseVO != null) {
                cases.put(depositId, caseVO);
                errorDepositIds.remove(depositId);
            } else {
                cases.remove(depositId);
                errorDepositIds.add(depositId);
            }
        } else {
            errorDepositIds.remove(depositId);
            cases.remove(depositId);
        }
        log.info("searchCaseInfo end");
    }
}