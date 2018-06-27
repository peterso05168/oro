package hk.oro.iefas.web.funds.investment.bankrate.view;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.bank.vo.BankRateVO;
import hk.oro.iefas.domain.bank.vo.CreateUploadBankRateVO;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.bank.vo.SearchUploadBankRateCriteriaVO;
import hk.oro.iefas.domain.bank.vo.UploadBankRateVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.investment.bankrate.service.BankRateClientService;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import lombok.Getter;
import lombok.Setter;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Named
@ViewScoped
public class BankRateSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = -5531821741578682751L;

    @Inject
    private BankRateClientService bankRateClientService;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private LazyDataModel<BankRateVO> lazyDataModel;

    @Getter
    @Setter
    private SearchUploadBankRateCriteriaVO searchCriteria = new SearchUploadBankRateCriteriaVO();

    @Getter
    @Setter
    private CreateUploadBankRateVO createUploadBankRateVO = new CreateUploadBankRateVO();

    @Getter
    private Map<String, String> currencyTypes = new LinkedHashMap<>();

    @PostConstruct
    private void init() {
        createUploadBankRateVO.setCurrencyBasicInfo(new CurrencyBasicInfoVO());

        List<CurrencyBasicInfoVO> currencyBasicInfoVOs = currencyClientService.findAll();
        if (CommonUtils.isNotEmpty(currencyBasicInfoVOs)) {
            currencyTypes.clear();
            for (CurrencyBasicInfoVO currencyBasicInfoVO : currencyBasicInfoVOs) {
                currencyTypes.put(currencyBasicInfoVO.getCurcyName(),
                    String.valueOf(currencyBasicInfoVO.getCurcyId().intValue()));
            }
        }
    }

    public void search() {
        lazyDataModel = new SimpleLazyDataModel<BankRateVO, SearchUploadBankRateCriteriaVO>(
            RequestUriConstant.CLIENT_URI_BANK_RATE_LIST, searchCriteria);
    }

    public void reset() {
        searchCriteria = new SearchUploadBankRateCriteriaVO();
        lazyDataModel = null;
    }

    public String createUploadBankRate() {
        Integer bankRateId = this.bankRateClientService.createUploadBankRate(this.createUploadBankRateVO);
        Faces.setFlashAttribute("bankRateId", bankRateId);
        return EDIT_PAGE;
    }

    public void deleteBankRate(BankRateVO bankRateVO) {
        UploadBankRateVO uploadBankRateVO = new UploadBankRateVO();
        uploadBankRateVO.setBankRateId(bankRateVO.getBankRateId());
        uploadBankRateVO.setStatus(CoreConstant.STATUS_INACTIVE);
        uploadBankRateVO.setVersionNo(bankRateVO.getVersionNo());
        Integer bankRateId = bankRateClientService.saveDailyBankRateList(uploadBankRateVO);
        if (bankRateId != null && bankRateId > 0) {
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        }
    }

}
