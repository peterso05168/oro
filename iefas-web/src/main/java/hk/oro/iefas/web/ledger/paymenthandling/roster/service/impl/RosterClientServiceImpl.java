package hk.oro.iefas.web.ledger.paymenthandling.roster.service.impl;

import javax.inject.Named;

import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.shroff.vo.SearchRosterVO;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.vo.RosterVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.paymenthandling.roster.service.RosterClientService;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @version $Revision: 2745 $ $Date: 2018-05-30 20:19:44 +0800 (週三, 30 五月 2018) $
 * @author $Author: garrett.han $
 */
@Named
@Slf4j
public class RosterClientServiceImpl extends BaseClientService implements RosterClientService {

    @Override
    public Integer saveRoster(RosterVO rosterVO) {
        log.info("saveRoster() start - createRosterVO : " + rosterVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_ROSTER, rosterVO, Integer.class);
        Integer rosterId = responseEntity.getBody();
        log.info("saveRoster() end - " + rosterId);
        return rosterId;
    }

    @Override
    public RosterVO getRosterDetail(Integer rosterId) {
        log.info("getRosterDetail() start - rosterId : " + rosterId);
        ResponseEntity<RosterVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_GET_ROSTER_DETAIL, rosterId, RosterVO.class);
        RosterVO rosterVO = responseEntity.getBody();
        log.info("getRosterDetail() end - " + rosterVO);
        return rosterVO;
    }

    @Override
    public Boolean existByOnDutyDateAndIdNot(SearchRosterVO criteria) {
        log.info("existByOnDutyDate start - criteria: " + criteria);
        Boolean result;
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_EXISTS_BY_ON_DUTY_DATE, criteria, Boolean.class);
        result = responseEntity.getBody();
        log.info("existByOnDutyDate end - " + result);
        return result;
    }
}
