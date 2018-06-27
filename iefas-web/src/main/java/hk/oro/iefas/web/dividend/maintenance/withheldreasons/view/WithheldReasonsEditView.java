package hk.oro.iefas.web.dividend.maintenance.withheldreasons.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.dividend.vo.WithheldReasonVO;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.service.SysSequenceClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.maintenance.withheldreasons.service.WithheldReasonsClientService;
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
public class WithheldReasonsEditView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Autowired
    private SysSequenceClientService sysSequenceClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Autowired
    private WithheldReasonsClientService withheldReasonsClientService;

    @Getter
    @Setter
    private WithheldReasonVO withheldReasonVO;

    @Getter
    private Map<String, String> statusMap = new HashMap<String, String>();

    @PostConstruct
    public void init() {
        log.info(" init - start");
        Integer withheldReasonId = Faces.getRequestParameter("withheldReasonId", Integer.class);
        if (withheldReasonId != null && withheldReasonId.intValue() > 0) {
            this.withheldReasonVO = withheldReasonsClientService.searchByWithheldReasonId(withheldReasonId);
            this.checkPrivilege();
        } else {
            // create
            this.withheldReasonVO = new WithheldReasonVO();
            this.withheldReasonVO.setStatus(CoreConstant.STATUS_ACTIVE);
            this.changeToEdit();
        }
        List<ApplicationCodeTableVO> statusVOs
            = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.SYS.name());
        if (CommonUtils.isNotEmpty(statusVOs)) {
            statusVOs.forEach(item -> {
                statusMap.put(item.getCodeValue(), item.getCodeDesc());
            });
        }
        log.info(" init - end");
    }

    public void save() {
        log.info("createWithheldReason - start ");
        if (this.withheldReasonVO != null) {
            Integer withheldId = this.withheldReasonVO.getWithheldReasonId();
            if (withheldId == null || withheldId.intValue() <= 0) {
                String withheldReasonCode
                    = sysSequenceClientService.generateWithheldReason(DividendConstant.WITHHELD_CODE);
                if (withheldReasonCode == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        appResourceUtils.getMessage(MsgCodeConstant.MSG_CREATE_WITHHLD_REASON_CODE_ERROR), ""));
                } else {
                    this.withheldReasonVO.setWithheldReasonCode(withheldReasonCode);
                }
            }
            Integer result = withheldReasonsClientService.saveWithheldReason(this.withheldReasonVO);
            if (result != null && result > 0) {
                this.withheldReasonVO = withheldReasonsClientService.searchByWithheldReasonId(result);
                Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
            }
        }
        this.changeToEdit();
        log.info("createWithheldReason - end");
    }

    public String changeStatus() {
        log.info(" deleteWithheldReason - start");
        this.withheldReasonVO.setStatus(CoreConstant.STATUS_INACTIVE);
        if (this.withheldReasonVO.getWithheldReasonId() != null && this.withheldReasonVO.getWithheldReasonId() > 0) {
            Integer result = withheldReasonsClientService.saveWithheldReason(this.withheldReasonVO);
            if (result != null && result.intValue() > 0) {
                this.withheldReasonVO = withheldReasonsClientService.searchByWithheldReasonId(result);
            }
        }
        Faces.setFlashAttribute(DividendWebConstant.DELETE_MSG_CODE, MsgCodeConstant.MSG_DELETE_SUCCESS);
        log.info(" deleteWithheldReason - end");
        return SEARCH_PAGE;
    }
}
