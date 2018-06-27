package hk.oro.iefas.web.common.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
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
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.core.service.BaseClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3122 $ $Date: 2018-06-13 17:34:56 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Named
public class CommonClientServiceImpl extends BaseClientService implements CommonClientService {

    @Override
    public List<CaseTypeVO> searchCaseTypeList() {
        log.info("searchCaseTypeList start -");
        ResponseEntity<List<CaseTypeVO>> postForEntity = this.exchange(RequestUriConstant.CLIENT_URI_CASE_TYPE_LIST,
            HttpMethod.GET, null, new ParameterizedTypeReference<List<CaseTypeVO>>() {});

        List<CaseTypeVO> body = postForEntity.getBody();
        log.info("searchCaseTypeList end - return: " + body);
        return body;
    }

    @Override
    public List<CaseTypeVO> searchDividendCaseTypeList() {
        log.info("searchDividendCaseTypeList start -");
        ResponseEntity<List<CaseTypeVO>> postForEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_DIVIDEND_CASE_TYPE_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CaseTypeVO>>() {});

        List<CaseTypeVO> body = postForEntity.getBody();
        log.info("searchDividendCaseTypeList end - return: " + body);
        return body;
    }

    @Override
    public List<ApplicationCodeTableVO> searchCalculationMethod() {
        log.info("searchCalculationMethod start -");
        ResponseEntity<List<ApplicationCodeTableVO>> postForEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_CALCULATION_METHOD, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ApplicationCodeTableVO>>() {});

        List<ApplicationCodeTableVO> body = postForEntity.getBody();
        log.info("searchCaseTypeList end - return: " + body);
        return body;
    }

    @Override
    public List<OutsiderTypeVO> searchOutsiderTypeList() {
        log.info("searchOutsiderTypeList start -");
        ResponseEntity<List<OutsiderTypeVO>> postForEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_OUTSIDER_TYPE_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<OutsiderTypeVO>>() {});
        List<OutsiderTypeVO> body = postForEntity.getBody();
        log.info("searchOutsiderTypeList end - return: " + body);
        return body;
    }

    @Override
    public List<ApplicationCodeTableVO> searchAddressType() {
        log.info("searchAddressType start -");
        ResponseEntity<List<ApplicationCodeTableVO>> postForEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_ADDRESS_TYPE_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ApplicationCodeTableVO>>() {});
        List<ApplicationCodeTableVO> body = postForEntity.getBody();
        log.info("searchAddressType end - return: " + body);
        return body;
    }

    @Override
    public List<ApplicationCodeTableVO> searchNatureOfClaim() {
        log.info("searchNatureOfClaim start -");
        ResponseEntity<List<ApplicationCodeTableVO>> postForEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_NATURE_OF_CLAIM, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ApplicationCodeTableVO>>() {});
        List<ApplicationCodeTableVO> body = postForEntity.getBody();
        log.info("searchNatureOfClaim end - return: " + body);
        return body;
    }

    @Override
    public List<CaseAccountTypeVO> searchCaseAccountTypeList() {
        log.info("searchCaseAccountTypeList start -");
        ResponseEntity<List<CaseAccountTypeVO>> postForEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_CASE_ACCOUNT_TYPE_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CaseAccountTypeVO>>() {});

        List<CaseAccountTypeVO> body = postForEntity.getBody();
        log.info("searchCaseAccountTypeList end - return: " + body);
        return body;
    }

    @Override
    public List<AnalysisCodeVO> searchAnalysisCodeList() {
        log.info("searchAnalysisCodeList start - ");
        ResponseEntity<List<AnalysisCodeVO>> response
            = this.exchange(RequestUriConstant.CLIENT_URI_SEARCH_ANALYSIS_CODE_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AnalysisCodeVO>>() {});
        List<AnalysisCodeVO> result = response.getBody();
        log.info("searchAnalysisCodeList end - return: " + result);
        return result;
    }

    @Override
    public List<ApplicationCodeTableVO> searchAllCodeTable() {
        log.info("searchAllCodeTable - start");
        ResponseEntity<List<ApplicationCodeTableVO>> response
            = this.exchange(RequestUriConstant.CLIENT_URI_SEARCH_ALL_CODE_TABLE, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ApplicationCodeTableVO>>() {});
        List<ApplicationCodeTableVO> result = response.getBody();
        log.info("searchAllCodeTable - end " + result);
        return result;
    }

    @Override
    public List<AnalysisCodeTypeVO> searchAnalysisCodeTypeList() {
        log.info("searchAnalysisCodeTypeList start - ");
        ResponseEntity<List<AnalysisCodeTypeVO>> response
            = this.exchange(RequestUriConstant.CLIENT_URI_SEARCH_ANALYSIS_CODE_TYPE_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AnalysisCodeTypeVO>>() {});
        List<AnalysisCodeTypeVO> result = response.getBody();
        log.info("searchAnalysisCodeTypeList end - return: " + result);
        return result;
    }

    @Override
    public List<VoucherTypeVO> searchVoucherTypeList() {
        log.info("searchVoucherTypeList start - ");
        ResponseEntity<List<VoucherTypeVO>> response = this.exchange(RequestUriConstant.CLIENT_URI_SEARCH_VOUCHER_TYPE,
            HttpMethod.GET, null, new ParameterizedTypeReference<List<VoucherTypeVO>>() {});
        List<VoucherTypeVO> result = response.getBody();
        log.info("searchVoucherTypeList end - return: " + result);
        return result;
    }

    @Override
    public List<ApplicationCodeTableVO> searchPaymentType() {
        log.info("searchPaymentType - start");
        ResponseEntity<List<ApplicationCodeTableVO>> response
            = this.exchange(RequestUriConstant.CLIENT_URI_PAYMENT_TYPE_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ApplicationCodeTableVO>>() {});
        List<ApplicationCodeTableVO> result = response.getBody();
        log.info("searchPaymentType - end " + result);
        return result;
    }

    @Override
    public List<ControlAccountTypeVO> searchCtlAcTypeList() {
        log.info("searchCtlAcTypeList() start");
        ResponseEntity<List<ControlAccountTypeVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_CONTROL_ACCOUNT_TYPE_FIND_ALL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ControlAccountTypeVO>>() {});
        List<ControlAccountTypeVO> result = responseEntity.getBody();
        log.info("searchCtlAcTypeList end - " + result);
        return result;
    }

    @Override
    public List<PaymentTypeVO> searchPaymentTypeList() {
        log.info("searchPaymentTypeList - start");
        ResponseEntity<List<PaymentTypeVO>> response
            = this.exchange(RequestUriConstant.CLIENT_SEARCH_URI_PAYMENT_TYPE_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<PaymentTypeVO>>() {});
        List<PaymentTypeVO> result = response.getBody();
        log.info("searchPaymentType - end " + result);
        return result;
    }

    @Override
    public List<PaymentFileTypeVO> searchPaymentFileTypeList() {
        log.info("searchPaymentFileTypeList start");
        ResponseEntity<List<PaymentFileTypeVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_FIND_ALL_PAYMENT_FILE_TYPE, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<PaymentFileTypeVO>>() {});
        List<PaymentFileTypeVO> result = responseEntity.getBody();
        log.info("searchPaymentFileTypeList end - " + result);
        return result;
    }

    @Override
    public List<SysRejectReasonVO> searchRejectReasonList() {
        log.info("searchRejectReasonList - start");
        ResponseEntity<List<SysRejectReasonVO>> response
            = this.exchange(RequestUriConstant.CLIENT_SEARCH_URI_REJECT_REASON_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<SysRejectReasonVO>>() {});
        List<SysRejectReasonVO> result = response.getBody();
        log.info("searchRejectReasonList - end " + result);
        return result;
    }

    @Override
    public List<TransferAmountTypeVO> searchTransferAmountTypeList() {
        log.info("searchPaymentFileTypeList start");
        ResponseEntity<List<TransferAmountTypeVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_SEARCH_TRANSFER_AMOUNT_TYPE_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TransferAmountTypeVO>>() {});
        List<TransferAmountTypeVO> result = responseEntity.getBody();
        log.info("searchPaymentFileTypeList end - " + result);
        return result;
    }
}
