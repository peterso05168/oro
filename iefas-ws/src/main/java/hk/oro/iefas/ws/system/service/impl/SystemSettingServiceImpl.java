/**
 * 
 */
package hk.oro.iefas.ws.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.exceptions.BusinessException;
import hk.oro.iefas.core.exceptions.support.ErrorMsg;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.system.dto.ErrorMessageDTO;
import hk.oro.iefas.domain.system.dto.OroInfoDTO;
import hk.oro.iefas.domain.system.dto.SysNotificationDTO;
import hk.oro.iefas.domain.system.dto.SystemParameterDTO;
import hk.oro.iefas.domain.system.entity.OroInfo;
import hk.oro.iefas.domain.system.entity.SysNotification;
import hk.oro.iefas.domain.system.entity.SystemParameter;
import hk.oro.iefas.ws.system.repository.OroInfoRepository;
import hk.oro.iefas.ws.system.repository.SysNotificationRepository;
import hk.oro.iefas.ws.system.repository.SystemParameterRepository;
import hk.oro.iefas.ws.system.repository.assembler.OroInformationDTOAssembler;
import hk.oro.iefas.ws.system.repository.assembler.SysNotificationDTOAssembler;
import hk.oro.iefas.ws.system.repository.assembler.SystemParameterDTOAssembler;
import hk.oro.iefas.ws.system.repository.predicate.OroInformationPredicate;
import hk.oro.iefas.ws.system.repository.predicate.SysNotificationPredicate;
import hk.oro.iefas.ws.system.repository.predicate.SystemParameterPredicate;
import hk.oro.iefas.ws.system.service.SystemSettingService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3300 $ $Date: 2018-06-26 14:22:17 +0800 (週二, 26 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Service
public class SystemSettingServiceImpl implements SystemSettingService {

    @Autowired
    private SystemParameterRepository systemParameterRepository;

    @Autowired
    private OroInfoRepository oroInfoRepository;

    @Autowired
    private SysNotificationRepository sysNotificationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SystemParameterDTO> findAllSystemParameters() {
        log.info("findAllSystemParameters() start");
        List<SystemParameterDTO> resultList = systemParameterRepository.findAll(SystemParameterDTOAssembler.toDTO(),
            SystemParameterPredicate.findAll());
        resultList = filterTransactionKeyInSystemParamList(resultList);
        log.info("findAllSystemParameters() end - " + resultList);
        return resultList;
    }

    private List<SystemParameterDTO> filterTransactionKeyInSystemParamList(List<SystemParameterDTO> sysParamList) {
        return sysParamList.stream().filter(sysParam -> !"transaction key".equals(sysParam.getParameterDesc()))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OroInfoDTO loadOroInformation() {
        log.info("loadOroInformation() start");
        List<OroInfoDTO> resultList
            = oroInfoRepository.findAll(OroInformationDTOAssembler.toDTO(), OroInformationPredicate.findAll());
        OroInfoDTO result = null;
        // Suppose this table just have 1 record.
        if (resultList != null && resultList.size() > 0) {
            result = resultList.get(0);
        } else {
            // error message.
            result = new OroInfoDTO();
        }
        log.info("loadOroInformation() end - " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<SystemParameterDTO> saveParameters(List<SystemParameterDTO> parameters) {
        log.info("saveParameters() start - " + parameters);
        // Validation - to be implemented

        // Do save()
        List<SystemParameter> sysParEntities = new ArrayList<>();
        sysParEntities = DataUtils.copyPropertiesForList(parameters, SystemParameter.class);
        List<SystemParameter> savedSysParEntities = systemParameterRepository.save(sysParEntities);
        List<SystemParameterDTO> resultList = systemParameterRepository.findAll(SystemParameterDTOAssembler.toDTO(),
            SystemParameterPredicate.findAll());
        log.info("saveParameters() end - " + resultList);
        return resultList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OroInfoDTO saveOroInformation(OroInfoDTO oroInfoDto) {
        log.info("saveOroInformation() start - " + oroInfoDto);
        OroInfo oroInfoEntity = DataUtils.copyProperties(oroInfoDto, OroInfo.class);
        OroInfo savedOroInfoEntity = oroInfoRepository.save(oroInfoEntity);
        OroInfoDTO savedOroInfoDto = DataUtils.copyProperties(savedOroInfoEntity, OroInfoDTO.class);
        log.info("saveOroInformation() end - " + savedOroInfoDto);
        return savedOroInfoDto;
    }

    @Override
    @Transactional(readOnly = true)
    public SysNotificationDTO loadSysNotification() {
        log.info("loadSysNotification() start");
        List<SysNotificationDTO> resultList = sysNotificationRepository.findAll(SysNotificationDTOAssembler.toDTO(),
            SysNotificationPredicate.findAll());
        SysNotificationDTO result = null;
        // // Suppose this table just have 1 record.
        if (resultList != null && resultList.size() > 0) {
            result = resultList.get(0);
        } else {
            // error message.
            result = new SysNotificationDTO();
        }
        log.info("loadSysNotification() end - " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public String loadEffectiveSysNotificationContent() {
        log.info("loadEffectiveSysNotificationContent() start");
        List<SysNotificationDTO> resultList = sysNotificationRepository.findAll(SysNotificationDTOAssembler.toDTO(),
            SysNotificationPredicate.findEffective());
        String result = null;
        // // Suppose this table just have 1 record.
        if (resultList != null && resultList.size() > 0) {
            result = resultList.get(0).getNotificationContent();
        } else {
            // error message.
            result = null;
        }
        log.info("loadEffectiveSysNotificationContent() end - " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SysNotificationDTO saveSysNotification(SysNotificationDTO sysNotificationDTO) {
        log.info("saveOroInformation() start - " + sysNotificationDTO);
        List<ErrorMsg> errorList = validateSaveSysNotification(sysNotificationDTO);
        if (errorList.size() > 0) {
            throw new BusinessException("Validate saveSysNotification failed.", errorList);
        } else {
            SysNotification sysNotificationDTOEntity
                = DataUtils.copyProperties(sysNotificationDTO, SysNotification.class);
            SysNotification savedsysNotificationEntity = sysNotificationRepository.save(sysNotificationDTOEntity);
            SysNotificationDTO savedsysNotificationDto
                = DataUtils.copyProperties(savedsysNotificationEntity, SysNotificationDTO.class);
            log.info("saveOroInformation() end - " + savedsysNotificationDto);
            return savedsysNotificationDto;
        }
    }

    private List<ErrorMsg> validateSaveSysNotification(SysNotificationDTO sysNotificationDTO) {
        List<ErrorMsg> errMsgList = new ArrayList<>();
        /** validation 1 - Sys_Notification_Content should not be null or empty **/
        if (sysNotificationDTO != null && sysNotificationDTO.getNotificationContent() == null
            || sysNotificationDTO.getNotificationContent().trim().isEmpty()) {
            ErrorMsg error1 = new ErrorMsg(MsgCodeConstant.MSG_IS_MANDATORY, MsgParamCodeConstant.CONTENT);
            errMsgList.add(error1);
        }
        /** validation 2 - Sys_Notification_Content should be <= 4000 characters **/
        if (sysNotificationDTO != null && sysNotificationDTO.getNotificationContent() != null
            && sysNotificationDTO.getNotificationContent().length() > 4000) {
            ErrorMsg error2 = new ErrorMsg(MsgCodeConstant.MSG_OUT_OF_RANGE, MsgParamCodeConstant.CONTENT);
            errMsgList.add(error2);
        }
        /** validation 3 - Sys_Notification_Effective_Start_Date should be before end date **/
        if (sysNotificationDTO != null && sysNotificationDTO.getNotificationEffStartDate() != null) {
            if (sysNotificationDTO.getNotificationEffEndDate() != null) {
                Date startDate = sysNotificationDTO.getNotificationEffStartDate();
                Date endDate = sysNotificationDTO.getNotificationEffEndDate();
                if (!DateUtils.isSameDay(startDate, endDate) && startDate.after(endDate)) {
                    ErrorMsg error3 = new ErrorMsg(MsgCodeConstant.MSG_NOT_BEFORE,
                        MsgParamCodeConstant.EFFECTIVE_END_DATE, MsgParamCodeConstant.EFFECTIVE_START_DATE);
                    errMsgList.add(error3);
                }
            } else {
                ErrorMsg error4
                    = new ErrorMsg(MsgCodeConstant.MSG_IS_MANDATORY, MsgParamCodeConstant.EFFECTIVE_END_DATE);
                errMsgList.add(error4);
            }
        } else {
            ErrorMsg error5 = new ErrorMsg(MsgCodeConstant.MSG_IS_MANDATORY, MsgParamCodeConstant.EFFECTIVE_START_DATE);
            errMsgList.add(error5);
        }
        return errMsgList;
    }

    private List<ErrorMessageDTO> validateSystemParameters(List<SystemParameterDTO> parameters) {
        List<ErrorMessageDTO> resultList = new ArrayList<>();
        for (SystemParameterDTO param : parameters) {
            if (param != null) {
                if (param.getParameterDesc() == null || param.getParameterDesc().trim().isEmpty()) {
                    ErrorMessageDTO error = new ErrorMessageDTO();
                    // error.SET
                }
                if (param.getParameterValue() == null || param.getParameterValue().trim().isEmpty()) {

                }
            }
        }
        return resultList;
    }
}
