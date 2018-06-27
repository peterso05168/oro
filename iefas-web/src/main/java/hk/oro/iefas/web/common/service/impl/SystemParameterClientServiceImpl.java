/**
 * 
 */
package hk.oro.iefas.web.common.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.system.vo.SystemParameterVO;
import hk.oro.iefas.web.common.service.SystemParameterClientService;
import hk.oro.iefas.web.core.service.BaseClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class SystemParameterClientServiceImpl extends BaseClientService implements SystemParameterClientService {

    @Override
    public List<SystemParameterVO> findAll() {
        log.info("findAll() start");
        ResponseEntity<List<SystemParameterVO>> responseEntity = exchange(RequestUriConstant.CLIENT_URI_SYS_PARAM_LIST,
            HttpMethod.GET, null, new ParameterizedTypeReference<List<SystemParameterVO>>() {});
        List<SystemParameterVO> body = responseEntity.getBody();
        log.info("findAll() end - " + body);
        return body;
    }

}
