package hk.oro.iefas.web.funds.maintenance.bankaccount.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.vo.BankAccountInfoResultVO;
import hk.oro.iefas.domain.bank.vo.BankInfoVO;
import hk.oro.iefas.domain.bank.vo.SearchBankAccountInfoCriteriaVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.bankaccount.service.BankClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class BankAccountSearchView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private BankClientService bankClientService;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private SearchBankAccountInfoCriteriaVO criteriaDTO = new SearchBankAccountInfoCriteriaVO();

    @Getter
    @Setter
    private LazyDataModel<BankAccountInfoResultVO> bankAccountDataModel;

    @Getter
    @Setter
    private Integer bankInfoId;

    @PostConstruct
    private void init() {
        statusVOs = commonConstantClientService.searchStatusList();
    }

    public void changeStatus() {
        BankInfoVO bankInfoVO = bankClientService.findOne(bankInfoId);
        if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(bankInfoVO.getStatus())) {
            bankInfoVO.setStatus(CoreConstant.STATUS_INACTIVE);
        } else {
            bankInfoVO.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        bankClientService.save(bankInfoVO);
    }

    public void search() {
        log.info("search() start");
        log.info("Criteria: " + criteriaDTO);
        bankAccountDataModel = new SimpleLazyDataModel<BankAccountInfoResultVO, SearchBankAccountInfoCriteriaVO>(
            RequestUriConstant.CLIENT_URI_BANK_ACCOUNT_LIST, criteriaDTO);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        criteriaDTO = new SearchBankAccountInfoCriteriaVO();
        bankAccountDataModel = null;
        log.info("reset() end");
    }
}
