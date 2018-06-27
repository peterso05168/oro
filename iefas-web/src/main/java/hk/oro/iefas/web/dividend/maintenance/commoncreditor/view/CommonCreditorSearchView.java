package hk.oro.iefas.web.dividend.maintenance.commoncreditor.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.CommonCreditorBasicVO;
import hk.oro.iefas.domain.casemgt.vo.CommonCreditorVO;
import hk.oro.iefas.domain.casemgt.vo.SearchCommonCreditorCriteriaVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.maintenance.commoncreditor.service.DividendCommonCreditorClientService;
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
public class CommonCreditorSearchView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private CommonConstantClientService commonConstantClientService;

    @Autowired
    private DividendCommonCreditorClientService dividendCommonCreditorClientService;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private SearchCommonCreditorCriteriaVO criteriaVO = new SearchCommonCreditorCriteriaVO();

    @Getter
    @Setter
    private LazyDataModel<CommonCreditorBasicVO> commonCreditorDataModel;

    @Getter
    @Setter
    private Integer commonCreditorId;

    @PostConstruct
    private void init() {
        log.info("======CurrencyRateSearchView init======");
        statusVOs = commonConstantClientService.searchStatusList();

    }

    public void changeStatus() {
        CommonCreditorVO commonCreditorVO = dividendCommonCreditorClientService.searchCommonCreditor(commonCreditorId);
        if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(commonCreditorVO.getStatus())) {
            commonCreditorVO.setStatus(CoreConstant.STATUS_INACTIVE);
        } else {
            commonCreditorVO.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        dividendCommonCreditorClientService.saveCommonCreditor(commonCreditorVO);
    }

    public void search() {
        log.info("search() start");
        log.info("Criteria: " + criteriaVO);
        commonCreditorDataModel = new SimpleLazyDataModel<CommonCreditorBasicVO, SearchCommonCreditorCriteriaVO>(
            RequestUriConstant.CLIENT_URI_COMMON_CREDITOR_LIST, criteriaVO);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        criteriaVO = new SearchCommonCreditorCriteriaVO();
        commonCreditorDataModel = null;
        log.info("reset() end");
    }
}
