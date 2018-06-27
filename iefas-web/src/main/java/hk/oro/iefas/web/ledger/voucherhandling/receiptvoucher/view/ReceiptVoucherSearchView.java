package hk.oro.iefas.web.ledger.voucherhandling.receiptvoucher.view;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherResultVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherSearchVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.maintenance.analysiscode.service.AnalysisCodeClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.VoucherClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision$ $Date$
 * @author $Author$
 */
@Slf4j
@Named
@ViewScoped
public class ReceiptVoucherSearchView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private AnalysisCodeClientService analysisCodeClientService;

    @Inject
    private VoucherClientService voucherClientService;

    @Getter
    @Setter
    private Integer voucherId;

    @Getter
    @Setter
    private List<AnalysisCodeVO> analysisCodes;

    @Getter
    @Setter
    private AnalysisCodeVO analysisCode;

    @Getter
    @Setter
    private ReceiptVoucherSearchVO receiptiVoucherSearchVO;

    @Getter
    @Setter
    private LazyDataModel<ReceiptVoucherResultVO> receiptVoucherDataModel;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @PostConstruct
    private void init() {
        log.info("======JournalVoucherSearchView init======");
        this.analysisCode = new AnalysisCodeVO();
        this.receiptiVoucherSearchVO = new ReceiptVoucherSearchVO();
        this.statusVOs = this.appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.VS.name());
    }

    public void deleteVoucher() {
        log.info("deleteVoucher() start");
        if (voucherId != null) {
            voucherClientService.deleteVoucher(voucherId);
        }
        log.info("deleteVoucher() end - ");
    }

    public void search() {
        log.info("search() start");
        this.receiptiVoucherSearchVO.setAnalysisCode(analysisCode.getAnalysisCode());
        this.receiptVoucherDataModel = new SimpleLazyDataModel<ReceiptVoucherResultVO, ReceiptVoucherSearchVO>(
            RequestUriConstant.CLIENT_URI_FIND_RECEIPT_VOUCHER_BY_CRITERIA, receiptiVoucherSearchVO);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        this.receiptiVoucherSearchVO = new ReceiptVoucherSearchVO();
        this.receiptVoucherDataModel = null;
        log.info("reset() end");
    }

    public List<AnalysisCodeVO> completeAnalysisCode(String query) {
        log.info("completeAnalysisCode() start");
        analysisCodes = this.analysisCodeClientService.findByAnalysisCode(query);
        if (CommonUtils.isNotEmpty(analysisCodes)) {
            analysisCodes = analysisCodes.stream()
                .filter(item -> ShroffConstant.VT_REC.equals(item.getVoucherType().getVoucherTypeCode()))
                .collect(Collectors.toList());
        }
        log.info("completeAnalysisCode() end");
        return analysisCodes;
    }

}
