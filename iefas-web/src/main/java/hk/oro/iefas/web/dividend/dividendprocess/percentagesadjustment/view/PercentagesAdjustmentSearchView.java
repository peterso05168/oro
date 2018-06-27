package hk.oro.iefas.web.dividend.dividendprocess.percentagesadjustment.view;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.dividend.vo.PercentagesAdjustmentVO;
import hk.oro.iefas.domain.dividend.vo.SearchPercentagesAdjustmentCriteriaVO;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
@Slf4j
@Named
@ViewScoped
public class PercentagesAdjustmentSearchView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private SearchPercentagesAdjustmentCriteriaVO criteria;

    @Getter
    @Setter
    private LazyDataModel<PercentagesAdjustmentVO> lazyDataModel;

    @PostConstruct
    public void init() {
        log.info("init - start");
        criteria = new SearchPercentagesAdjustmentCriteriaVO();
        criteria.setCaseNumber(new CaseNumberVO());
        log.info("init - end");
    }

    public void search() {
        log.info("search - start");
        this.lazyDataModel = new SimpleLazyDataModel<PercentagesAdjustmentVO, SearchPercentagesAdjustmentCriteriaVO>(
            RequestUriConstant.CLIENT_URI_SEARCH_PERCENTAGES_ADJUSTMENT_LIST, criteria);
        log.info("search - end");
    }

    public String toEdit() {
        log.info("toEdit - start");
        Integer adjudicationResultId = Faces.getRequestParameter("adjudicationResultId", Integer.class);
        CaseNumberVO caseNumberVO = this.criteria.getCaseNumber();
        if (caseNumberVO != null) {
            Faces.setFlashAttribute(DividendWebConstant.PAYMENT_PERCENTAGE_PARAM_CASE_NUMBER, caseNumberVO);
        }
        if (adjudicationResultId != null) {
            Faces.setFlashAttribute(DividendWebConstant.PAYMENT_PERCENTAGE_PARAM_ADJRESULTID, adjudicationResultId);
        }
        log.info("toEdit - end and caseNumberVO = " + caseNumberVO + "adjudicationResultId =" + adjudicationResultId);
        return EDIT_PAGE;
    }

    public void reset() {
        log.info("reset - start");
        criteria = new SearchPercentagesAdjustmentCriteriaVO();
        criteria.setCaseNumber(new CaseNumberVO());
        this.lazyDataModel = null;
        log.info("reset - end");
    }

}
