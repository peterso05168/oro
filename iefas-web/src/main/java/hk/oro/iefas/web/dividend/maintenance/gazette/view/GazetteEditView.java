package hk.oro.iefas.web.dividend.maintenance.gazette.view;

import java.io.Serializable;
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
import hk.oro.iefas.domain.dividend.vo.GazetteVO;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.service.SysSequenceClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.maintenance.gazette.service.GazetteDescriptionClientService;
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
public class GazetteEditView extends BaseBean implements Serializable {

    private static final long serialVersionUID = -348147694194087643L;

    @Autowired
    private SysSequenceClientService sysSequenceClientService;

    @Autowired
    private GazetteDescriptionClientService gazetteDescriptionClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private Integer gazetteId;

    @Getter
    @Setter
    private GazetteVO gazetteVO;

    @Getter
    private Map<String, String> statusMap = new HashMap<String, String>();

    @PostConstruct
    public void init() {
        log.info("Gazette EditView init -start");
        Integer gazetteId = Faces.getRequestParameter("gazetteId", Integer.class);
        if (gazetteId != null && gazetteId > 0) {
            this.gazetteVO = gazetteDescriptionClientService.searchGazetteById(gazetteId);
            this.checkPrivilege();
        } else {
            this.changeToEdit();
            gazetteVO = new GazetteVO();
            gazetteVO.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        List<ApplicationCodeTableVO> statusVOs
            = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.SYS.name());
        if (CommonUtils.isNotEmpty(statusVOs)) {
            statusVOs.forEach(item -> {
                statusMap.put(item.getCodeValue(), item.getCodeDesc());
            });
        }
        log.info("Gazette EditView init - end");
    }

    public void save() {
        log.info("save - start");
        Integer gazetteId = this.gazetteVO.getGazetteId();
        if (gazetteId == null || gazetteId.intValue() == 0) {
            Integer gazetteSeqCode = sysSequenceClientService.generateIncrSeq(DividendConstant.GAZETTE_CODE).intValue();
            if (gazetteSeqCode != null && gazetteSeqCode > Integer.valueOf(DividendConstant.GAZETTE_CODE_MAX_VAL)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_CREATE_GAZETTE_CODE_ERROR), ""));
            } else {
                String gazetteCode = DividendConstant.GAZETTE_CODE_G
                    + String.format(DividendConstant.GAZETTE_CODE_FORMAT, gazetteSeqCode);
                this.gazetteVO.setGazetteCode(gazetteCode);
            }
        }
        Integer result = gazetteDescriptionClientService.saveGazetteDescription(this.gazetteVO);
        if (result != null && result > 0) {
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
            this.gazetteVO = gazetteDescriptionClientService.searchGazetteById(result);
        }
        this.changeToEdit();
        log.info("end - start");
    }

    public String deleteGazette() {
        log.info("deleteGazette - start");
        this.gazetteVO.setStatus(CoreConstant.STATUS_INACTIVE);
        if (this.gazetteVO.getGazetteId() != null && this.gazetteVO.getGazetteId().intValue() > 0) {
            Integer result = gazetteDescriptionClientService.saveGazetteDescription(this.gazetteVO);
            if (result != null && result.intValue() > 0) {
                this.gazetteVO = gazetteDescriptionClientService.searchGazetteById(result);
            }
        }
        Faces.setFlashAttribute(DividendWebConstant.DELETE_MSG_CODE, MsgCodeConstant.MSG_DELETE_SUCCESS);
        log.info("deleteGazette - end");
        return SEARCH_PAGE;
    }
}
