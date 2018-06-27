package hk.oro.iefas.web.system.systemadministration.systemsetting.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.exceptions.support.ErrorMsg;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.system.vo.OroInfoVO;
import hk.oro.iefas.domain.system.vo.SysNotificationVO;
import hk.oro.iefas.domain.system.vo.SystemParameterVO;
import hk.oro.iefas.domain.system.vo.SystemSettingPageVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.systemadministration.systemsetting.service.SystemSettingClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3010 $ $Date: 2018-06-08 18:55:10 +0800 (週五, 08 六月 2018) $
 * @author $Author: cwen $
 */
@Slf4j
@Named
public class SystemSettingClientServiceImpl extends BaseClientService implements SystemSettingClientService {
	
	private static final String LOGIN_FAIL_COUNT = "login fail count";
	private static final String LOCK_PERIOD = "Lock period";
	private static final String BUSINESS_OFFSET = "The offset of business date";
	private static final String PWD_COUNT = "User password history count";
	private static final String PWD_EXP_DAYS = "User password expired days";
	private static final String SESSION_NO = "Login Session No.";
	private static final String FPTEXPMIN = "FPTEXPMIN";
	
	@Autowired
	private AppResourceUtils appResourceUtils;

    @Override
    public SystemSettingPageVO loadSystemSettingPage() {
        log.info("loadSystemSettingPage() start ");
        SystemSettingPageVO body
            = postForObject(RequestUriConstant.CLIENT_URI_SYSTEM_SETTING_PAGE_LOAD, null, SystemSettingPageVO.class);
        log.info("loadSystemSettingPage() end ");
        return body;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResultPageVO<List<SystemParameterVO>> findAllSystemParameters() {
        log.info("findAllSystemParameters() start ");
        ResponseEntity<ResultPageVO> postForEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SYSTEM_SETTING_SYSPAR_LIST, null, ResultPageVO.class);
        ResultPageVO<List<SystemParameterVO>> body = postForEntity.getBody();
        log.info("findAllSystemParameters() end - " + body);
        return body;
    }

