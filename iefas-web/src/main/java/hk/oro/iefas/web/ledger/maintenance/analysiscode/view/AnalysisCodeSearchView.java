package hk.oro.iefas.web.ledger.maintenance.analysiscode.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeTypeVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.domain.shroff.vo.SearchAnalysisCodeCriteriaVO;
import hk.oro.iefas.domain.shroff.vo.SearchAnalysisCodeResultVO;
import hk.oro.iefas.domain.shroff.vo.VoucherTypeVO;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.maintenance.analysiscode.service.AnalysisCodeClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
public class AnalysisCodeSearchView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Autowired
    private AnalysisCodeClientService analysisCodeClientService;

    @Autowired
    private CommonClientService commonClientService;

    @Autowired
    private CommonConstantClientService commonConstantClientService;

    @Getter
    @Setter
    private SearchAnalysisCodeCriteriaVO criteria = new SearchAnalysisCodeCriteriaVO();

    @Getter
    @Setter
    private List<VoucherTypeVO> voucherTypeVOs;

    @Getter
    @Setter
    private List<AnalysisCodeTypeVO> codeTypeVOs;

    @Getter
    @Setter
    private List<ApplicationCodeTableVO> realizationFeeOptions;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private Integer analysisCodeId;

    @Getter
    @Setter
    private LazyDataModel<SearchAnalysisCodeResultVO> lazyDataModel;

    @PostConstruct
    private void init() {
        log.info("=====AnalysisCodeSearchView init=====");
        voucherTypeVOs = commonClientService.searchVoucherTypeList();
        codeTypeVOs = commonClientService.searchAnalysisCodeTypeList();
        realizationFeeOptions = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.ACF.name());
        statusVOs = commonConstantClientService.searchStatusList();
    }

    public void search() {
        log.info("search() start");
        this.lazyDataModel = new SimpleLazyDataModel<SearchAnalysisCodeResultVO, SearchAnalysisCodeCriteriaVO>(
            RequestUriConstant.CLIENT_FIND_ANALYSIS_CODE_BY_CRITERIA, criteria);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        lazyDataModel = null;
        criteria = new SearchAnalysisCodeCriteriaVO();
        log.info("reset() end");
    }

    public void changeStatus() {
        log.info("changeStatus() start");
        AnalysisCodeVO code = analysisCodeClientService.findOne(analysisCodeId);
        if (code.getStatus().equals(CoreConstant.STATUS_ACTIVE)) {
            code.setStatus(CoreConstant.STATUS_INACTIVE);
        } else {
            code.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        analysisCodeClientService.save(code);
        log.info("changeStatus() end");
    }
}
