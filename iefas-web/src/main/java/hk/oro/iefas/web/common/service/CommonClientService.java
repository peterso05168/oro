package hk.oro.iefas.web.common.service;

import java.util.List;

import hk.oro.iefas.domain.casemgt.vo.CaseAccountTypeVO;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.common.vo.CaseTypeVO;
import hk.oro.iefas.domain.common.vo.OutsiderTypeVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeTypeVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.domain.shroff.vo.ControlAccountTypeVO;
import hk.oro.iefas.domain.shroff.vo.PaymentFileTypeVO;
import hk.oro.iefas.domain.shroff.vo.PaymentTypeVO;
import hk.oro.iefas.domain.shroff.vo.TransferAmountTypeVO;
import hk.oro.iefas.domain.shroff.vo.VoucherTypeVO;
import hk.oro.iefas.domain.system.vo.SysRejectReasonVO;

/**
 * @version $Revision: 3122 $ $Date: 2018-06-13 17:34:56 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface CommonClientService {
    public List<CaseTypeVO> searchCaseTypeList();

    public List<CaseTypeVO> searchDividendCaseTypeList();

    public List<ApplicationCodeTableVO> searchCalculationMethod();

    public List<OutsiderTypeVO> searchOutsiderTypeList();

    public List<ApplicationCodeTableVO> searchAddressType();

    public List<ApplicationCodeTableVO> searchNatureOfClaim();

    public List<CaseAccountTypeVO> searchCaseAccountTypeList();

    public List<AnalysisCodeVO> searchAnalysisCodeList();

    public List<AnalysisCodeTypeVO> searchAnalysisCodeTypeList();

    public List<VoucherTypeVO> searchVoucherTypeList();

    public List<ApplicationCodeTableVO> searchAllCodeTable();

    public List<ApplicationCodeTableVO> searchPaymentType();

    public List<ControlAccountTypeVO> searchCtlAcTypeList();

    public List<PaymentTypeVO> searchPaymentTypeList();

    public List<PaymentFileTypeVO> searchPaymentFileTypeList();

    public List<SysRejectReasonVO> searchRejectReasonList();
    
    public List<TransferAmountTypeVO> searchTransferAmountTypeList();
}
