package hk.oro.iefas.web.dividend.common.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.vo.AdjudicationTypeVO;
import hk.oro.iefas.domain.dividend.vo.CreditorTypeVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.common.service.CommonDividendClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class CommonDividendClientServiceImpl extends BaseClientService implements CommonDividendClientService {

    @Override
    public List<CreditorTypeVO> searchCreditorType() {
        log.info("searchCreditorType start -");
        ResponseEntity<List<CreditorTypeVO>> postForEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_CREDITOR_TYPE_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CreditorTypeVO>>() {});
        List<CreditorTypeVO> body = postForEntity.getBody();
        log.info("searchOutsiderTypeList end - return: " + body);
        return body;
    }

    @Override
    public List<AdjudicationTypeVO> searchGroundCodeTypeList() {
        log.info(" searchGroundCodeTypeList - start");
        ResponseEntity<List<AdjudicationTypeVO>> result = this.exchange(RequestUriConstant.CLIENT_URI_GROUND_TYPE_LIST,
            HttpMethod.GET, null, new ParameterizedTypeReference<List<AdjudicationTypeVO>>() {});
        List<AdjudicationTypeVO> body = result.getBody();
        log.info(" searchGroundCodeTypeList - end and return " + body);
        return body;
    }

}
