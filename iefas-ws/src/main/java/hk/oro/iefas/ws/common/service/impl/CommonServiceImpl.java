package hk.oro.iefas.ws.common.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.exceptions.BusinessException;
import hk.oro.iefas.core.security.UserInfo;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjType;
import hk.oro.iefas.domain.casemgt.dto.CaseAccountTypeDTO;
import hk.oro.iefas.domain.casemgt.entity.CaseAccountType;
import hk.oro.iefas.domain.casemgt.entity.CaseType;
import hk.oro.iefas.domain.casemgt.entity.OutsiderType;
import hk.oro.iefas.domain.common.dto.ApplicationCodeTableDTO;
import hk.oro.iefas.domain.common.dto.CaseTypeDTO;
import hk.oro.iefas.domain.common.dto.OutsiderTypeDTO;
import hk.oro.iefas.domain.common.dto.StatusDTO;
import hk.oro.iefas.domain.dividend.dto.AdjudicationTypeDTO;
import hk.oro.iefas.domain.dividend.dto.SysApprovalWfDTO;
import hk.oro.iefas.domain.security.entity.Privilege;
import hk.oro.iefas.domain.shroff.dto.AnalysisCodeDTO;
import hk.oro.iefas.domain.shroff.dto.AnalysisCodeTypeDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentTypeDTO;
import hk.oro.iefas.domain.shroff.dto.TransferAmountTypeDTO;
import hk.oro.iefas.domain.shroff.dto.VoucherTypeDTO;
import hk.oro.iefas.domain.shroff.entity.AnalysisCode;
import hk.oro.iefas.domain.shroff.entity.AnalysisCodeType;
import hk.oro.iefas.domain.shroff.entity.PaymentType;
import hk.oro.iefas.domain.shroff.entity.ShrTxfAmountType;
import hk.oro.iefas.domain.shroff.entity.VoucherType;
import hk.oro.iefas.domain.system.dto.SysRejectReasonDTO;
import hk.oro.iefas.domain.system.dto.SysWorkFlowRuleDTO;
import hk.oro.iefas.domain.system.entity.ApplicationCodeTable;
import hk.oro.iefas.domain.system.entity.SysApprovalWf;
import hk.oro.iefas.domain.system.entity.SysRejectReason;
import hk.oro.iefas.ws.casemgt.repository.CaseAccountTypeRepository;
import hk.oro.iefas.ws.casemgt.repository.CaseTypeRepository;
import hk.oro.iefas.ws.casemgt.repository.OutsiderTypeRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.CaseAccountTypeDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.assembler.CaseTypeDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.assembler.OutsiderTypeDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.predicate.CaseAccountTypePredicate;
import hk.oro.iefas.ws.casemgt.repository.predicate.CaseTypePredicate;
import hk.oro.iefas.ws.casemgt.repository.predicate.OutsiderTypePredicate;
import hk.oro.iefas.ws.common.service.CommonService;
import hk.oro.iefas.ws.common.util.RequestContextUtils;
import hk.oro.iefas.ws.core.constant.SysSequenceEnum;
import hk.oro.iefas.ws.dividend.repository.AdjTypeRepository;
import hk.oro.iefas.ws.dividend.repository.AnalysisCodeRepository;
import hk.oro.iefas.ws.dividend.repository.AnalysisCodeTypeRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.AdjTypeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.AnalysisCodeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.AnalysisCodeTypeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.SysRejectReasonDTOAssembler;
import hk.oro.iefas.ws.security.repository.PrivilegeRepository;
import hk.oro.iefas.ws.shroff.repository.PaymentTypeRepository;
import hk.oro.iefas.ws.shroff.repository.TransferAmountTypeRepository;
import hk.oro.iefas.ws.shroff.repository.VoucherTypeRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.PaymentTypeDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.TransferAmountTypeDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.VoucherTypeDTOAssembler;
import hk.oro.iefas.ws.system.repository.ApplicationCodeTableRepository;
import hk.oro.iefas.ws.system.repository.SysApprovalWFRepository;
import hk.oro.iefas.ws.system.repository.SysRejectReasonRepository;
import hk.oro.iefas.ws.system.repository.assembler.ApplicationCodeTableDTOAssembler;
import hk.oro.iefas.ws.system.repository.predicate.ApplicationCodeTablePredicate;
import hk.oro.iefas.ws.system.repository.predicate.SysRejectReasonPredicate;
import hk.oro.iefas.ws.system.service.SysSequenceService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3217 $ $Date: 2018-06-20 14:01:41 +0800 (週三, 20 六月 2018) $
 * @author $Author: scott.feng $
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private SysSequenceService sysSequenceService;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private SysApprovalWFRepository sysApprovalWFRepository;

    @Autowired
    private CaseTypeRepository caseTypeRepository;

    @Autowired
    private AdjTypeRepository adjTypeRepository;

    @Autowired
    private SysRejectReasonRepository sysRejectReasonRepository;

    @Autowired
    private CaseTypeDTOAssembler caseTypeDTOAssembler;

    @Autowired
    private CaseAccountTypeRepository caseAccountTypeRepository;

    @Autowired
    private CaseAccountTypeDTOAssembler caseAccountTypeDTOAssembler;

    @Autowired
    private OutsiderTypeRepository outsiderTypeRepository;

    @Autowired
    private OutsiderTypeDTOAssembler outsiderTypeDTOAssembler;

    @Autowired
    private ApplicationCodeTableRepository applicationCodeTableRepository;

    @Autowired
    private ApplicationCodeTableDTOAssembler applicationCodeTableDTOAssembler;

    @Autowired
    private AnalysisCodeRepository analysisCodeRepository;

    @Autowired
    private AnalysisCodeDTOAssembler analysisCodeDTOAssembler;

    @Autowired
    private AnalysisCodeTypeRepository analysisCodeTypeRepository;

    @Autowired
    private AnalysisCodeTypeDTOAssembler analysisCodeTypeDTOAssembler;

    @Autowired
    private VoucherTypeRepository voucherTypeRepository;

    @Autowired
    private VoucherTypeDTOAssembler voucherTypeDTOAssembler;

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    private PaymentTypeDTOAssembler paymentTypeDTOAssembler;

    @Autowired
    private AdjTypeDTOAssembler adjTypeDTOAssembler;

    @Autowired
    private SysRejectReasonDTOAssembler sysRejectReasonDTOAssembler;

    @Autowired
    private TransferAmountTypeRepository transferAmountTypeRepository;

    @Autowired
    private TransferAmountTypeDTOAssembler transferAmountTypeDTOAssembler;

    @Transactional(readOnly = true)
    @Override
    public List<StatusDTO> searchStatusList() {
        log.info("searchStatusList start -");
        List<StatusDTO> returnVal = null;
        List<ApplicationCodeTableDTO> list = this.findByGroupingCode(ApplicationCodeTableEnum.SYS.name());
        if (CommonUtils.isNotEmpty(list)) {
            returnVal = new ArrayList<StatusDTO>();
            for (ApplicationCodeTableDTO applicationCodeTableDTO : list) {
                StatusDTO statusDTO = new StatusDTO();
                statusDTO.setStatusName(applicationCodeTableDTO.getCodeDesc());
                statusDTO.setStatusVal(applicationCodeTableDTO.getCodeValue());
                returnVal.add(statusDTO);
            }
        }
        log.info("searchStatusList end - rerurn: " + returnVal);
        return returnVal;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CaseTypeDTO> searchCaseTypeList() {
        log.info("searchCaseTypeList start -");
        Iterable<CaseType> iterable
            = caseTypeRepository.findAll(CaseTypePredicate.findAll(), CaseTypePredicate.order());
        List<CaseTypeDTO> list = null;
        if (!IterableUtils.isEmpty(iterable)) {
            list = caseTypeDTOAssembler.toDTOList(iterable);
        }
        log.info("searchCaseTypeList end - return: " + list);
        return list;
    }

    @Override
    public List<CaseTypeDTO> searchDividendCaseTypeList() {
        log.info("searchDividendCaseTypeList start -");
        Iterable<CaseType> iterable = caseTypeRepository.findAll(
            CaseTypePredicate.findByDividendRequired(DividendConstant.DIVIDEND_REQUIRED), CaseTypePredicate.order());
        List<CaseTypeDTO> list = null;
        if (!IterableUtils.isEmpty(iterable)) {
            list = caseTypeDTOAssembler.toDTOList(iterable);
        }
        log.info("searchDividendCaseTypeList end - return: " + list);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationCodeTableDTO> searchCalculationMethod() {
        log.info("searchCalculationMethod start -");
        List<ApplicationCodeTableDTO> list = this.findByGroupingCode(ApplicationCodeTableEnum.CAL.name());
        log.info("searchCalculationMethod end - rerurn: " + list);
        return list;
    }

    private List<ApplicationCodeTableDTO> findByGroupingCode(String groupingCode) {
        log.info("findByGroupingCode start - param: " + groupingCode);
        Iterable<ApplicationCodeTable> iterable = applicationCodeTableRepository.findAll(
            ApplicationCodeTablePredicate.findByGroupingCode(groupingCode), ApplicationCodeTablePredicate.order());
        List<ApplicationCodeTableDTO> list = null;
        if (iterable != null) {
            list = applicationCodeTableDTOAssembler.toDTOList(iterable);
        }
        log.info("findByGroupingCode end - return: " + list);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<OutsiderTypeDTO> searchOutsiderTypeList() {
        log.info("searchOutsiderTypeList start -");
        Iterable<OutsiderType> iterable
            = outsiderTypeRepository.findAll(OutsiderTypePredicate.findAll(), OutsiderTypePredicate.order());
        List<OutsiderTypeDTO> list = null;
        if (!IterableUtils.isEmpty(iterable)) {
            list = outsiderTypeDTOAssembler.toDTOList(iterable);
        }
        log.info("searchOutsiderTypeList end - return: " + list);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CaseAccountTypeDTO> searchCaseAccountTypeList() {
        log.info("searchCaseAccountTypeList start -");
        Iterable<CaseAccountType> iterable
            = caseAccountTypeRepository.findAll(CaseAccountTypePredicate.findAll(), CaseAccountTypePredicate.order());
        List<CaseAccountTypeDTO> list = null;
        if (!IterableUtils.isEmpty(iterable)) {
            list = caseAccountTypeDTOAssembler.toDTOList(iterable);
        }
        log.info("searchCaseAccountTypeList end - return: " + list);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationCodeTableDTO> searchAddressType() {
        log.info("searchAddressType start -");
        List<ApplicationCodeTableDTO> list = this.findByGroupingCode(ApplicationCodeTableEnum.ADT.name());
        log.info("searchAddressType end - rerurn: " + list);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationCodeTableDTO> searchNatureOfClaim() {
        log.info("searchNatureOfClaim start -");
        List<ApplicationCodeTableDTO> list = this.findByGroupingCode(ApplicationCodeTableEnum.NOC.name());
        log.info("searchNatureOfClaim end - return: " + list);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationCodeTableDTO> searchPaymentType() {
        log.info("searchPaymentType start -");
        List<ApplicationCodeTableDTO> list = this.findByGroupingCode(ApplicationCodeTableEnum.DPA.name());
        log.info("searchPaymentType end - rerurn: " + list);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationCodeTableDTO> searchDividendScheduleStatus() {
        log.info("searchDividendScheduleStatus start -");
        List<ApplicationCodeTableDTO> list = this.findByGroupingCode(ApplicationCodeTableEnum.DSS.name());
        log.info("searchDividendScheduleStatus end - rerurn: " + list);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationCodeTableDTO> searchPledgeType() {
        log.info("searchPledgeType start -");
        List<ApplicationCodeTableDTO> list = this.findByGroupingCode(ApplicationCodeTableEnum.DPT.name());
        log.info("searchPledgeType end - rerurn: " + list);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationCodeTableDTO> searchDividendScheduleType() {
        log.info("searchDividendScheduleType start -");
        List<ApplicationCodeTableDTO> list = this.findByGroupingCode(ApplicationCodeTableEnum.DST.name());
        log.info("searchDividendScheduleType end - rerurn: " + list);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<AnalysisCodeDTO> searchAnalysisCodeList() {
        log.info("searchAnalysisCodeList - start");
        List<AnalysisCodeDTO> result = null;
        List<AnalysisCode> analysisCodeList = analysisCodeRepository.findAll();
        if (analysisCodeList != null) {
            result = analysisCodeDTOAssembler.toDTOList(analysisCodeList);
        }
        log.info("searchAnalysisCodeList - end and result=" + result);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationCodeTableDTO> searchAllCodeTable() {
        log.info("searchAllCodeTable - start");
        Iterable<ApplicationCodeTable> iterable
            = applicationCodeTableRepository.findAll(ApplicationCodeTablePredicate.orderById());
        List<ApplicationCodeTableDTO> result = applicationCodeTableDTOAssembler.toDTOList(iterable);
        log.info("searchAllCodeTable - end " + result);
        return result;
    }

    @Override
    public List<ApplicationCodeTableDTO> searchPreferentialAdjudicationType() {
        log.info("searchPreferentialAdjudicationType start -");
        Iterable<ApplicationCodeTable> iterable = applicationCodeTableRepository.findAll(
            ApplicationCodeTablePredicate.findByGroupingCode(ApplicationCodeTableEnum.PAJ.name()),
            ApplicationCodeTablePredicate.orderById());
        List<ApplicationCodeTableDTO> list = null;
        if (iterable != null) {
            list = applicationCodeTableDTOAssembler.toDTOList(iterable);
        }
        log.info("searchPreferentialAdjudicationType end - rerurn: " + list);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<AnalysisCodeTypeDTO> searchAnalysisCodeTypeList() {
        log.info("searchAnalysisCodeTypeList - start");
        List<AnalysisCodeTypeDTO> result = null;
        List<AnalysisCodeType> analysisCodeTypeList = analysisCodeTypeRepository.findAll();
        if (analysisCodeTypeList != null) {
            result = analysisCodeTypeDTOAssembler.toDTOList(analysisCodeTypeList);
        }
        log.info("searchAnalysisCodeTypeList - end and result=" + result);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<VoucherTypeDTO> searchVoucherTypeList() {
        log.info("searchVoucherTypeList - start");
        List<VoucherTypeDTO> result = null;
        List<VoucherType> voucherTypeList = voucherTypeRepository.findAll();
        if (voucherTypeList != null) {
            result = voucherTypeDTOAssembler.toDTOList(voucherTypeList);
        }
        log.info("searchVoucherTypeList - end and result=" + result);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PaymentTypeDTO> searchPaymentTypeList() {
        log.info("searchPaymentType start -");
        List<PaymentTypeDTO> result = null;
        List<PaymentType> paymentTypeList = paymentTypeRepository.findAll();
        if (paymentTypeList != null) {
            result = paymentTypeDTOAssembler.toDTOList(paymentTypeList);
        }
        log.info("searchPaymentTypeList end - return: " + result);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<AdjudicationTypeDTO> searchAdjTypeList() {
        log.info("searchAdjTypeList - start");
        List<AdjType> list = adjTypeRepository.findAll();
        List<AdjudicationTypeDTO> dtoList = adjTypeDTOAssembler.toDTOList(list);
        log.info("searchAdjTypeList - end return : " + dtoList);
        return dtoList;
    }

    @Override
    public List<SysRejectReasonDTO> searchRejectReasonList() {
        log.info("searchRejectReasonList - start");
        Iterable<SysRejectReason> iterable = sysRejectReasonRepository
            .findAll(SysRejectReasonPredicate.findActiveList(), SysRejectReasonPredicate.order());
        List<SysRejectReasonDTO> dtoList = sysRejectReasonDTOAssembler.toDTOList(iterable);
        log.info("searchRejectReasonList - end return : " + dtoList);
        return dtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransferAmountTypeDTO> searchTransferAmountTypeList() {
        log.info("searchTransferAmountTypeList - start");
        List<ShrTxfAmountType> list = transferAmountTypeRepository.findAll();
        List<TransferAmountTypeDTO> dtoList = transferAmountTypeDTOAssembler.toDTOList(list);
        log.info("searchTransferAmountTypeList - end return : " + dtoList);
        return dtoList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveWorkFlowHistory(Integer workFlowId, SysWorkFlowRuleDTO sysWorkFlowRuleDTO, String remark) {
        log.info("saveWorkFlowHistory() start - WorkFlowId: " + workFlowId + ", SysWorkFlowRuleDTO: "
            + sysWorkFlowRuleDTO + ", Remark: " + remark);
        UserInfo currentUserInfo = RequestContextUtils.getCurrentUserInfo();
        ApplicationCodeTableDTO actionDone = sysWorkFlowRuleDTO.getActionDone();
        if (actionDone != null) {
            SysApprovalWfDTO sysApprovalWf = new SysApprovalWfDTO();
            sysApprovalWf.setAction(actionDone.getCodeDesc());
            sysApprovalWf.setActionByPerson(currentUserInfo.getUserName());
            sysApprovalWf.setActionByPostId(currentUserInfo.getPostId());
            sysApprovalWf.setDivisionId(currentUserInfo.getDivisionId());
            sysApprovalWf.setPrivilegeCode(sysWorkFlowRuleDTO.getPrivilege().getPrivilegeCode());
            sysApprovalWf.setRemark(remark);
            workFlowId = saveSysApprovalWf(workFlowId, sysApprovalWf);
        }

        ApplicationCodeTableDTO actionToBeTaken = sysWorkFlowRuleDTO.getActionToBeTaken();
        if (actionToBeTaken != null) {
            SysApprovalWfDTO sysApprovalWf = new SysApprovalWfDTO();
            sysApprovalWf.setAction(actionToBeTaken.getCodeDesc());
            sysApprovalWf.setActionByPerson(currentUserInfo.getUserName());
            sysApprovalWf.setActionByPostId(currentUserInfo.getPostId());
            sysApprovalWf.setDivisionId(currentUserInfo.getDivisionId());
            sysApprovalWf.setPrivilegeCode(sysWorkFlowRuleDTO.getPrivilege().getPrivilegeCode());
            sysApprovalWf.setRemark(remark);
            workFlowId = saveSysApprovalWf(workFlowId, sysApprovalWf);
        }

        log.info("saveWorkFlowHistory() end - WorkFlowId: " + workFlowId);
        return workFlowId;
    }

    public Integer saveSysApprovalWf(Integer workFlowId, SysApprovalWfDTO sysApprovalWf) {
        if (workFlowId == null || workFlowId.intValue() <= 0) {
            workFlowId = sysSequenceService.generateIncrSeq(SysSequenceEnum.WORK_FLOW_ID.name()).intValue();
        }
        if (sysApprovalWf != null && workFlowId != null) {
            SysApprovalWf wf = DataUtils.copyProperties(sysApprovalWf, SysApprovalWf.class);
            wf.setWorkflowId(workFlowId);
            int seq = 0;
            List<SysApprovalWf> sysApprovalWfs = sysApprovalWFRepository
                .findByWorkflowIdAndStatusIgnoreCaseOrderByApprovalWorkflowId(workFlowId, CoreConstant.STATUS_ACTIVE);
            if (CommonUtils.isNotEmpty(sysApprovalWfs)) {
                seq = sysApprovalWfs.size();
            }

            wf.setSeq(new BigDecimal(seq));
            wf.setStatus(CoreConstant.STATUS_ACTIVE);
            wf.setActionBy(sysApprovalWf.getActionByPostId());
            wf.setActionByDivision(sysApprovalWf.getDivisionId());
            wf.setIsLatest(BigDecimal.ZERO);
            Privilege privilege = null;
            if (sysApprovalWf.getPrivilegeCode() != null && !"".equals(sysApprovalWf.getPrivilegeCode().trim())) {
                privilege = privilegeRepository.findByPrivilegeCode(sysApprovalWf.getPrivilegeCode());
            }
            if (privilege != null) {
                wf.setPrivilege(privilege);
            } else {
                // TODO
                throw new BusinessException("No privilege found.");
            }
            sysApprovalWFRepository.save(wf);
        }
        return workFlowId;
    }
}
