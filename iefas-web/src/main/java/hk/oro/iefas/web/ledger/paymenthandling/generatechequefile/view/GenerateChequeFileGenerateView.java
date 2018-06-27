package hk.oro.iefas.web.ledger.paymenthandling.generatechequefile.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.organization.vo.ApproverVO;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.paymenthandling.generatechequefile.service.ChequeFileClientService;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3231 $ $Date: 2018-06-20 18:08:11 +0800 (週三, 20 六月 2018) $
 * @author $Author: garrett.han $
 */
@ViewScoped
@Slf4j
@Named
public class GenerateChequeFileGenerateView extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private PostClientService postClientService;
    @Inject
    private ChequeFileClientService chequeFileClientService;
    @Getter
    @Setter
    private List<ApproverVO> approverAVOs;
    @Getter
    @Setter
    private List<ApproverVO> approverBVOS;

    @PostConstruct
    public void init() {
        log.info("============= generateChequeFileGenerateView init start ===========");
        approverAVOs = postClientService.getFirstApprover(SecurityUtils.getCurrenDivisionId());
        approverBVOS = postClientService.getSecondApprover(SecurityUtils.getCurrenDivisionId());
        log.info("============= generateChequeFileGenerateView init end =============");
    }

    public void save() {
        log.info("save start");
        log.info("save end");
    }

    public void submit() {
        log.info("submit start");
        log.info("submit end");
    }


    public void approve() {
        log.info("approve start");
        log.info("approve end");
    }

    public void reject() {
        log.info("reject start");
        log.info("reject end");
    }

    public void generate() {
        log.info("generate start");
        log.info("generate end");
    }
}
