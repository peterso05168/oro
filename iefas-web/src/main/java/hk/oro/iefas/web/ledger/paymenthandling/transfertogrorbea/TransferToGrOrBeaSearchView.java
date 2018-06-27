package hk.oro.iefas.web.ledger.paymenthandling.transfertogrorbea;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.CaseAccountTypeVO;
import hk.oro.iefas.domain.shroff.vo.SearchTransferToGrOrBeaCriteriaVO;
import hk.oro.iefas.domain.shroff.vo.SearchTransferToGrOrBeaResultVO;
import hk.oro.iefas.domain.shroff.vo.TransferAmountTypeVO;
import hk.oro.iefas.domain.shroff.vo.TransferToGrOrBeaVO;
import hk.oro.iefas.web.common.constant.ShroffWebConstant;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3174 $ $Date: 2018-06-15 19:54:00 +0800 (週五, 15 六月 2018) $
 * @author $Author: dante.fang $
 */
@ViewScoped
@Slf4j
@Named
public class TransferToGrOrBeaSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CommonClientService commonClientService;

    @Getter
    @Setter
    private List<CaseAccountTypeVO> accountTypeVOs;

    @Getter
    @Setter
    private List<TransferAmountTypeVO> transferTypeVOs;

    @Getter
    @Setter
    private SearchTransferToGrOrBeaCriteriaVO criteria = new SearchTransferToGrOrBeaCriteriaVO();

    @Getter
    @Setter
    private TransferToGrOrBeaVO newTransferVO;

    @Getter
    @Setter
    private Integer accountTypeId;

    @Getter
    @Setter
    private LazyDataModel<SearchTransferToGrOrBeaResultVO> lazyDataModel;

    @PostConstruct
    public void init() {
        log.info("==== init start ====");
        transferTypeVOs = commonClientService.searchTransferAmountTypeList();
        accountTypeVOs = commonClientService.searchCaseAccountTypeList();
        log.info("==== init end ====");
    }

    public void search() {
        log.info("search start");
        lazyDataModel = new SimpleLazyDataModel<SearchTransferToGrOrBeaResultVO, SearchTransferToGrOrBeaCriteriaVO>(
            RequestUriConstant.CLIENT_URI_FIND_TRANSFER_BY_CRITERIA, criteria);
        log.info("search end");
    }

    public void reset() {
        log.info("reset start");
        criteria = new SearchTransferToGrOrBeaCriteriaVO();
        lazyDataModel = null;
        log.info("reset end");
    }

    public void selectTransferType() {
        log.info("selectTransferType start");
        transferTypeVOs.stream().forEach(item -> {
            if (item.getTxfAmountTypeId().equals(newTransferVO.getTransferType().getTxfAmountTypeId())) {
                newTransferVO.setTransferType(item);
            }
        });
        log.info("selectTransferType end");
    }

    public void dialogOpen() {
        newTransferVO = new TransferToGrOrBeaVO();
        newTransferVO.setTransferType(new TransferAmountTypeVO());
        showComponent("generateDialog");
    }

    public void dialogClose() {
        log.info("dialogClose start");
        newTransferVO = new TransferToGrOrBeaVO();
        newTransferVO.setTransferType(new TransferAmountTypeVO());
        accountTypeId = null;
        log.info("dialogClose end");
    }

    public String generateTransfer() throws ParseException {
        Faces.setFlashAttribute(ShroffWebConstant.TRANSFER_VO, newTransferVO);
        Faces.setFlashAttribute(ShroffWebConstant.ACCOUNT_TYPE_ID, accountTypeId);
        return EDIT_PAGE;
    }
}
