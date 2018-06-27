package hk.oro.iefas.web.ledger.chequehandling.bulkreversal.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.RandomUtils;

import hk.oro.iefas.domain.shroff.vo.BulkReversalItemVO;
import hk.oro.iefas.domain.shroff.vo.BulkReversalResultVO;
import hk.oro.iefas.domain.shroff.vo.BulkReversalVO;
import hk.oro.iefas.domain.shroff.vo.GenerateBulkReversalVO;
import hk.oro.iefas.domain.shroff.vo.SearchBulkReversalVO;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.chequehandling.bulkreversal.service.BulkReversalClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date $
 * @author $Author $
 */
@Slf4j
@Named
@ViewScoped
public class BulkReversalMaintenanceView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private BulkReversalClientService bulkReversalClientService;

    @Getter
    @Setter
    private SearchBulkReversalVO searchBulkReversalVO;

    @Getter
    @Setter
    private BulkReversalResultVO searchResult;

    @Getter
    @Setter
    private BulkReversalVO selectedBulkReversal;

    @Getter
    @Setter
    private GenerateBulkReversalVO generateBulkReversalVO;

    @Getter
    @Setter
    private BulkReversalVO generatedResult;

    @Getter
    @Setter
    private List<BulkReversalItemVO> selectedBulkReversalItems;

    @Getter
    @Setter
    private BulkReversalItemVO selectedBulkReversalItem;

    @Getter
    @Setter
    private boolean showSearchResultFlag;

    @PostConstruct
    public void init() {
        log.info("==============BulkReversalMaintenanceView init start=============");
        generateBulkReversalVO = new GenerateBulkReversalVO();
        searchBulkReversalVO = new SearchBulkReversalVO();
        showSearchResultFlag = false;
        log.info("==============BulkReversalMaintenanceView init end==============");
    }

    public void reset() {
        log.info("reset() start");
        searchBulkReversalVO = new SearchBulkReversalVO();
        showSearchResultFlag = false;
        log.info("reset() end");
    }

    public void searchBulkReversal() {
        log.info("searchBulkReversal() start");
        searchResult = bulkReversalClientService.searchBulkReversal(searchBulkReversalVO);
        if (searchResult != null && searchResult.getBulkReversalList() != null) {
            showSearchResultFlag = true;
        }
        log.info("searchBulkReversal() end");
    }

    public void create() {
        log.info("create() start");
        generatedResult = bulkReversalClientService.generateBulkReversal(generateBulkReversalVO);
        generatedResult.getShrBulkRvlItems().stream().forEach(each -> {
            each.setRowId(RandomUtils.nextLong());
        });
        log.info("create() end");
    }

    public void confirmGenerate() {
        log.info("confirmGenerate() start");
        BulkReversalVO confirmedBulkReversalVo = new BulkReversalVO();
        if (selectedBulkReversalItems != null && !selectedBulkReversalItems.isEmpty()) {
            confirmedBulkReversalVo.setCutOffDate(generatedResult.getCutOffDate());
            confirmedBulkReversalVo.setProcessDate(generatedResult.getProcessDate());
            confirmedBulkReversalVo.setShrBulkRvlItems(selectedBulkReversalItems);
            boolean result = bulkReversalClientService.insertBulkReversal(confirmedBulkReversalVo);
            if (result) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Save successfully.", null));
            }
        } else {
            // promp mandatory message.
        }
        log.info("confirmGenerate() end");
    }

}
