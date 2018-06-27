package hk.oro.iefas.web.ledger.maintenance.analysiscode.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeTypeVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.domain.shroff.vo.VoucherTypeVO;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.maintenance.analysiscode.service.AnalysisCodeClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
public class AnalysisCodeEditView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Autowired
    private AnalysisCodeClientService analysisCodeClientService;

    @Autowired
    private CommonClientService commonClientService;

    @Autowired
    private CommonConstantClientService commonConstantClientService;

    @Getter
    @Setter
    private List<VoucherTypeVO> voucherTypeVOs;

    @Getter
    @Setter
    private List<AnalysisCodeTypeVO> codeTypeVOs;

    @Getter
    @Setter
    private List<ApplicationCodeTableVO> realizationFeeOptions;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private Integer analysisCodeId;

    @Getter
    @Setter
    private AnalysisCodeVO analysisCodeVO;

    @PostConstruct
    private void init() {
        log.info("=====AnalysisCodeSearchView init=====");
        voucherTypeVOs = commonClientService.searchVoucherTypeList();
        codeTypeVOs = commonClientService.searchAnalysisCodeTypeList();
        realizationFeeOptions = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.ACF.name());
        statusVOs = commonConstantClientService.searchStatusList();

        analysisCodeId = Faces.getRequestParameter("analysisCodeId", Integer.class);
        if (analysisCodeId != null && analysisCodeId != 0) {
            analysisCodeVO = analysisCodeClientService.findOne(analysisCodeId);
        } else {
            analysisCodeVO = new AnalysisCodeVO();
            analysisCodeVO.setAnalysisCodeType(new AnalysisCodeTypeVO());
            analysisCodeVO.setVoucherType(new VoucherTypeVO());
        }
    }

    public void save() {
        log.info("save() start -");
        codeTypeVOs.stream().forEach(item -> {
            if (item.getAnalysisCodeTypeId().equals(analysisCodeVO.getAnalysisCodeType().getAnalysisCodeTypeId())) {
                analysisCodeVO.setAnalysisCodeType(item);
            }
        });

        voucherTypeVOs.stream().forEach(item -> {
            if (item.getVoucherTypeId().equals(analysisCodeVO.getVoucherType().getVoucherTypeId())) {
                analysisCodeVO.setVoucherType(item);
            }
        });

        Integer result = analysisCodeClientService.save(analysisCodeVO);
        if (result != null) {
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
            analysisCodeVO = analysisCodeClientService.findOne(result);
        } else {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
        }
        log.info("save() end - ");
    }
}
