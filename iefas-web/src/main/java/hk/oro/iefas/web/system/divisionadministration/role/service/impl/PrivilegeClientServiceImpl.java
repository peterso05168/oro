package hk.oro.iefas.web.system.divisionadministration.role.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.security.vo.PrivilegeVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeResultVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.PrivilegeClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class PrivilegeClientServiceImpl extends BaseClientService implements PrivilegeClientService {

    @Override
    public Integer save(PrivilegeVO privilegeVO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SearchPrivilegeResultVO> findAll() {
        log.info("findAll() start");
        ResponseEntity<List<SearchPrivilegeResultVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_PRIVILEGE_LIST, HttpMethod.POST, null,
                new ParameterizedTypeReference<List<SearchPrivilegeResultVO>>() {});
        List<SearchPrivilegeResultVO> body = responseEntity.getBody();
        log.info("findAll() end - " + body);
        return body;
    }

    @Override
    public List<PrivilegeVO> findByPostId(Integer postId) {
        log.info("findByPost() start = PostId: " + postId);
        HttpEntity<Integer> entity = new HttpEntity<Integer>(postId);
        ResponseEntity<List<PrivilegeVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_PRIVILEGE_FINDBY_POSTID, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<PrivilegeVO>>() {});
        List<PrivilegeVO> result = responseEntity.getBody();
        log.info("findByPost end - " + result);
        return result;
    }

}
