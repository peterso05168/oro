package hk.oro.iefas.web.ledger.voucherhandling.paymentvoucher.view;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.domain.shroff.vo.PaymentVoucherResultVO;
import hk.oro.iefas.domain.shroff.vo.PaymentVoucherSearchVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.maintenance.analysiscode.service.AnalysisCodeClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.VoucherClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3108 $ $Date: 2018-06-13 14:55:09 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Named
@ViewScoped
public class PaymentVoucherSearchView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Inject
    private AnalysisCodeClientService analysisCodeClientService;

    @Inject
    private VoucherClientService voucherClientService;

    @Getter
    @Setter
    private PaymentVoucherSearchVO criteria = new PaymentVoucherSearchVO();

    @Getter
    @Setter
    private List<AnalysisCodeVO> analysisCodes;

    @Getter
    @Setter
    private AnalysisCodeVO selectedAnalysisCode;

    @Getter
    @Setter
    private Integer voucherId;

    @Getter
    @Setter
    private List<ApplicationCodeTableVO> statusVOs;

    @Getter
    @Setter
    private LazyDataModel<PaymentVoucherResultVO> lazyDataModel;

    @PostConstruct
    private void init() {
        log.info("=====PaymentVoucherSearchView init=====");
        statusVOs = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.VS.name());
        statusVOs.removeIf(item -> item.getCodeValue().equals(CoreConstant.VOUCHER_STATUS_DELETED));
        statusVOs = statusVOs.stream().sorted(Comparator.comparing(ApplicationCodeTableVO::getCodeDesc))
            .collect(Collectors.toList());
    }

    public void search() {
        log.info("search() start");
        this.lazyDataModel = new SimpleLazyDataModel<PaymentVoucherResultVO, PaymentVoucherSearchVO>(
            RequestUriConstant.CLIENT_URI_VOUCHER_FIND_PAYMENT_VOUCHER_BY_CRITERIA, criteria);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        lazyDataModel = null;
        voucherId = null;
        criteria = new PaymentVoucherSearchVO();
        log.info("reset() start");
    }

    public List<AnalysisCodeVO> completeAnalysisCode(String query) {
        log.info("completeAnalysisCode() start");
        analysisCodes = analysisCodeClientService.findByAnalysisCode(query);
        if (CommonUtils.isNotEmpty(analysisCodes)) {
            analysisCodes = analysisCodes.stream()
                .filter(item -> ShroffConstant.VT_PAY.equals(item.getVoucherType().getVoucherTypeCode()))
                .collect(Collectors.toList());
        }
        log.info("completeAnalysisCode() end");
        return analysisCodes;
    }

    public void onAnalysisCodeSelect(SelectEvent event) {
        log.info("onAnalysisCodeSelect() start");
        AnalysisCodeVO analysisCode = (AnalysisCodeVO)event.getObject();
        criteria.setAnalysisCode(analysisCode.getAnalysisCode());
        log.info("onAnalysisCodeSelect() end");
    }

    public void deleteVoucher() {
        log.info("deleteVoucher() start");
        if (voucherId != null) {
            voucherClientService.deleteVoucher(voucherId);
        }
        log.info("deleteVoucher() end");
    }
}
