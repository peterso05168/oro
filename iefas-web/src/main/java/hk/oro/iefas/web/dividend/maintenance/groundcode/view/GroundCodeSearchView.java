package hk.oro.iefas.web.dividend.maintenance.groundcode.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.common.vo.CaseTypeVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.dividend.vo.GroundCodeVO;
import hk.oro.iefas.domain.dividend.vo.SearchGroundCodeCriteriaVO;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3265 $ $Date: 2018-06-25 11:21:23 +0800 (週一, 25 六月 2018) $
 * @author $Author: noah.liang $
 */

@Slf4j
@Named
@ViewScoped
public class GroundCodeSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CommonClientService commonClientServicel;

    @Autowired
    private CommonConstantClientService commonConstantClientService;
    
    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private List<ApplicationCodeTableVO> applicationCodeTableVOList;

    @Getter
    @Setter
    private List<CaseTypeVO> caseTypes;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private SearchGroundCodeCriteriaVO criteriaVO = new SearchGroundCodeCriteriaVO();

    @Getter
    @Setter
    private LazyDataModel<GroundCodeVO> groundCodeDataModel;

    @Getter
    @Setter
    private CaseTypeVO caseType;

    @Getter
    @Setter
    private ApplicationCodeTableVO applicationCodeTableVO;

    @Getter
    private String deleteMsg;

    @PostConstruct
    public void init() {
        log.info(" init - start");
        String deleteMsgCode = Faces.getFlashAttribute(DividendWebConstant.DELETE_MSG_CODE);
        if (deleteMsgCode != null && !"".equals(deleteMsgCode.trim())) {
            deleteMsg = appResourceUtils.getMessage(deleteMsgCode);
            showComponent("msgDialog");
        }

        this.caseTypes = commonClientServicel.searchDividendCaseTypeList();
        this.applicationCodeTableVOList = commonClientServicel.searchNatureOfClaim();
        this.statusVOs = commonConstantClientService.searchStatusList();
        this.criteriaVO.setCaseType(new CaseTypeVO());
        this.applicationCodeTableVO = new ApplicationCodeTableVO();
        caseType = new CaseTypeVO();
        log.info(" init - start");
    }

    public void search() {
        log.info(" search - start");
        log.info(" Criteria: " + criteriaVO);
        groundCodeDataModel = new SimpleLazyDataModel<GroundCodeVO, SearchGroundCodeCriteriaVO>(
            RequestUriConstant.CLIENT_URI_GROUND_CODE_LIST, criteriaVO);
        log.info("search - end");
    }

    public void reset() {
        log.info(" reset - start");
        criteriaVO = new SearchGroundCodeCriteriaVO();
        criteriaVO.setCaseType(new CaseTypeVO());
        groundCodeDataModel = null;
        log.info("reset - end");
    }

    public String toEdit() {
        log.info(" toEdit - start");
        StringBuilder seq_code = new StringBuilder(DividendConstant.SEQ_CODE);
        caseTypes.forEach(c -> {
            if (c.getCaseTypeId().equals(this.caseType.getCaseTypeId())) {
                this.caseType = c;
                seq_code.append(c.getCaseTypeCode());
            }
        });
        applicationCodeTableVOList.forEach(a -> {
            if (a.getApplicationCodeTableId().equals(this.applicationCodeTableVO.getApplicationCodeTableId())) {
                this.applicationCodeTableVO = a;
                seq_code.append(a.getCodeValue().substring(0, 1));
            }
        });
        Faces.setFlashAttribute(DividendWebConstant.GROUND_CODE_CASE_TYPE, this.caseType);
        Faces.setFlashAttribute(DividendWebConstant.GROUND_CODE_APPLICATION_CODE, this.applicationCodeTableVO);
        Faces.setFlashAttribute(DividendWebConstant.GROUND_CODE_SEQ_CODE, seq_code.toString());
        return EDIT_PAGE;
    }
}
