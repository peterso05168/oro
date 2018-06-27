package hk.oro.iefas.web.dividend.maintenance.withheldreasons.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.dividend.vo.SearchWithheldReasonVO;
import hk.oro.iefas.domain.dividend.vo.WithheldReasonVO;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
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
public class WithheldReasonsSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private SearchWithheldReasonVO searchCriteria;

    @Getter
    @Setter
    private LazyDataModel<WithheldReasonVO> withheldReasonDataModel;

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
        this.statusVOs = commonConstantClientService.searchStatusList();
        searchCriteria = new SearchWithheldReasonVO();
        log.info(" init - end");
    }

    public void search() {
        log.info(" search - start");
        withheldReasonDataModel = new SimpleLazyDataModel<WithheldReasonVO, SearchWithheldReasonVO>(
            RequestUriConstant.CLIENT_URI_SEARCH_WITHHELD_REASON, searchCriteria);
        log.info(" search - end");
    }

    public void reset() {
        log.info(" reset - start");
        searchCriteria = new SearchWithheldReasonVO();
        withheldReasonDataModel = null;
        log.info(" reset - end");
    }

}
