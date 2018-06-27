package hk.oro.iefas.web.dividend.dividendprocess.dividendcheque.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.dividend.vo.DividendChequeVO;
import hk.oro.iefas.domain.dividend.vo.SearchDividendChequeVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3021 $ $Date: 2018-06-10 16:06:34 +0800 (Sun, 10 Jun 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class DividendChequeSearchView extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Autowired
    private CaseClientService caseClientService;

    @Getter
    @Setter
    private LazyDataModel<DividendChequeVO> lazyDataModel;

    @Setter
    @Getter
    private SearchDividendChequeVO criteriaVO;

    @Getter
    private List<StatusVO> statusVOs;
    
    @Getter
    private Map<String, String> statusMap = new HashMap<>();

    @Getter
    @Setter
    private String caseName;
    
    @Getter
    private String deleteMsg;

    @PostConstruct
    private void init() {
        log.info("DividendChequeSearchView init");
        String deleteMsgCode = Faces.getFlashAttribute(DividendWebConstant.DELETE_MSG_CODE);
        if (deleteMsgCode != null && !"".equals(deleteMsgCode.trim())) {
            deleteMsg = appResourceUtils.getMessage(deleteMsgCode);
            showComponent("msgDialog");
        }
        
        criteriaVO = new SearchDividendChequeVO();
        criteriaVO.setCaseNumber(new CaseNumberVO());
        statusVOs = appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.DCS.name());
        if (CommonUtils.isNotEmpty(statusVOs)) {
            statusVOs.stream().forEach(item -> {
                statusMap.put(item.getStatusVal(), item.getStatusName());
            });
        }
    }

    public void search() {
        log.info("search() start");
        CaseVO caseVO = caseClientService.findByCaseNumber(criteriaVO.getCaseNumber());
        if (caseVO != null) {
            caseName = caseVO.getCaseName();
            criteriaVO.setCaseId(caseVO.getCaseId());
        } else {
            caseName = null;
            criteriaVO.setCaseId(null);
        }

        lazyDataModel = new SimpleLazyDataModel<DividendChequeVO, SearchDividendChequeVO>(
            RequestUriConstant.CLIENT_DIVIDEND_CHEQUE_LIST, criteriaVO);
        log.info("search() end");
    }

    public void reset() {
        criteriaVO = new SearchDividendChequeVO();
        lazyDataModel = null;
    }
}
