package hk.oro.iefas.web.funds.investment.investmentinstruction.service.impl;

import javax.inject.Named;

import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.funds.vo.InvestmentInstructionDetailVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.funds.investment.investmentinstruction.service.InvestmentClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class InvestmentClientServiceImpl extends BaseClientService implements InvestmentClientService {
    @Override
    public InvestmentInstructionDetailVO searchInvestmentOption(Integer invItemId) {
        log.info("findOne() start - invItemId: " + invItemId);
        ResponseEntity<InvestmentInstructionDetailVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_INVESTMENT_INSTRUCTION_DETAIL, invItemId,
                InvestmentInstructionDetailVO.class);
        InvestmentInstructionDetailVO body = responseEntity.getBody();
        log.info("findOne() end - " + body);
        return body;
    }
}
