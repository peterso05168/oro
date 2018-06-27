package hk.oro.iefas.web.casemgt.casedetailenquiry.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.DepositCardVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.DepositCardClientService;
import hk.oro.iefas.web.core.service.BaseClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class DepositCardClientServiceImpl extends BaseClientService implements DepositCardClientService {

    @Override
    public DepositCardVO findOne(Integer depositCardId) {
        log.info("findOne() start - and depositCardId = " + depositCardId);
        ResponseEntity<DepositCardVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_DEPOSIT_CARD_DETAIL, depositCardId, DepositCardVO.class);
        DepositCardVO body = responseEntity.getBody();
        log.info("findOne() end - result = " + body);
        return body;
    }

    @Override
    public List<DepositCardVO> findDepositCardByCase(Integer caseId) {
        log.info("findDepositCardByCase() start - caseId = " + caseId);
        HttpEntity<Integer> entity = new HttpEntity<Integer>(caseId);
        ResponseEntity<List<DepositCardVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_FIND_DEPOSIT_CARD_BY_CASE, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<DepositCardVO>>() {});
        List<DepositCardVO> body = responseEntity.getBody();
        log.info("findDepositCardByCase() end - " + body);
        return body;
    }

    @Override
    public Integer save(DepositCardVO depositCard) {
        log.info("create() start - and depositCard = " + depositCard);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_DEPOSIT_CARD_SAVE, depositCard, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("create() end - and depositCardId = " + body);
        return body;
    }

}
