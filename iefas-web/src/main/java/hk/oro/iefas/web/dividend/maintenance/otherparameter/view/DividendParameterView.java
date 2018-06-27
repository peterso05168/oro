package hk.oro.iefas.web.dividend.maintenance.otherparameter.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.dividend.vo.DividendParameterVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.maintenance.otherparameter.service.DividendParameterClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class DividendParameterView implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private AppResourceUtils appResourceUtils;
    @Inject
    private DividendParameterClientService dividendParameterClientService;

    private List<DividendParameterVO> dividendParameterVOs;

    @Getter
    @Setter
    private DividendParameterVO parameterVO;

    @PostConstruct
    private void init() {
        log.info("======DividendParameterView init======");
        loadParameter();
    }

    private void loadParameter() {
        dividendParameterVOs = dividendParameterClientService.searchDividendParameter();
        convertBackendValueToDisplay();
    }

    private void convertBackendValueToDisplay() {
        if (CommonUtils.isNotEmpty(dividendParameterVOs)) {
            parameterVO = dividendParameterVOs.get(0);
        }
    }

    private void convertDisplayValueToBackend() {}

    public void updateParameter() {
        log.info("updateParameter() start ");
        convertDisplayValueToBackend();
        if (dividendParameterClientService.saveDividendParameter(dividendParameterVOs)) {
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        }
        log.info("updateParameter() end");
    }
}
