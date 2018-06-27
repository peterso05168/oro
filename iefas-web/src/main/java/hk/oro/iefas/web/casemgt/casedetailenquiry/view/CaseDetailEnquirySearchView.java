package hk.oro.iefas.web.casemgt.casedetailenquiry.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.SearchCaseDetailCriteriaVO;
import hk.oro.iefas.domain.casemgt.vo.SearchCaseDetailResultVO;
import hk.oro.iefas.domain.common.vo.CaseTypeVO;
import hk.oro.iefas.domain.common.vo.OutsiderTypeVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2753 $ $Date: 2018-05-31 10:10:34 +0800 (週四, 31 五月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Named
@ViewScoped
public class CaseDetailEnquirySearchView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private CommonClientService commonClientService;

    @Getter
    @Setter
    private SearchCaseDetailCriteriaVO criteria = new SearchCaseDetailCriteriaVO();

    @Getter
    @Setter
    private LazyDataModel<SearchCaseDetailResultVO> caseResultDataModel;

    @Getter
    @Setter
    private List<CaseTypeVO> caseTypeVOs;

    @Getter
    @Setter
    private List<OutsiderTypeVO> outsiderTypeVOs;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private Integer caseId;

    @PostConstruct
    private void init() {
        log.info("======CaseDetailEnquirySearchView init======");
        statusVOs = commonConstantClientService.searchStatusList();
        caseTypeVOs = commonClientService.searchCaseTypeList();
        outsiderTypeVOs = commonClientService.searchOutsiderTypeList();
    }

    public void search() {
        log.info("search() start and Criteria: " + criteria);
        caseResultDataModel = new SimpleLazyDataModel<SearchCaseDetailResultVO, SearchCaseDetailCriteriaVO>(
            RequestUriConstant.CLIENT_URI_SEARCH_CASE_BY_CRITERIA, criteria);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        criteria = new SearchCaseDetailCriteriaVO();
        caseResultDataModel = null;
        log.info("reset() end");
    }
}
