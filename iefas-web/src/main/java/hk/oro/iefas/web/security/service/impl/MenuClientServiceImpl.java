package hk.oro.iefas.web.security.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.security.vo.MenuVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.security.service.MenuClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class MenuClientServiceImpl extends BaseClientService implements MenuClientService {

    @Override
    public List<MenuVO> findAllTopMenu() {
        log.info("findAll() start");
        ResponseEntity<List<MenuVO>> responseEntity = exchange(RequestUriConstant.CLIENT_URI_TOPMENU_LIST,
            HttpMethod.GET, null, new ParameterizedTypeReference<List<MenuVO>>() {});
        List<MenuVO> body = responseEntity.getBody();
        log.info("findAll() end - " + body);
        return body;
    }

    @Override
    public List<MenuVO> findByPostId(Integer postId) {
        log.info("findByPostId() start - PostId: " + postId);
        ResponseEntity<List<MenuVO>> responseEntity = exchange(RequestUriConstant.CLIENT_URI_MENU_FINDBY_POSTID,
            HttpMethod.POST, new HttpEntity<Integer>(postId), new ParameterizedTypeReference<List<MenuVO>>() {});
        List<MenuVO> body = responseEntity.getBody();
        log.info("findByPostId() end - " + body);
        return body;
    }

}
