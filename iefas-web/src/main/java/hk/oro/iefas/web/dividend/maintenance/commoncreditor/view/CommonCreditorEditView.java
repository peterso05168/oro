package hk.oro.iefas.web.dividend.maintenance.commoncreditor.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.vo.AddressVO;
import hk.oro.iefas.domain.casemgt.vo.CommonCreditorSectionVO;
import hk.oro.iefas.domain.casemgt.vo.CommonCreditorVO;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.maintenance.commoncreditor.service.DividendCommonCreditorClientService;
import hk.oro.iefas.web.dividend.maintenance.commoncreditor.view.validator.CommonCreditorValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3200 $ $Date: 2018-06-19 17:26:07 +0800 (週二, 19 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class CommonCreditorEditView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private DividendCommonCreditorClientService dividendCommonCreditorClientService;

    @Autowired
    private CommonCreditorValidator validator;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    private Map<String, String> statusMap = new HashMap<String, String>();

    @Getter
    @Setter
    private CommonCreditorVO commonCreditorVO;

    @Getter
    @Setter
    private CommonCreditorSectionVO commonCreditorSectionVO;

    @Getter
    @Setter
    private Integer commonCreditorId;

    @PostConstruct
    private void init() {
        log.info("======CommonCreditorEditView init======");

        commonCreditorId = Faces.getRequestParameter("commonCreditorId", Integer.class);
        log.debug("commonCreditorId: " + commonCreditorId);
        if (commonCreditorId != null) {
            commonCreditorVO = dividendCommonCreditorClientService.searchCommonCreditor(commonCreditorId);
        } else {
            commonCreditorVO = new CommonCreditorVO();
            commonCreditorVO.setStatus(CoreConstant.STATUS_ACTIVE);
            AddressVO addressVO = new AddressVO();
            addressVO.setStatus(CoreConstant.STATUS_ACTIVE);
            commonCreditorVO.setAddress(addressVO);
        }
        commonCreditorSectionVO = new CommonCreditorSectionVO();
        this.commonCreditorSectionVO.setAddress(new AddressVO());

        List<ApplicationCodeTableVO> statusVOs
            = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.SYS.name());
        if (CommonUtils.isNotEmpty(statusVOs)) {
            statusVOs.forEach(item -> {
                statusMap.put(item.getCodeValue(), item.getCodeDesc());
            });
        }
    }

    public void save() {
        log.info("save() start " + this.commonCreditorVO);
        if (validator.saveCommonCreditorValidate()) {
            Integer commonCreditorId = dividendCommonCreditorClientService.saveCommonCreditor(this.commonCreditorVO);
            this.commonCreditorVO = dividendCommonCreditorClientService.searchCommonCreditor(commonCreditorId);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        }
        log.info("save() end ");
    }

    public void changeSectionStatus() {
        if (CoreConstant.STATUS_ACTIVE.equals(commonCreditorSectionVO.getStatus())) {
            commonCreditorSectionVO.setStatus(CoreConstant.STATUS_INACTIVE);
        } else {
            commonCreditorSectionVO.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        this.commonCreditorSectionVO = new CommonCreditorSectionVO();
        this.commonCreditorSectionVO.setAddress(new AddressVO());
    }

    public void addSection() {
        List<CommonCreditorSectionVO> commonCreditorSections = this.commonCreditorVO.getCommonCreditorSections();
        if (CommonUtils.isEmpty(commonCreditorSections)) {
            commonCreditorSections = new ArrayList<CommonCreditorSectionVO>();
            this.commonCreditorVO.setCommonCreditorSections(commonCreditorSections);
        }
        if (commonCreditorSections.contains(this.commonCreditorSectionVO)) {
            for (CommonCreditorSectionVO commonCreditorSectionVO : commonCreditorSections) {
                if (commonCreditorSectionVO.equals(this.commonCreditorSectionVO)) {
                    commonCreditorSectionVO
                        = DataUtils.copyProperties(this.commonCreditorSectionVO, CommonCreditorSectionVO.class);
                }
            }
        } else {
            this.commonCreditorSectionVO.setStatus(CoreConstant.STATUS_ACTIVE);
            commonCreditorSections.add(this.commonCreditorSectionVO);
        }
        hideComponent("sectionDialog");
    }

    public void sectionHandleClose(CloseEvent event) {
        log.info("sectionHandleClose() start ");
        this.commonCreditorSectionVO = new CommonCreditorSectionVO();
        this.commonCreditorSectionVO.setAddress(new AddressVO());
        log.info("sectionHandleClose() end ");
    }
}
