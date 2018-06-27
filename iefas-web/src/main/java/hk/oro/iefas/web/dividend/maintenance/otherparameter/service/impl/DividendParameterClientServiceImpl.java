package hk.oro.iefas.web.dividend.maintenance.otherparameter.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.vo.DividendParameterVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.maintenance.otherparameter.service.DividendParameterClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class DividendParameterClientServiceImpl extends BaseClientService implements DividendParameterClientService {
    @Override
    public List<DividendParameterVO> searchDividendParameter() {
        log.info("searchDividendParameter() start");
        ResponseEntity<List<DividendParameterVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_DIVIDEND_PARAMETER_SEARCH, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<DividendParameterVO>>() {});
        List<DividendParameterVO> body = responseEntity.getBody();
        log.info("searchDividendParameter() end - " + body);
        return body;
    }

    @Override
    public boolean saveDividendParameter(List<DividendParameterVO> parameters) {
        log.info("saveDividendParameter() start - parameters: " + parameters);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_DIVIDEND_PARAMETER_SAVE, parameters, Boolean.class);
        Boolean body = responseEntity.getBody();
        log.info("saveDividendParameter() end - " + body);
        return body;
    }

}
