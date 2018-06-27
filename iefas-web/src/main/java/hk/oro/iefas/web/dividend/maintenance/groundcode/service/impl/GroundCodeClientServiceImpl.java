package hk.oro.iefas.web.dividend.maintenance.groundcode.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.vo.GroundCodeVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.maintenance.groundcode.service.GroundCodeClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class GroundCodeClientServiceImpl extends BaseClientService implements GroundCodeClientService {

    @Override
    public Integer saveGroundCode(GroundCodeVO groundCodeVO) {
        log.info("saveGroundCode - start - and groundCodeVO =" + groundCodeVO);
        ResponseEntity<Integer> response
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_GROUND_CODE, groundCodeVO, Integer.class);
        Integer result = response.getBody();
        log.info("saveGroundCode - end and return" + result);
        return result;
    }

    @Override
    public GroundCodeVO searchGroundCodeById(Integer groundCodeId) {
        log.info("searchGroundCodeById - start and groundCodeId =" + groundCodeId);
        ResponseEntity<GroundCodeVO> response
            = postForEntity(RequestUriConstant.CLIENT_URI_GROUND_CODE, groundCodeId, GroundCodeVO.class);
        GroundCodeVO groundCodeVO = response.getBody();
        log.info("searchGroundCodeById - end and return " + groundCodeVO);
        return groundCodeVO;
    }

    @Override
    public List<GroundCodeVO> findAll() {
        log.info("findAll start -");
        ResponseEntity<List<GroundCodeVO>> postForEntity = this.exchange(RequestUriConstant.CLIENT_URI_GROUND_CODE_ALL,
            HttpMethod.GET, null, new ParameterizedTypeReference<List<GroundCodeVO>>() {});
        List<GroundCodeVO> body = postForEntity.getBody();
        log.info("findAll end - return: " + body);
        return body;
    }

}
