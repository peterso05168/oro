package hk.oro.iefas.web.dividend.maintenance.orfeetobecharged.view;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.common.vo.CaseTypeVO;
import hk.oro.iefas.domain.dividend.vo.CaseFeeTypeVO;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2992 $ $Date: 2018-06-07 18:22:34 +0800 (週四, 07 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named(value = "feeToBeChargedSearchView")
@ViewScoped
public class OrFeeToBeChargedSearchView implements Serializable {

    private static final long serialVersionUID = 8116919111337461358L;

    @Inject
    private CommonClientService commonClientService;

    @Getter
    @Setter
    private CaseTypeVO caseType = new CaseTypeVO();

    @Getter
    private Map<String, String> caseTypes = new LinkedHashMap<>();

    @Getter
    @Setter
    private LazyDataModel<CaseFeeTypeVO> lazyDataModel;

    @PostConstruct
    private void init() {
        log.info("ORFeeToBeChargedSearchView init");
        List<CaseTypeVO> caseTypeList = this.commonClientService.searchDividendCaseTypeList();
        if (CommonUtils.isNotEmpty(caseTypeList)) {
            this.caseTypes.clear();
            caseTypeList.forEach(
                type -> this.caseTypes.put(type.getCaseTypeDesc(), String.valueOf(type.getCaseTypeId().intValue())));
        }
    }

    public void search() {
        log.info("search() start");
        lazyDataModel = new SimpleLazyDataModel<CaseFeeTypeVO, CaseTypeVO>(
            RequestUriConstant.CLIENT_URI_COMMON_DIVIDEND_ORFEEITEM_LIST, caseType);
        log.info("search() end");
    }

    public void reset() {
        caseType.setCaseTypeId(0);
        log.info("reset() end");
    }
}
