package hk.oro.iefas.ws.common.service;

import java.util.List;

import hk.oro.iefas.domain.casemgt.dto.CaseAccountTypeDTO;
import hk.oro.iefas.domain.common.dto.ApplicationCodeTableDTO;
import hk.oro.iefas.domain.common.dto.CaseTypeDTO;
import hk.oro.iefas.domain.common.dto.OutsiderTypeDTO;
import hk.oro.iefas.domain.common.dto.StatusDTO;
import hk.oro.iefas.domain.dividend.dto.AdjudicationTypeDTO;
import hk.oro.iefas.domain.dividend.dto.SysApprovalWfDTO;
import hk.oro.iefas.domain.shroff.dto.AnalysisCodeDTO;
import hk.oro.iefas.domain.shroff.dto.AnalysisCodeTypeDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentTypeDTO;
import hk.oro.iefas.domain.shroff.dto.TransferAmountTypeDTO;
import hk.oro.iefas.domain.shroff.dto.VoucherTypeDTO;
import hk.oro.iefas.domain.system.dto.SysRejectReasonDTO;
import hk.oro.iefas.domain.system.dto.SysWorkFlowRuleDTO;

/**
 * @version $Revision: 3122 $ $Date: 2018-06-13 17:34:56 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface CommonService {

    public List<StatusDTO> searchStatusList();

    public List<CaseTypeDTO> searchCaseTypeList();

    public List<CaseTypeDTO> searchDividendCaseTypeList();

    public List<ApplicationCodeTableDTO> searchCalculationMethod();

    public List<OutsiderTypeDTO> searchOutsiderTypeList();

    public List<CaseAccountTypeDTO> searchCaseAccountTypeList();

    public List<ApplicationCodeTableDTO> searchAddressType();

    public List<ApplicationCodeTableDTO> searchNatureOfClaim();

    public List<ApplicationCodeTableDTO> searchPaymentType();

    public List<ApplicationCodeTableDTO> searchDividendScheduleStatus();

    public List<ApplicationCodeTableDTO> searchPledgeType();

    public List<ApplicationCodeTableDTO> searchDividendScheduleType();

    public List<ApplicationCodeTableDTO> searchPreferentialAdjudicationType();

    public List<AnalysisCodeDTO> searchAnalysisCodeList();

    public List<ApplicationCodeTableDTO> searchAllCodeTable();

    public List<AnalysisCodeTypeDTO> searchAnalysisCodeTypeList();

    public List<VoucherTypeDTO> searchVoucherTypeList();

    public List<PaymentTypeDTO> searchPaymentTypeList();

    public List<AdjudicationTypeDTO> searchAdjTypeList();

    public List<SysRejectReasonDTO> searchRejectReasonList();

    public Integer saveWorkFlowHistory(Integer workFlowId, SysWorkFlowRuleDTO sysWorkFlowRuleDTO, String remark);

    public Integer saveSysApprovalWf(Integer workFlowId, SysApprovalWfDTO sysApprovalWf);

    public List<TransferAmountTypeDTO> searchTransferAmountTypeList();
}
