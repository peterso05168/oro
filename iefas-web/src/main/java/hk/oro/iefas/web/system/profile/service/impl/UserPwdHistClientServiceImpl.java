/**
 * 
 */
package hk.oro.iefas.web.system.profile.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.security.vo.UserPwdHistVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.profile.service.UserPwdHistClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class UserPwdHistClientServiceImpl extends BaseClientService implements UserPwdHistClientService {

    @Override
    public Integer saveUserPwdHist(UserPwdHistVO userPwdHistVO) {
        log.info("saveUserPwdHist() start - " + userPwdHistVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_USER_PWD_HIST_SAVE, userPwdHistVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveUserPwdHist() end - User Pwd Hist Id: " + body);
        return body;
    }

    @Override
    public List<UserPwdHistVO> findByUserAcId(SearchVO<Integer> searchVO) {
        log.info("findByUserAcId() start");
        ResponseEntity<List<UserPwdHistVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_USER_PWD_HIST_FINDBY_USER_AC_ID, HttpMethod.POST,
                new HttpEntity<SearchVO<Integer>>(searchVO), new ParameterizedTypeReference<List<UserPwdHistVO>>() {});
        List<UserPwdHistVO> body = responseEntity.getBody();
        log.info("findByUserAcId() end - " + body);
        return body;
    }

}
