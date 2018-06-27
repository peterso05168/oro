/**
 * 
 */
package hk.oro.iefas.web.ledger.voucherhandling.journalvoucher.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.domain.shroff.vo.JournalTypeVO;
import hk.oro.iefas.domain.shroff.vo.JournalVoucherResultItemVO;
import hk.oro.iefas.domain.shroff.vo.JournalVoucherSearchVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.maintenance.analysiscode.service.AnalysisCodeClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.JournalTypeClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.VoucherClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2831 $ $Date: 2018-06-02 14:14:30 +0800 (週六, 02 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class JournalVoucherSearchView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private JournalTypeClientService journalTypeClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private AnalysisCodeClientService analysisCodeClientService;

    @Inject
    private VoucherClientService voucherClientService;

    @Getter
    @Setter
    private JournalVoucherSearchVO journalVoucherSearchVO;

    @Getter
    @Setter
    private List<JournalTypeVO> journalTypes;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private LazyDataModel<JournalVoucherResultItemVO> journalVoucherResultDataModel;

    @Getter
    @Setter
    private Integer selectedVoucherId;

    @PostConstruct
    private void init() {
        log.info("======JournalVoucherSearchView init======");
        journalVoucherSearchVO = new JournalVoucherSearchVO();
        journalTypes = journalTypeClientService.findAll();
        statusVOs = appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.VS.name());
    }

    public void search() {
        log.info("search() start");
        journalVoucherResultDataModel = new SimpleLazyDataModel<JournalVoucherResultItemVO, JournalVoucherSearchVO>(
            RequestUriConstant.CLIENT_URI_VOUCHER_SEARCH_JOURNAL_VOUCHER, journalVoucherSearchVO);
        log.info("search() end");
    }

    public List<AnalysisCodeVO> completeAnalysisCode(String query) {
        log.info("completeAnalysisCode() start");
        List<AnalysisCodeVO> analysisCodes = analysisCodeClientService.findByAnalysisCode(query);
        log.info("completeAnalysisCode() end - " + analysisCodes);
        return analysisCodes;
    }

    public void delete() {
        log.info("delete() start");
        if (selectedVoucherId != null) {
            voucherClientService.deleteVoucher(selectedVoucherId);
        }
        log.info("delete() end");
    }

    public void reset() {
        log.info("reset() start");
        journalVoucherSearchVO = new JournalVoucherSearchVO();
        journalVoucherResultDataModel = null;
        log.info("reset() end");
    }
}
