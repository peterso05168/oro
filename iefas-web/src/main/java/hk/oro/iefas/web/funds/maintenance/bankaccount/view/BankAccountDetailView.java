package hk.oro.iefas.web.funds.maintenance.bankaccount.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.CloseEvent;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.bank.vo.BankInfoVO;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.bank.vo.FreeBankTransferVO;
import hk.oro.iefas.domain.bank.vo.LeapYearParameterVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.bankaccount.service.BankClientService;
import hk.oro.iefas.web.funds.maintenance.bankaccount.view.validator.BankAccountValidator;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2500 $ $Date: 2018-05-21 10:24:29 +0800 (週一, 21 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class BankAccountDetailView extends BaseBean {
    private static final long serialVersionUID = 1L;

    @Inject
    private BankClientService bankClientService;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private BankAccountValidator validator;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private BankInfoVO bankInfoVO;

    @Getter
    @Setter
    private LeapYearParameterVO leapYearParameterVO;

    @Getter
    @Setter
    private FreeBankTransferVO freeBankTransferVO;

    @Getter
    @Setter
    private Integer bankInfoId;

    @Getter
    @Setter
    private String curyId;

    @Getter
    @Setter
    private String transferBankId;

    @Getter
    @Setter
    private Map<String, String> currencyBasicInfos;
    private Map<String, CurrencyBasicInfoVO> tempCurrencyBasicInfos;

    @Getter
    @Setter
    private Map<String, String> bankBasics;
    private Map<String, BankInfoVO> tempBankBasics;

    @Getter
    @Setter
    private List<LeapYearParameterVO> filteredLeapYearParameters;
    @Getter
    @Setter
    private List<FreeBankTransferVO> filteredFreeBankTransfers;

    @PostConstruct
    private void init() {
        log.info("======BankAccountEditView init======");

        bankInfoId = Faces.getRequestParameter("bankInfoId", Integer.class);
        log.info("bankInfoId: " + bankInfoId);
        if (bankInfoId != null && bankInfoId.intValue() > 0) {
            bankInfoVO = bankClientService.findOne(bankInfoId);
            filterLeapYearParameters();
            filterFreeBankTransfer();
        } else {
            bankInfoVO = new BankInfoVO();
        }
        leapYearParameterVO = new LeapYearParameterVO();
        freeBankTransferVO = new FreeBankTransferVO();
        freeBankTransferVO.setBankInfo(new BankInfoVO());
        List<CurrencyBasicInfoVO> currencies = currencyClientService.findAll();
        currencyBasicInfos = new TreeMap<>();
        tempCurrencyBasicInfos = new HashMap<>();
        if (CommonUtils.isNotEmpty(currencies)) {
            currencies.stream().forEach(currency -> {
                currencyBasicInfos.put(currency.getCurcyName(), String.valueOf(currency.getCurcyId()));
                tempCurrencyBasicInfos.put(String.valueOf(currency.getCurcyId()), currency);
            });
        }

        List<BankInfoVO> banks = bankClientService.findAll();
        bankBasics = new TreeMap<>();
        tempBankBasics = new HashMap<>();
        if (CommonUtils.isNotEmpty(banks)) {
            banks.stream().forEach(bank -> {
                if (!bank.getBankInfoId().equals(bankInfoId)) {
                    bankBasics.put(bank.getBankName(), String.valueOf(bank.getBankInfoId()));
                    tempBankBasics.put(String.valueOf(bank.getBankInfoId()), bank);
                }
            });
        }

    }

    private void filterLeapYearParameters() {
        filteredLeapYearParameters = new ArrayList<>();
        for (LeapYearParameterVO leapYearParameterVO : bankInfoVO.getLeapYearParameters()) {
            if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(leapYearParameterVO.getStatus())) {
                filteredLeapYearParameters.add(leapYearParameterVO);
            }
        }
    }

    private void filterFreeBankTransfer() {
        filteredFreeBankTransfers = new ArrayList<>();
        for (FreeBankTransferVO freeBankTransferVO : bankInfoVO.getFreeBankTransfers()) {
            if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(freeBankTransferVO.getStatus())) {
                filteredFreeBankTransfers.add(freeBankTransferVO);
            }
        }
    }

    public void changeAvailableRoom() {
        if (bankInfoId == null || bankInfoId.intValue() <= 0) {
            this.bankInfoVO.setAvaiableRoom(this.bankInfoVO.getBankDepositlimit());
        }
    }

    public void save() {
        log.info("save() start " + this.bankInfoVO);

        if (validator.validateSaveBankAccountInfo()) {
            Integer bankInfoId = bankClientService.save(this.bankInfoVO);

            this.bankInfoVO = bankClientService.findOne(bankInfoId);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        }

        log.info("save() end " + this.bankInfoVO);
    }

    public void addCalculation() {
        boolean hasError = false;
        if (filteredLeapYearParameters != null && filteredLeapYearParameters.size() > 0) {
            for (LeapYearParameterVO leapYearParameterVO : filteredLeapYearParameters) {
                if (!this.leapYearParameterVO.equals(leapYearParameterVO)
                    && Integer.valueOf(curyId).equals(leapYearParameterVO.getCurrency().getCurcyId())) {
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Calculation is duplicated.", ""));
                    hasError = true;
                    break;
                }
            }
        }
        if (!hasError) {
            List<LeapYearParameterVO> leapYearParameters = this.bankInfoVO.getLeapYearParameters();
            if (CommonUtils.isEmpty(leapYearParameters)) {
                leapYearParameters = new ArrayList<LeapYearParameterVO>();
                this.bankInfoVO.setLeapYearParameters(leapYearParameters);
            }
            if (!leapYearParameters.contains(this.leapYearParameterVO)) {
                this.leapYearParameterVO.setStatus(CoreConstant.STATUS_ACTIVE);
                this.leapYearParameterVO.setCurrency(tempCurrencyBasicInfos.get(curyId));
                leapYearParameters.add(this.leapYearParameterVO);
            } else {
                for (LeapYearParameterVO leapYearParameterVO : leapYearParameters) {
                    if (leapYearParameterVO.equals(this.leapYearParameterVO)) {
                        leapYearParameterVO.setCurrency(tempCurrencyBasicInfos.get(curyId));
                    }
                }
            }
            filterLeapYearParameters();
            hideComponent("calculationDialog");
        }
    }

    public void changeBank() {
        BankInfoVO bankInfo = tempBankBasics.get(transferBankId);
        freeBankTransferVO.setBankInfo(bankInfo);
    }

    public void addBankTransfer() {
        boolean hasError = false;
        if (transferBankId == null || "".equals(transferBankId)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bank: Validation Error: Value is required.", ""));
            hasError = true;
        } else {
            if (filteredFreeBankTransfers != null && filteredFreeBankTransfers.size() > 0) {
                for (FreeBankTransferVO freeBankTransferVO : filteredFreeBankTransfers) {
                    if (!this.freeBankTransferVO.equals(freeBankTransferVO)
                        && Integer.valueOf(transferBankId).equals(freeBankTransferVO.getBankInfo().getBankInfoId())) {
                        FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bank is duplicated.", ""));
                        hasError = true;
                        break;
                    }
                }
            }
        }
        if (!hasError) {
            List<FreeBankTransferVO> freeBankTransfers = this.bankInfoVO.getFreeBankTransfers();
            if (CommonUtils.isEmpty(freeBankTransfers)) {
                freeBankTransfers = new ArrayList<>();
                this.bankInfoVO.setFreeBankTransfers(freeBankTransfers);
            }
            if (!freeBankTransfers.contains(this.freeBankTransferVO)) {
                this.freeBankTransferVO.setStatus(CoreConstant.STATUS_ACTIVE);
                this.freeBankTransferVO.setBankInfo(tempBankBasics.get(transferBankId));
                freeBankTransfers.add(this.freeBankTransferVO);
            }
            filterFreeBankTransfer();
            hideComponent("bankDialog");
        }
    }

    public void calculationHandleClose(CloseEvent event) {
        log.info("calculationHandleClose() start ");
        leapYearParameterVO = new LeapYearParameterVO();
        curyId = null;
        log.info("calculationHandleClose() end ");
    }

    public void bankTransferHandleClose(CloseEvent event) {
        log.info("bankTransferHandleClose() start ");
        this.freeBankTransferVO = new FreeBankTransferVO();
        transferBankId = null;
        log.info("bankTransferHandleClose() end ");
    }

    public void deleteCalculation() {
        List<LeapYearParameterVO> leapYearParameters = bankInfoVO.getLeapYearParameters();
        for (LeapYearParameterVO leapYearParameterVO : leapYearParameters) {
            if (leapYearParameterVO.equals(this.leapYearParameterVO)) {
                leapYearParameterVO.setStatus(CoreConstant.STATUS_INACTIVE);
            }
        }
        filterLeapYearParameters();
        leapYearParameterVO = new LeapYearParameterVO();
    }

    public void deleteBankTransfer() {
        List<FreeBankTransferVO> freeBankTransferVOs = bankInfoVO.getFreeBankTransfers();
        for (FreeBankTransferVO freeBankTransferVO : freeBankTransferVOs) {
            if (freeBankTransferVO.equals(this.freeBankTransferVO)) {
                freeBankTransferVO.setStatus(CoreConstant.STATUS_INACTIVE);
            }
        }
        filterFreeBankTransfer();
        freeBankTransferVO = new FreeBankTransferVO();
    }
}
