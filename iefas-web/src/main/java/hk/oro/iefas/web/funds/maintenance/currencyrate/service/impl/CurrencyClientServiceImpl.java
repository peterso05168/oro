package hk.oro.iefas.web.funds.maintenance.currencyrate.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.bank.vo.CurrencySearchVO;
import hk.oro.iefas.domain.bank.vo.CurrencyVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class CurrencyClientServiceImpl extends BaseClientService implements CurrencyClientService {

    @Override
    public Integer save(CurrencyVO currencyVO) {
        log.info("create() start - " + currencyVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_CURRENCY_SAVE_CURRENCY, currencyVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("create() end - " + body);
        return body;
    }

    @Override
    public CurrencyVO findOne(Integer curcyId) {
        log.info("findOne() start - curcyId: " + curcyId);
        ResponseEntity<CurrencyVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_CURRENCY_FIND_ONE, curcyId, CurrencyVO.class);
        CurrencyVO body = responseEntity.getBody();
        log.info("findOne() end - " + body);
        return body;
    }

    @Override
    public Boolean existsByCurcyCode(String curcyCode, Integer curcyId) {
        log.info("existsByCurcyCode() start - curcyCode: " + curcyCode + ", curcyId: " + curcyId);
        CurrencySearchVO currencySearchVO = new CurrencySearchVO();
        currencySearchVO.setCurrencyCode(curcyCode);
        currencySearchVO.setCurcyId(curcyId);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_CURRENCY_EXISTS, currencySearchVO, Boolean.class);
        Boolean body = responseEntity.getBody();
        log.info("existsByCurcyCode() end - exists: " + body);
        return body;
    }

    @Override
    public List<CurrencyBasicInfoVO> findAll() {
        log.info("findAll() strat - ");
        ResponseEntity<List<CurrencyBasicInfoVO>> postForEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_CURRENCY_FIND_ALL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CurrencyBasicInfoVO>>() {});
        List<CurrencyBasicInfoVO> body = postForEntity.getBody();
        log.info("findAll() end - " + body);
        return body;
    }

}
