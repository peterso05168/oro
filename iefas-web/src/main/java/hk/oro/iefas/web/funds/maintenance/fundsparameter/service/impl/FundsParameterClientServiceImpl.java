package hk.oro.iefas.web.funds.maintenance.fundsparameter.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.funds.vo.FundsParameterVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.funds.maintenance.fundsparameter.service.FundsParameterClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class FundsParameterClientServiceImpl extends BaseClientService implements FundsParameterClientService {

    @Override
    public List<FundsParameterVO> loadParameter() {
        log.info("loadParameter() start");
        ResponseEntity<List<FundsParameterVO>> postForEntity = this.exchange(RequestUriConstant.CLIENT_URI_LOAD_PARA,
            HttpMethod.GET, null, new ParameterizedTypeReference<List<FundsParameterVO>>() {});
        List<FundsParameterVO> body = postForEntity.getBody();
        log.info("loadParameter() end - " + body);
        return body;
    }

    @Override
    public void saveParameter(List<FundsParameterVO> fundsParameter) {
        log.info("saveParameter() start - fundsParameterVO: " + fundsParameter);
        postForEntity(RequestUriConstant.CLIENT_URI_SAVE_PARA, fundsParameter, null);
        log.info("saveParameter() end");
    }
}
