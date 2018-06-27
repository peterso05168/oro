package hk.oro.iefas.web.funds.investment.fundsdeposit.service.impl;

import java.util.Date;

import javax.inject.Named;

import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.vo.CalculationOfFundsAvailableVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.funds.investment.fundsdeposit.service.PlacingDepositsClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class PlacingDepositsClientServiceImpl extends BaseClientService implements PlacingDepositsClientService {
    @Override
    public CalculationOfFundsAvailableVO searchPlacingDeposits(Integer calculationOfFundsAvailableId) {
        log.info("findOne() start - calculationOfFundsAvailableId: " + calculationOfFundsAvailableId);
        ResponseEntity<CalculationOfFundsAvailableVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_PLACING_DEPOSITS_DETAIL, calculationOfFundsAvailableId,
                CalculationOfFundsAvailableVO.class);
        CalculationOfFundsAvailableVO body = responseEntity.getBody();
        log.info("findOne() end - " + body);
        return body;
    }

    @Override
    public Integer savePlacingDeposits(CalculationOfFundsAvailableVO availableVO) {
        log.info("save() start - availableVO: " + availableVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_PLACING_DEPOSITS_SAVE, availableVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("save() end - " + body);
        return body;
    }

    @Override
    public Boolean existsByInvestmentDate(Date invtDate) {
        log.info("existsByInvestmentDate() start - invtDate: " + invtDate);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_PLACING_DEPOSITS_EXISTS, invtDate, Boolean.class);
        Boolean body = responseEntity.getBody();
        log.info("existsByInvestmentDate() end - " + body);
        return body;
    }
}
