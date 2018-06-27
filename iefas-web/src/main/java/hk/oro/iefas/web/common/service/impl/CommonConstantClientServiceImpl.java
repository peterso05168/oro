package hk.oro.iefas.web.common.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.core.service.BaseClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class CommonConstantClientServiceImpl extends BaseClientService implements CommonConstantClientService {
    @Override
    public List<StatusVO> searchStatusList() {
        log.info("searchStatusList start-");
        ResponseEntity<List<StatusVO>> postForEntity = this.exchange(RequestUriConstant.CLIENT_URI_STATUS_LIST,
            HttpMethod.GET, null, new ParameterizedTypeReference<List<StatusVO>>() {});

        List<StatusVO> body = postForEntity.getBody();
        log.info("searchStatusList end- return :" + body);
        return body;
    }

}
