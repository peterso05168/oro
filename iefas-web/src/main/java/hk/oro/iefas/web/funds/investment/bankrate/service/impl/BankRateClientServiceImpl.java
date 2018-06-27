package hk.oro.iefas.web.funds.investment.bankrate.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.vo.BankDepositTypeVO;
import hk.oro.iefas.domain.bank.vo.BankRateVO;
import hk.oro.iefas.domain.bank.vo.CreateUploadBankRateVO;
import hk.oro.iefas.domain.bank.vo.SearchUploadBankRateCriteriaVO;
import hk.oro.iefas.domain.bank.vo.UploadBankRateVO;
import hk.oro.iefas.domain.report.DownloadFileVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.funds.investment.bankrate.service.BankRateClientService;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Named
public class BankRateClientServiceImpl extends BaseClientService implements BankRateClientService {

    @Override
    public ResultPageVO<List<BankRateVO>>
        searchUploadBankRateList(SearchVO<SearchUploadBankRateCriteriaVO> searchCriteria) {
        ResponseEntity<ResultPageVO<List<BankRateVO>>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_BANK_RATE_LIST, HttpMethod.POST,
                new HttpEntity<SearchVO<SearchUploadBankRateCriteriaVO>>(searchCriteria),
                new ParameterizedTypeReference<ResultPageVO<List<BankRateVO>>>() {});
        ResultPageVO<List<BankRateVO>> body = responseEntity.getBody();
        return body;
    }

    @Override
    public Integer createUploadBankRate(CreateUploadBankRateVO createUploadBankRateVO) {
        Integer body
            = this.postForObject(RequestUriConstant.CLIENT_URI_BANK_RATE_SAVE, createUploadBankRateVO, Integer.class);

        return body;
    }

    @Override
    public UploadBankRateVO searchUploadBankRate(Integer bankRateId) {
        UploadBankRateVO body = this.postForObject(RequestUriConstant.CLIENT_URI_BANK_RATE_DETAIL,
            bankRateId == null ? 0 : bankRateId, UploadBankRateVO.class);
        return body;
    }

    @Override
    public Boolean createUploadBankRateValidate(Date investDate) {
        return this.postForObject(RequestUriConstant.CLIENT_URI_BANK_RATE_SAVE_VALIDATE, investDate, Boolean.class);
    }

    @Override
    public List<BankDepositTypeVO> findBankDepositTypeList() {
        ResponseEntity<List<BankDepositTypeVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_BANK_DEPOSIT_TYPE_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BankDepositTypeVO>>() {});
        List<BankDepositTypeVO> body = responseEntity.getBody();
        return body;
    }

    @Override
    public Integer saveDailyBankRateList(UploadBankRateVO uploadBankRate) {
        return this.postForObject(RequestUriConstant.CLIENT_URI_BANK_RATE_LIST_SAVE, uploadBankRate, Integer.class);
    }

    @Override
    public DownloadFileVO downloadBankRateTemplate() {
        return this.getForObject(RequestUriConstant.CLIENT_URI_BANKRATE_TEMPLATE_DOWNLOAD, DownloadFileVO.class);
    }

}
