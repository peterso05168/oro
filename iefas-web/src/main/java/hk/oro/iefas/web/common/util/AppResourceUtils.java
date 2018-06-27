/**
 * 
 */
package hk.oro.iefas.web.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.session.Session;
import org.springframework.session.hazelcast.HazelcastSessionRepository;
import org.springframework.web.context.request.RequestContextHolder;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.SysParamConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.system.vo.ErrorMessageParamVO;
import hk.oro.iefas.domain.system.vo.ErrorMessageVO;
import hk.oro.iefas.domain.system.vo.SystemParameterVO;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.service.ErrorMessageClientService;
import hk.oro.iefas.web.common.service.ErrorMessageParamClientService;
import hk.oro.iefas.web.common.service.SystemNotificationClientService;
import hk.oro.iefas.web.common.service.SystemParameterClientService;
import hk.oro.iefas.web.core.jsf.scope.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3303 $ $Date: 2018-06-26 14:28:09 +0800 (週二, 26 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Named(value = AppResourceUtils.BEAN_NAME)
@ApplicationScoped
public class AppResourceUtils {

    public final static String BEAN_NAME = "appResourceUtils";

    private final static String SYS_PARAM_TYPE_INT = "INT";
    private final static String SYS_PARAM_TYPE_VAR = "VAR";

    @Inject
    private SystemParameterClientService systemParameterClientService;

    @Inject
    private ErrorMessageClientService errorMessageClientService;

    @Inject
    private ErrorMessageParamClientService errorMessageParamClientService;

    @Inject
    private HazelcastSessionRepository hazelcastSessionRepository;

    @Inject
    private CommonClientService commonClientService;

    @Inject
    private SystemNotificationClientService systemNotificationClientService;

    @Getter
    @Setter
    @Value(value = "${server.session.timeout:0}")
    private Integer sessionTimeout;

    private Map<String, Object> sysParams = new HashMap<>();
    private Map<String, ErrorMessageVO> msgs = new HashMap<>();
    private Map<String, ErrorMessageParamVO> msgParams = new HashMap<>();
    private Map<String, Map<String, ApplicationCodeTableVO>> appCodeTables = new LinkedHashMap<>();

    @PostConstruct
    private void init() {
        log.info("======AppResourceUtils init======");
        initSysParams();
        initErrMsgs();
        initErrMsgParams();
        initAppCodeTables();
    }

    private void initAppCodeTables() {
        log.info("initAppCodeTables start");
        List<ApplicationCodeTableVO> allCodeTable = commonClientService.searchAllCodeTable();
        if (CommonUtils.isNotEmpty(allCodeTable)) {
            for (ApplicationCodeTableVO act : allCodeTable) {
                if (!appCodeTables.containsKey(act.getGroupingCode())) {
                    appCodeTables.put(act.getGroupingCode(), new LinkedHashMap<>());
                }
                appCodeTables.get(act.getGroupingCode()).put(act.getCodeValue(), act);

            }
        }
        log.info("initAppCodeTables end");
    }

    private void initErrMsgParams() {
        log.info("initErrMsgParams start");
        List<ErrorMessageParamVO> msgParamsList = errorMessageParamClientService.findAll();
        msgParamsList.parallelStream().forEach(param -> msgParams.put(param.getParamCode(), param));
        log.info("initErrMsgParams end");
    }

    private void initErrMsgs() {
        log.info("initErrMsgs start");
        List<ErrorMessageVO> msgsList = errorMessageClientService.findAll();
        msgsList.parallelStream().forEach(msg -> msgs.put(msg.getErrorMessageCode(), msg));
        log.info("initErrMsgs end");
    }

    private void initSysParams() {
        log.info("initSysParams start");
        List<SystemParameterVO> sysParams = systemParameterClientService.findAll();
        if (CommonUtils.isNotEmpty(sysParams)) {
            sysParams.stream().forEach(param -> {
                if (param.getParameterValue() != null) {
                    switch (param.getParameterValueType()) {
                        case SYS_PARAM_TYPE_INT:
                            this.sysParams.put(param.getModuleCode() + "_" + param.getSubModuleCode(),
                                Integer.valueOf(param.getParameterValue()));
                            break;
                        case SYS_PARAM_TYPE_VAR:
                            this.sysParams.put(param.getModuleCode() + "_" + param.getSubModuleCode(),
                                String.valueOf(param.getParameterValue()));
                            break;
                        default:
                            this.sysParams.put(param.getModuleCode() + "_" + param.getSubModuleCode(),
                                param.getParameterValue());
                            break;
                    }
                }
            });
        }
        log.info("initSysParams end");
    }

    public Object getSysParam(String key) {
        return this.sysParams.get(key);
    }

    public Date getBusinessDate() {
        return DateUtils.addDays(new Date(), getBusinessOffset());
    }

    public String getFormatedBusinessDate() {
        return new SimpleDateFormat(CoreConstant.DATE_TIME_PATTERN).format(getBusinessDate());
    }

    public Integer getLoginFailCount() {
        log.info("getLoginFailCount() start");
        Integer sysParam = (Integer)getSysParam(SysParamConstant.LOGIN_FAIL_COUNT);
        log.info("getLoginFailCount() end - ReturnVal: " + sysParam);
        return sysParam;
    }

    public Integer getLoginLockPeriod() {
        log.info("getLoginLockPeriod() start");
        Integer sysParam = (Integer)getSysParam(SysParamConstant.LOGIN_LOCK_PERIOD);
        log.info("getLoginLockPeriod() end - ReturnVal: " + sysParam);
        return sysParam;
    }

    public Integer getLoginSessionNo() {
        log.info("getLoginSessionNo() start");
        Integer sysParam = (Integer)getSysParam(SysParamConstant.LOGIN_SESSION);
        log.info("getLoginSessionNo() end - ReturnVal: " + sysParam);
        return sysParam;
    }

    public String getPasswordRegex() {
        log.info("getPasswordRegex() start");
        String sysParam = (String)getSysParam(SysParamConstant.USER_PASSWORD);
        log.info("getPasswordRegex() end - ReturnVal: " + sysParam);
        return sysParam;
    }

    public String getDateFormat() {
        log.info("getDateFormat() start");
        String sysParam = (String)getSysParam(SysParamConstant.COM_DATEFORMAT);
        log.info("getDateFormat() end - ReturnVal: " + sysParam);
        return sysParam;
    }

    public String getRateFormat() {
        log.info("getRateFormat() start");
        String sysParam = (String)getSysParam(SysParamConstant.COM_RATEFORMAT);
        log.info("getRateFormat() end - ReturnVal: " + sysParam);
        return sysParam;
    }

    public String getMoneyFormat() {
        log.info("getMoneyFormat() start");
        String sysParam = (String)getSysParam(SysParamConstant.COM_MONEYFORMAT);
        log.info("getMoneyFormat() end - ReturnVal: " + sysParam);
        return sysParam;
    }

    public String getMoneyMillionFormat() {
        log.info("getMoneyMillionFormat() start");
        String sysParam = (String)getSysParam(SysParamConstant.COM_MONEYMILLIONFORMAT);
        log.info("getMoneyMillionFormat() end - ReturnVal: " + sysParam);
        return sysParam;
    }

    public Integer getBusinessOffset() {
        log.info("getBusinessOffset() start");
        Integer sysParam = (Integer)getSysParam(SysParamConstant.COM_BUSINESSOFFSET);
        log.info("getBusinessOffset() end - ReturnVal: " + sysParam);
        return sysParam;
    }

    public Integer getPwdHistCount() {
        log.info("getPwdHistCount() start");
        Integer sysParam = (Integer)getSysParam(SysParamConstant.USER_PWDCOUNT);
        log.info("getPwdHistCount() end - ReturnVal: " + sysParam);
        return sysParam;
    }

    public Integer getPwdExpDay() {
        log.info("getPwdExpDay() start");
        Integer sysParam = (Integer)getSysParam(SysParamConstant.USER_PWDEXPDAY);
        log.info("getPwdExpDay() end - ReturnVal: " + sysParam);
        return sysParam;
    }

    public String getRequiredMessage(String field) {
        log.info("getRequiredMessage() start - field: " + field);
        ErrorMessageParamVO errorMessageParamVO = msgParams.get(field);
        if (errorMessageParamVO == null) {
            return field;
        }

        ErrorMessageVO errorMessageVO = msgs.get(MsgCodeConstant.MSG_IS_MANDATORY);
        if (errorMessageVO == null) {
            return field;
        }
        String requiredMessage
            = String.format(errorMessageVO.getMessageContent(), errorMessageParamVO.getParamContent());
        log.info("getRequiredMessage() end requiredMessage=" + requiredMessage);
        return requiredMessage;
    }

    public String getSessionTimeoutMsg() {
        return getMessage(MsgCodeConstant.MSG_SESSION_TIMEOUT);
    }

    public String getNotEqMsg(String param1, String param2) {
        return getMessage(MsgCodeConstant.MSG_NOT_EQUAL, param1, param2);
    }

    public String getInvalidFormatMsg(String param) {
        return getMessage(MsgCodeConstant.MSG_INVALID_FORMAT, param);
    }

    public void reloadSysParams() {
        log.info("reloadSysParams() start");
        this.initSysParams();
        log.info("reloadSysParams() end");
    }
    
    public String getInEngWordMsg() {
        return getMessage(MsgCodeConstant.MSG_IN_ENG_WORD);
    }

    public String getMessage(String msgCode, String... params) {
        log.info("getMessage() start");

        ErrorMessageVO errorMessageVO = msgs.get(msgCode);
        if (errorMessageVO == null) {
            return msgCode;
        }

        String msg = null;
        if (CommonUtils.isNotEmpty(params)) {
            Object[] paramArray = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                ErrorMessageParamVO errorMessageParamVO = msgParams.get(params[i]);
                if (errorMessageParamVO != null) {
                    paramArray[i] = errorMessageParamVO.getParamContent();
                }
            }

            msg = String.format(errorMessageVO.getMessageContent(), paramArray);
        } else {
            msg = String.format(errorMessageVO.getMessageContent());
        }

        log.info("getMessage() end");
        return msg;

    }

    public String getMessage(String msgCode) {
        log.info("getMessage() start");

        String msg = String.format(getMessageContent(msgCode));

        log.info("getMessage() end");
        return msg;

    }

    public String getMessageContent(String msgCode) {
        log.info("getMessage() start");

        ErrorMessageVO errorMessageVO = msgs.get(msgCode);
        if (errorMessageVO == null) {
            return msgCode;
        }

        String msg = errorMessageVO.getMessageContent();

        log.info("getMessage() end");
        return msg;

    }

    public String getMessageParam(String msgParamCode) {
        log.info("getMessageParam() start");

        ErrorMessageParamVO paramVO = msgParams.get(msgParamCode);
        if (paramVO == null) {
            return msgParamCode;
        }

        String msg = String.format(paramVO.getParamContent());

        log.info("getMessageParam() end");
        return msg;

    }

    public Session getCurrentSession() {
        log.info("getCurrentSession() start");
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        log.info("SessionId: " + sessionId);
        Session session = hazelcastSessionRepository.getSession(sessionId);
        log.info("getCurrentSession() end");
        return session;
    }

    public List<ApplicationCodeTableVO> getApplicationCodeByGroup(String groupingCode) {
        log.info("getApplicationCodeByGroup() start and groupingCode = " + groupingCode);
        List<ApplicationCodeTableVO> result = null;
        if (CommonUtils.isNotBlank(groupingCode)) {
            Map<String, ApplicationCodeTableVO> map = appCodeTables.get(groupingCode);
            if (map != null) {
                result = new ArrayList<ApplicationCodeTableVO>(map.values());
            }
        }
        log.info("getApplicationCodeByGroup() end and result = " + result);
        return result;
    }

    public List<StatusVO> getStatusListByGroup(String groupingCode) {
        log.info("getApplicationCodeByGroup() start and groupingCode = " + groupingCode);
        List<StatusVO> result = null;
        if (CommonUtils.isNotBlank(groupingCode)) {
            List<ApplicationCodeTableVO> codeTableList = getApplicationCodeByGroup(groupingCode);
            if (codeTableList != null) {
                result = codeTableList.stream().map(item -> {
                    return new StatusVO(item.getApplicationCodeTableId(), item.getCodeDesc(), item.getCodeValue());
                }).collect(Collectors.toList());
            }
        }
        log.info("getApplicationCodeByGroup() end and result = " + result);
        return result;
    }

    // public String getSysNotificationContent() {
    // log.info("getSysNotificationContent() start");
    // String result = systemNotificationClientService.findSysNotification().getNotificationContent();
    // log.info("getSysNotificationContent() end and result = " + result);
    // return result;
    // }
}
