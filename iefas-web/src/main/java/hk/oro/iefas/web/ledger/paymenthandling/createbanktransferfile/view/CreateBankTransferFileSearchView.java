package hk.oro.iefas.web.ledger.paymenthandling.createbanktransferfile.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.shroff.vo.BankTransferItemResultVO;
import hk.oro.iefas.domain.shroff.vo.SearchBankTransferItemVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3261 $ $Date: 2018-06-22 17:53:57 +0800 (週五, 22 六月 2018) $
 * @author $Author: marvel.ma $
 */
@ViewScoped
@Slf4j
@Named
public class CreateBankTransferFileSearchView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private SearchBankTransferItemVO criteria;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyBasicInfoVOs;

    @Getter
    @Setter
    private LazyDataModel<BankTransferItemResultVO> lazyDataModel;

    @Getter
    @Setter
    private List<BankTransferItemResultVO> selectedItems;

    @PostConstruct
    public void init() {
        log.info("=============== CreateBankTransferFileSearchView init start =================");
        criteria = new SearchBankTransferItemVO();
        currencyBasicInfoVOs = currencyClientService.findAll();
        log.info("=============== CreateBankTransferFileSearchView init end ===================");
    }

    public void search() {
        log.info("search start");
        lazyDataModel = new SimpleLazyDataModel<BankTransferItemResultVO, SearchBankTransferItemVO>(
            RequestUriConstant.CLIENT_URI_BANK_TXF_ITEM_SEARCH, criteria);
        log.info("search end");
    }

    public void reset() {
        log.info("reset start");
        lazyDataModel = null;
        criteria = new SearchBankTransferItemVO();
        log.info("reset end");
    }

    public String create() {
        log.info("create start");
        if (CommonUtils.isNotEmpty(selectedItems)) {
            List<String> curyCodes = new ArrayList<>();
            selectedItems.stream().forEach(item -> {
                String currency = item.getCurrency();
                if (!curyCodes.contains(currency)) {
                    curyCodes.add(currency);
                }
            });

            if (curyCodes.size() > 1) {
                Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_ITEMS_IN_DIFF_CURRENCY));
            } else {
                return EDIT_PAGE;
            }
        }
        log.info("create end");
        return null;
    }
}
