/**
 * 
 */
package hk.oro.iefas.web.system.dashboard.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.system.vo.DashboardInfoVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.dashboard.service.DashboardInfoClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */

@Slf4j
@Service
public class DashboardInfoClientServiceImpl extends BaseClientService implements DashboardInfoClientService {

    @Override
    public List<DashboardInfoVO> findByPostId(Integer postId) {
        log.info("findByPostId() start - PostId: " + postId);
        ResponseEntity<List<DashboardInfoVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_DASHBOARD_INFO_FINDBY_POSTID, HttpMethod.POST,
                new HttpEntity<Integer>(postId), new ParameterizedTypeReference<List<DashboardInfoVO>>() {});
        List<DashboardInfoVO> result = responseEntity.getBody();
        log.info("findByPostId() end - " + result);
        return result;
    }

}
