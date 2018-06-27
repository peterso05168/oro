/**
 * 
 */
package hk.oro.iefas.web.system.divisionadministration.user.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.security.vo.SearchUserCriteriaVO;
import hk.oro.iefas.domain.security.vo.SearchUserResultVO;
import hk.oro.iefas.domain.security.vo.UserAccountVO;
import hk.oro.iefas.domain.security.vo.UserDetailVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserAccountClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class UserAccountClientServiceImpl extends BaseClientService implements UserAccountClientService {

    @Override
    public UserAccountVO findByLoginName(String loginName) {
        log.info("findByLoginName() start - Login Name: " + loginName);
        ResponseEntity<UserAccountVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_USER_FINDBY_LOGINNAME, loginName, UserAccountVO.class);
        UserAccountVO body = responseEntity.getBody();
        log.info("findByLoginName() end - " + body);
        return body;
    }

    @Override
    public UserDetailVO findUserDetail(String loginName) {
        log.info("findUserDetail() start - Login Name: " + loginName);
        ResponseEntity<UserDetailVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_USER_DETAIL, loginName, UserDetailVO.class);
        UserDetailVO body = responseEntity.getBody();
        log.info("findUserDetail() end - " + body);
        return body;
    }

    @Override
    public void clearLock(String loginName) {
        log.info("clearLock() start - Login Name: " + loginName);
        postForEntity(RequestUriConstant.CLIENT_URI_USER_CLEARLOCK, loginName, Void.class);
        log.info("clearLock() end");
    }

    @Override
    public Integer save(UserAccountVO userAccountVO) {
        log.info("save() start - " + userAccountVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_USER_SAVE, userAccountVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("save() end");
        return body;
    }

    @Override
    public ResultPageVO<List<SearchUserResultVO>> searchUserList(SearchVO<SearchUserCriteriaVO> searchVO) {
        log.info("searchUserList() start - " + searchVO);
        ResponseEntity<ResultPageVO<List<SearchUserResultVO>>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_USER_FINDBY_CRITERIA, HttpMethod.POST,
                new HttpEntity<SearchVO<SearchUserCriteriaVO>>(searchVO),
                new ParameterizedTypeReference<ResultPageVO<List<SearchUserResultVO>>>() {});
        ResultPageVO<List<SearchUserResultVO>> body = responseEntity.getBody();
        log.info("searchUserList() end - " + body);
        return body;
    }

    @Override
    public Integer insertUserDetail(UserAccountVO userAccountVO) {
        log.info("insertUserDetail() start - " + userAccountVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_USER_INSERT, userAccountVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("insertUserDetail() end - User Account Id: " + body);
        return body;
    }

    @Override
    public Integer updateUserDetail(UserAccountVO userAccountVO) {
        log.info("updateUserDetail() start - " + userAccountVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_USER_UPDATE, userAccountVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("updateUserDetail() end- User Account Id: " + body);
        return body;
    }

    @Override
    public UserAccountVO findOne(Integer id) {
        log.info("findByLoginName() start - Id: " + id);
        ResponseEntity<UserAccountVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_USER_FINDONE, id, UserAccountVO.class);
        UserAccountVO body = responseEntity.getBody();
        log.info("findByLoginName() end - " + body);
        return body;
    }

}
