/**
 * 
 */
package hk.oro.iefas.web.security.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.security.vo.ForgotPwdLogVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.security.service.ForgotPwdLogClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class ForgotPwdLogClientServiceImpl extends BaseClientService implements ForgotPwdLogClientService {

    @Override
    public Integer save(ForgotPwdLogVO forgotPwdLogVO) {
        log.info("save() start - " + forgotPwdLogVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_FORGOT_PWD_LOG_SAVE, forgotPwdLogVO, Integer.class);
        Integer id = responseEntity.getBody();
        log.info("save() end Id: " + id);
        return id;
    }

    @Override
    public ForgotPwdLogVO findOne(Integer id) {
        log.info("findOne() start Id: " + id);
        ResponseEntity<ForgotPwdLogVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_FORGOT_PWD_LOG_FINDONE, id, ForgotPwdLogVO.class);
        ForgotPwdLogVO forgotPwdLogVO = responseEntity.getBody();
        log.info("findOne() end - " + forgotPwdLogVO);
        return forgotPwdLogVO;
    }

    @Override
    public String createForgotPwdLog(Integer userAcId, Date forgotPwdDate) {
        log.info("createForgotPwdLog() start UserAcId: " + userAcId);
        LinkedMultiValueMap<Object, Object> linkedMultiValueMap = new LinkedMultiValueMap<>();
        linkedMultiValueMap.add("userAcId", userAcId);
        linkedMultiValueMap.add("forgotPwdDate",
            new SimpleDateFormat(CoreConstant.DATE_TIME_PATTERN).format(forgotPwdDate));
        ResponseEntity<String> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_FORGOT_PWD_LOG_CREATE, linkedMultiValueMap, String.class);
        String token = responseEntity.getBody();
        log.info("createForgotPwdLog() end - Token: " + token);
        return token;
    }

    @Override
    public ForgotPwdLogVO findByToken(String token) {
        log.info("findByToken() start token: " + token);
        ResponseEntity<ForgotPwdLogVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_FORGOT_PWD_LOG_FINDBY_TOKEN, token, ForgotPwdLogVO.class);
        ForgotPwdLogVO forgotPwdLogVO = responseEntity.getBody();
        log.info("findByToken() end - " + forgotPwdLogVO);
        return forgotPwdLogVO;
    }

}
