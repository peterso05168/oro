package hk.oro.iefas.web.ledger.maintenance.controlaccount.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.shroff.vo.ControlAccountResultVO;
import hk.oro.iefas.domain.shroff.vo.ControlAccountTypeVO;
import hk.oro.iefas.domain.shroff.vo.ControlAccountVO;
import hk.oro.iefas.domain.shroff.vo.SearchControlAccountVO;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.maintenance.controlaccount.service.ControlAccountClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class ControlAccountSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer ctlAcId;

    @Inject
    private ControlAccountClientService controlAccountClientService;
    @Getter
    @Setter
    private SearchControlAccountVO criteria;

    @Inject
    private CommonClientService commonClientService;

    @Inject
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private List<StatusVO> statusVOS;

    @Getter
    @Setter
    private List<ControlAccountTypeVO> controlAccountTypeVOS;

    @Getter
    @Setter
    private List<StatusVO> balanceNatures;

    @Getter
    @Setter
    private LazyDataModel<ControlAccountResultVO> lazyDataModel;

    @PostConstruct
    public void init() {
        log.info("============== CtlAcServiceView init start ==============");
        balanceNatures = appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.BLN.name());
        criteria = new SearchControlAccountVO();
        statusVOS = commonConstantClientService.searchStatusList();
        controlAccountTypeVOS = commonClientService.searchCtlAcTypeList();
        log.info("============== CtlAcServiceView init end ==========");
    }

    public void search() {
        log.info("search() start");
        lazyDataModel
            = new SimpleLazyDataModel<>(RequestUriConstant.CLIENT_URI_CONTROL_ACCOUNT_SEARCH_CONTROL_ACCOUNT, criteria);
        log.info("search() end");
    }

    public void changeStatus() {
        log.info("changeStatus() start");
        ControlAccountVO controlAccountVO = controlAccountClientService.getControlAccountDetail(ctlAcId);
        if (controlAccountVO != null) {
            if (CoreConstant.STATUS_ACTIVE.equals(controlAccountVO.getStatus()))
                controlAccountVO.setStatus(CoreConstant.STATUS_INACTIVE);
            else
                controlAccountVO.setStatus(CoreConstant.STATUS_ACTIVE);
            controlAccountClientService.saveControlAccount(controlAccountVO);
        }
        log.info("changeStatus() end");
    }

    public void reset() {
        log.info("reset() start");
        criteria = new SearchControlAccountVO();
        lazyDataModel = null;
        log.info("reset() end");
    }
}
