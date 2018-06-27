package hk.oro.iefas.web.casemgt.maintenance.outsider.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.domain.casemgt.vo.AddressVO;
import hk.oro.iefas.domain.casemgt.vo.OutsiderVO;
import hk.oro.iefas.domain.common.vo.OutsiderTypeVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.web.casemgt.maintenance.outsider.service.OutsiderClientService;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@ViewScoped
public class OutsiderEditView extends BaseBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private Integer outsiderId;

    @Inject
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private CommonClientService commonClientService;

    @Inject
    private OutsiderClientService outsiderClientService;

    @Getter
    @Setter
    private List<OutsiderTypeVO> outsiderTypeVOs;

    @Getter
    @Setter
    private OutsiderVO outsiderVO;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @PostConstruct
    private void init() {
        log.info("OutsiderSearchView init ======");
        outsiderId = Faces.getRequestParameter("outsiderId", Integer.class);
        statusVOs = commonConstantClientService.searchStatusList();
        outsiderTypeVOs = commonClientService.searchOutsiderTypeList();
        if (outsiderId == null) {
            outsiderVO = new OutsiderVO();
            outsiderVO.setAddress(new AddressVO());
            outsiderVO.setOutsiderType(new OutsiderTypeVO());
            outsiderVO.setStatus(CoreConstant.STATUS_ACTIVE);
            outsiderVO.getAddress().setStatus(CoreConstant.STATUS_ACTIVE);
        } else
            outsiderVO = outsiderClientService.getOutsiderDetail(outsiderId);
        log.info("OutsiderSearchView init end");
    }

    public void save() {
        log.info("save() start");
        Integer id = outsiderClientService.saveOutsider(outsiderVO);
        if (id != null) {
            outsiderVO = outsiderClientService.getOutsiderDetail(id);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        }
        else
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
        log.info("save() end");
    }

}
