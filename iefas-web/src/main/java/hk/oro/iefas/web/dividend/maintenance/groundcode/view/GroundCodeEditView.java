package hk.oro.iefas.web.dividend.maintenance.groundcode.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.common.vo.CaseTypeVO;
import hk.oro.iefas.domain.dividend.vo.GroundCodeVO;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.service.SysSequenceClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.maintenance.groundcode.service.GroundCodeClientService;
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
public class GroundCodeEditView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private SysSequenceClientService sysSequenceClientService;

    @Autowired
    private GroundCodeClientService groundCodeClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private GroundCodeVO groundCodeVO;

    @Getter
    @Setter
    private String seq_code;

    @PostConstruct
    public void init() {
        log.info(" groundCodeEditView init - start");      
        this.seq_code = Faces.getFlashAttribute(DividendWebConstant.GROUND_CODE_SEQ_CODE);
        CaseTypeVO paramCaseType = Faces.getFlashAttribute(DividendWebConstant.GROUND_CODE_CASE_TYPE);
        ApplicationCodeTableVO applicationCodeTableVO
            = Faces.getFlashAttribute(DividendWebConstant.GROUND_CODE_APPLICATION_CODE);
        Integer groundCodeId = Faces.getRequestParameter(DividendWebConstant.GROUND_CODE_GROUND_Code_ID, Integer.class);
        if (groundCodeId != null && groundCodeId > 0) {
            this.groundCodeVO = groundCodeClientService.searchGroundCodeById(groundCodeId);
            this.checkPrivilege();
        } else {
            this.groundCodeVO = new GroundCodeVO();
            this.groundCodeVO.setStatus(CoreConstant.STATUS_ACTIVE);
            this.groundCodeVO.setNatureOfClaim(applicationCodeTableVO.getCodeValue());
            this.groundCodeVO.setCaseType(paramCaseType);
            this.changeToEdit();
        }
        log.info(" groundCodeEditView init - end");
    }

    public void save() {
        log.info(" save - start");
        Integer result = null;
        if (this.groundCodeVO != null && this.groundCodeVO.getGroundCodeId() != null
            && this.groundCodeVO.getGroundCodeId() > 0) {
            result = groundCodeClientService.saveGroundCode(this.groundCodeVO);
            if (result != null && result > 0) {
                Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
                this.groundCodeVO = groundCodeClientService.searchGroundCodeById(result);
            }
        } else {
            Integer groundCodeCode = sysSequenceClientService.generateIncrSeq(this.seq_code).intValue();
            if (groundCodeCode > Integer.valueOf(DividendConstant.GROUND_CODE_MAX_VAL)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_INVALID_PERIOD), ""));
            } else {
                this.groundCodeVO.setGroundCodeCode(groundCodeCode + "");
                result = groundCodeClientService.saveGroundCode(this.groundCodeVO);
                if (result != null && result > 0) {
                    Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
                    this.groundCodeVO = groundCodeClientService.searchGroundCodeById(result);
                }
            }
        }
        this.changeToEdit();
        log.info(" save - end result =" + result);
    }

    public String changeStatus() {
        log.info(" changeStatus - start ");
        this.groundCodeVO.setStatus(CoreConstant.STATUS_INACTIVE);
        if (groundCodeVO.getGroundCodeId() != null && groundCodeVO.getGroundCodeId() > 0) {
            Integer result = groundCodeClientService.saveGroundCode(this.groundCodeVO);
            this.groundCodeVO = groundCodeClientService.searchGroundCodeById(result);
        }
        Faces.setFlashAttribute(DividendWebConstant.DELETE_MSG_CODE, MsgCodeConstant.MSG_DELETE_SUCCESS);
        log.info(" changeStatus - end ");
        return SEARCH_PAGE;
    }

}