    @Override
    public OroInfoVO loadOroInformation() {
        log.info("loadOroInformation() start ");
        ResponseEntity<OroInfoVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SYSTEM_SETTING_ORO_INFO_LOAD, null, OroInfoVO.class);
        OroInfoVO body = responseEntity.getBody();
        log.info("loadOroInformation() end ");
        return body;
    }

    @Override
    public SystemSettingPageVO saveParameters(SystemSettingPageVO systemSettingPageVo) {
        log.info("saveParameters() start ");
        List<ErrorMsg> errorList = validateSaveSystemParameters(systemSettingPageVo.getSystemParameterList());
        if (errorList != null && !errorList.isEmpty()) {
        	for (ErrorMsg error : errorList) {
                Messages.addGlobalError(appResourceUtils.getMessage(error.getCode(), error.getFields()));
            }
        	return systemSettingPageVo;
        }
        SystemSettingPageVO result = postForObject(RequestUriConstant.CLIENT_URI_SYSTEM_SETTING_SYSPAR_SAVE,
            systemSettingPageVo, SystemSettingPageVO.class);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        log.info("saveParameters() end - " + result);
        return result;
    }

    @Override
    public OroInfoVO saveOroInformation(OroInfoVO oroInfo) {
        log.info("saveOroInformation() start ");
        OroInfoVO savedVo
            = postForObject(RequestUriConstant.CLIENT_URI_SYSTEM_SETTING_ORO_INFO_SAVE, oroInfo, OroInfoVO.class);
        log.info("saveOroInformation() end ");
        return savedVo;
    }

    @Override
    public SysNotificationVO saveSysNotification(SysNotificationVO sysNotificationVo) {
        log.info("saveSysNotification() start ");
        SysNotificationVO savedVo = postForObject(RequestUriConstant.CLIENT_URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_SAVE,
            sysNotificationVo, SysNotificationVO.class);
        log.info("saveSysNotification() end ");
        return savedVo;
    }
    
    private boolean isInteger (String input) {
    	try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return false;
		} catch(NullPointerException e) {
	        return false;
	    }
		return true;
    }
    
    private List<ErrorMsg> validateSaveSystemParameters(List<SystemParameterVO> systemParameterList) {
    	List<ErrorMsg> errorList = new ArrayList<>();
    	for (SystemParameterVO param : systemParameterList) {
    		String value = param.getParameterValue();
    		if (LOGIN_FAIL_COUNT.equalsIgnoreCase(param.getParameterDesc())) {
    			//Check these fields are integer or not.
    			if (!isInteger(value)) {
    				ErrorMsg error1 = new ErrorMsg(MsgCodeConstant.MSG_SHOULD_BE_INTEGER, MsgParamCodeConstant.LOGIN_FAIL_COUNT);
    				errorList.add(error1);
    			} else {
    				//if they are, check if they are gt 0
    				if (Integer.valueOf(value) <= 0 ) {
    					ErrorMsg error2 = new ErrorMsg(MsgCodeConstant.MSG_SHOULD_BE_GT_0, MsgParamCodeConstant.LOGIN_FAIL_COUNT);
        				errorList.add(error2);
    				}
    			}
    		} else if (LOCK_PERIOD.equalsIgnoreCase(param.getParameterDesc())) {
    			//Check these fields are integer or not.
    			if (!isInteger(value)) {
    				ErrorMsg error1 = new ErrorMsg(MsgCodeConstant.MSG_SHOULD_BE_INTEGER, MsgParamCodeConstant.LOCK_PERIOD);
    				errorList.add(error1);
    			} else {
    				//if they are, check if they are gt 0
    				if (Integer.valueOf(value) <= 0 ) {
    					ErrorMsg error2 = new ErrorMsg(MsgCodeConstant.MSG_IS_NEGATIVE, MsgParamCodeConstant.LOCK_PERIOD);
        				errorList.add(error2);
    				}
    			}
    		} else if (PWD_COUNT.equalsIgnoreCase(param.getParameterDesc())) {
    			//Check these fields are integer or not.
    			if (!isInteger(value)) {
    				ErrorMsg error1 = new ErrorMsg(MsgCodeConstant.MSG_SHOULD_BE_INTEGER, MsgParamCodeConstant.PWD_COUNT);
    				errorList.add(error1);
    			} else {
    				//if they are, check if they are gt 0
    				if (Integer.valueOf(value) <= 0 ) {
    					ErrorMsg error2 = new ErrorMsg(MsgCodeConstant.MSG_IS_NEGATIVE, MsgParamCodeConstant.PWD_COUNT);
        				errorList.add(error2);
    				}
    			}
    		}  else if (PWD_EXP_DAYS.equalsIgnoreCase(param.getParameterDesc())) {
    			//Check these fields are integer or not.
    			if (!isInteger(value)) {
    				ErrorMsg error1 = new ErrorMsg(MsgCodeConstant.MSG_SHOULD_BE_INTEGER, MsgParamCodeConstant.PWD_EXP_DAYS);
    				errorList.add(error1);
    			} else {
    				//if they are, check if they are gt 0
    				if (Integer.valueOf(value) <= 0 ) {
    					ErrorMsg error2 = new ErrorMsg(MsgCodeConstant.MSG_IS_NEGATIVE, MsgParamCodeConstant.PWD_EXP_DAYS);
        				errorList.add(error2);
    				}
    			}
    		} else if (SESSION_NO.equalsIgnoreCase(param.getParameterDesc())) {
    			//Check these fields are integer or not.
    			if (!isInteger(value)) {
    				ErrorMsg error1 = new ErrorMsg(MsgCodeConstant.MSG_SHOULD_BE_INTEGER, MsgParamCodeConstant.SESSION_NO);
    				errorList.add(error1);
    			} else {
    				//if they are, check if they are gt 0
    				if (Integer.valueOf(value) <= 0 ) {
    					ErrorMsg error2 = new ErrorMsg(MsgCodeConstant.MSG_IS_NEGATIVE, MsgParamCodeConstant.SESSION_NO);
        				errorList.add(error2);
    				}
    			}
    		} else if (BUSINESS_OFFSET.equalsIgnoreCase(param.getParameterDesc())) {
    			//Check these fields are integer or not.
    			if (!isInteger(value)) {
    				ErrorMsg error1 = new ErrorMsg(MsgCodeConstant.MSG_SHOULD_BE_INTEGER, MsgParamCodeConstant.BUSINESS_OFFSET);
    				errorList.add(error1);
    			} else {
    				//if they are, check if they are gt 0
    				if (Integer.valueOf(value) < 0 ) {
    					ErrorMsg error2 = new ErrorMsg(MsgCodeConstant.MSG_IS_NEGATIVE, MsgParamCodeConstant.BUSINESS_OFFSET);
        				errorList.add(error2);
    				}
    			}
    		} else if (FPTEXPMIN.equalsIgnoreCase(param.getParameterDesc())) {
    			//Check these fields are integer or not.
    			if (!isInteger(value)) {
    				ErrorMsg error1 = new ErrorMsg(MsgCodeConstant.MSG_SHOULD_BE_INTEGER, MsgParamCodeConstant.FPTEXPMIN);
    				errorList.add(error1);
    			} else {
    				//if they are, check if they are gt 0
    				if (Integer.valueOf(value) <= 0 ) {
    					ErrorMsg error2 = new ErrorMsg(MsgCodeConstant.MSG_IS_NEGATIVE, MsgParamCodeConstant.FPTEXPMIN);
        				errorList.add(error2);
    				}
    			}
    		} 
    	}
    	return errorList;
    }

}
