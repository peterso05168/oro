package hk.oro.iefas.web.casemgt.proofofdebt.enquiry.view;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.util.BusinessUtils;
import hk.oro.iefas.domain.casemgt.vo.SearchProofOfDebtCriteriaVO;
import hk.oro.iefas.domain.casemgt.vo.SearchProofOfDebtResultVO;
import hk.oro.iefas.domain.counter.vo.ProofOfDebtVO;
import hk.oro.iefas.web.casemgt.proofofdebt.enquiry.service.ProofOfDebtClientService;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
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
public class ProofOfDebtEnquirySearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ProofOfDebtClientService proofOfDebtClientService;

    @Getter
    @Setter
    private LazyDataModel<SearchProofOfDebtResultVO> lazyDataModel;

    @Getter
    @Setter
    private SearchProofOfDebtCriteriaVO criteria = new SearchProofOfDebtCriteriaVO();

    @Getter
    @Setter
    private Integer proofOfDebtId;

    public void search() {
        log.info("search() start");
        criteria.setCaseNumber(
            BusinessUtils.genCaseNumber(criteria.getCaseTypeCode(), criteria.getCaseSeqNo(), criteria.getCaseYear()));
        this.lazyDataModel = new SimpleLazyDataModel<SearchProofOfDebtResultVO, SearchProofOfDebtCriteriaVO>(
            RequestUriConstant.CLIENT_URI_FIND_PROOF_OF_DEBT_BY_CRITERIA, criteria);
        log.info("search() end");
    }

    public void deleteProofOfDebt() {
        log.info("changeStatus() start");
        ProofOfDebtVO debt = proofOfDebtClientService.findOne(proofOfDebtId);
        debt.setStatus(CoreConstant.STATUS_DELETE);
        proofOfDebtClientService.save(debt);
        log.info("changeStatus() end");
    }
}
