package hk.oro.iefas.web.ledger.maintenance.controlaccount.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.vo.ControlAccountVO;
import hk.oro.iefas.domain.shroff.vo.SearchControlAccountVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.maintenance.controlaccount.service.ControlAccountClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
public class ControlAccountClientServiceImpl extends BaseClientService implements ControlAccountClientService {

    @Override
    public List<ControlAccountVO> findAllControlAccounts() {
        log.info("findControlAccounts() start -  ");
        ResponseEntity<List<ControlAccountVO>> responseEntity
            = super.exchange(RequestUriConstant.CLIENT_URI_CONTROL_ACCOUNT_FIND_ALL_CONTROL_ACCOUNT, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<ControlAccountVO>>() {});
        List<ControlAccountVO> controlAccountVOs = responseEntity.getBody();
        log.info("findControlAccounts() end -  ");
        return controlAccountVOs;
    }

    @Override
    public ControlAccountVO getControlAccountDetail(Integer ctlAcId) {
        log.info("getControlAccountDetail() start -  : " + ctlAcId);
        ResponseEntity<ControlAccountVO> ctlAcVOResponseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_CONTROL_ACCOUNT_GET_CONTROL_ACCOUNT_DETAIL, ctlAcId, ControlAccountVO.class);
        ControlAccountVO result = ctlAcVOResponseEntity.getBody();
        log.info("getControlAccountDetail end - " + result);
        return result;
    }

    @Override
    public Integer saveControlAccount(ControlAccountVO controlAccountVO) {
        log.info("saveControlAccount() start - controlAccountVO : " + controlAccountVO);
        ResponseEntity<Integer> idEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_CONTROL_ACCOUNT_SAVE_CONTROL_ACCOUNT, controlAccountVO, Integer.class);
        Integer ctlAcId = idEntity.getBody();
        log.info("saveControlAccount end - " + ctlAcId);
        return ctlAcId;
    }

    @Override
    public Boolean existsByControlAccountName(SearchControlAccountVO criteria) {
        log.info("existsByControlAccountName start - criteria: " + criteria);
        Boolean result = null;
        ResponseEntity<Boolean> responseEntity = this.postForEntity(
            RequestUriConstant.CLIENT_URI_CONTROL_ACCOUNT_EXISTS_BY_CONTROL_ACCOUNT_NAME, criteria, Boolean.class);
        if (responseEntity != null) {
            result = responseEntity.getBody();
        }
        log.info("existsByControlAccountName end - " + result);
        return result;
    }

    @Override
    public Boolean existsByControlAccountCode(SearchControlAccountVO criteria) {
        log.info("existsByControlAccountCode start - criteria: " + criteria);
        Boolean result = null;
        ResponseEntity<Boolean> responseEntity = this.postForEntity(
            RequestUriConstant.CLIENT_URI_CONTROL_ACCOUNT_EXISTS_BY_CONTROL_ACCOUNT_CODE, criteria, Boolean.class);
        if (responseEntity != null) {
            result = responseEntity.getBody();
        }
        log.info("existsByControlAccountCode end - " + result);
        return result;
    }

}
