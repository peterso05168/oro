package hk.oro.iefas.web.dividend.maintenance.gazette.view;

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
import hk.oro.iefas.domain.dividend.vo.GazetteVO;
import hk.oro.iefas.domain.dividend.vo.SearchGazetteDescriptionVO;
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
public class GazetteSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 548747120211355548L;

    @Autowired
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private AppResourceUtils appResourceUtils;
    
    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private SearchGazetteDescriptionVO searchGazetteDescriptionVO;

    @Getter
    @Setter
    private LazyDataModel<GazetteVO> lazyDataModel;
    
    @Getter
    private String deleteMsg;

    @PostConstruct
    public void init() {
        log.info("init - start");
        String deleteMsgCode = Faces.getFlashAttribute(DividendWebConstant.DELETE_MSG_CODE);
        if (deleteMsgCode != null && !"".equals(deleteMsgCode.trim())) {
            deleteMsg = appResourceUtils.getMessage(deleteMsgCode);
            showComponent("msgDialog");
        }
        statusVOs = commonConstantClientService.searchStatusList();
        searchGazetteDescriptionVO = new SearchGazetteDescriptionVO();
        log.info("init - End ");

    }

    public void search() {
        log.info("search - start");
        this.lazyDataModel = new SimpleLazyDataModel<GazetteVO, SearchGazetteDescriptionVO>(
            RequestUriConstant.CLIENT_LOAD_URI_GAZETTE_DESCRIPTION, searchGazetteDescriptionVO);
        log.info("search - end");
    }

    public void reset() {
        log.info("reset - start ");
        lazyDataModel = null;
        searchGazetteDescriptionVO = new SearchGazetteDescriptionVO();
        log.info("reset - end ");
    }
}
