package hk.oro.iefas.web.funds.investment.investmentinstruction.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.domain.funds.vo.InvestmentInstructionDetailVO;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.investment.investmentinstruction.service.InvestmentClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2743 $ $Date: 2018-05-30 19:14:53 +0800 (週三, 30 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class InvestmentInstructionDetailView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private InvestmentClientService investmentClientService;

    @Getter
    @Setter
    private InvestmentInstructionDetailVO detailVO;

    @Getter
    @Setter
    private String invType;

    @PostConstruct
    private void init() {
        log.info("======InvestmentInstructionDetailView init======");

        Integer invItemId = Faces.getRequestParameter("invItemId", Integer.class);
        log.info("invItemId: " + invItemId);
        if (invItemId != null && invItemId.intValue() > 0) {
            detailVO = investmentClientService.searchInvestmentOption(invItemId);
        } else {
            detailVO = new InvestmentInstructionDetailVO();
        }
        invType = "F";
    }

    public void changeInvType() {
        System.err.println(invType);
    }

}
