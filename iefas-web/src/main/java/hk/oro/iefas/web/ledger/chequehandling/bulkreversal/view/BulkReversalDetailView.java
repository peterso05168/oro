package hk.oro.iefas.web.ledger.chequehandling.bulkreversal.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.domain.shroff.vo.BulkReversalItemVO;
import hk.oro.iefas.domain.shroff.vo.BulkReversalVO;
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
public class BulkReversalDetailView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private BulkReversalClientService bulkReversalClientService;

    @Getter
    @Setter
    private Long bulkReversalId;

    @Getter
    @Setter
    private BulkReversalVO selectedBulkReversal;

    @Getter
    @Setter
    private List<BulkReversalItemVO> selectedBulkReversalItems;

    @PostConstruct
    public void init() {
        log.info("==============BulkReversalDetailView init start=============");
        bulkReversalId = Faces.getRequestParameter("bulkReversalId", Long.class);
        if (bulkReversalId != null && bulkReversalId.intValue() > 0) {
            selectedBulkReversal = bulkReversalClientService.loadBulkReversalDetail(bulkReversalId);
        } else {
            selectedBulkReversal = new BulkReversalVO();
        }
        selectedBulkReversalItems = new ArrayList<>();
        log.info("==============BulkReversalDetailView init end==============");
    }

    public void headerToggleSelect() {
        log.info("headerToggleSelect() start");
        log.info("headerToggleSelect() end");
    }

    public void confirmBulkReversal() {
        log.info("confirmBulkReversal() start");
        BulkReversalVO confirmedBulkReversalVo = selectedBulkReversal;
        if (selectedBulkReversalItems != null && !selectedBulkReversalItems.isEmpty()) {
            confirmedBulkReversalVo.setShrBulkRvlItems(selectedBulkReversalItems);
            selectedBulkReversal = bulkReversalClientService.confirmBulkReversal(confirmedBulkReversalVo);

            // if (confirmedBulkReversal != null) {
            // FacesContext.getCurrentInstance().addMessage(null,
            // new FacesMessage(FacesMessage.SEVERITY_INFO, "Save successfully.", null));
            // }
        } else {
            Messages.addGlobalError("Please select at least 1 cheque.");
        }
        log.info("confirmBulkReversal() end");
    }

}
