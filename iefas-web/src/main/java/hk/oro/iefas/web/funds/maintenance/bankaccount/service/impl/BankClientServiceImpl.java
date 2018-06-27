package hk.oro.iefas.web.funds.maintenance.bankaccount.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.vo.BankInfoVO;
import hk.oro.iefas.domain.bank.vo.SearchBankAccountInfoCriteriaVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.funds.maintenance.bankaccount.service.BankClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class BankClientServiceImpl extends BaseClientService implements BankClientService {

    @Override
    public Integer save(BankInfoVO bankInfoVO) {
        log.info("create() start - " + bankInfoVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_BANK_ACCOUNT_SAVE, bankInfoVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("create() end - " + body);
        return body;
    }

    @Override
    public BankInfoVO findOne(Integer bankInfoId) {
        log.info("findOne() start - bankInfoId: " + bankInfoId);
        ResponseEntity<BankInfoVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_BANK_ACCOUNT_DETAIL, bankInfoId, BankInfoVO.class);
        BankInfoVO body = responseEntity.getBody();
        log.info("findOne() end - " + body);
        return body;
    }

    @Override
    public Boolean existsByBankCode(String bankCode, Integer bankInfoId) {
        log.info("existsByBankCode() start - bankCode: " + bankCode + ";bankInfoId: " + bankInfoId);
        SearchBankAccountInfoCriteriaVO searchVO = new SearchBankAccountInfoCriteriaVO();
        searchVO.setBankInfoId(bankInfoId);
        searchVO.setBankCode(bankCode);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_BANK_ACCOUNT_EXISTSBY_BANKCODE, searchVO, Boolean.class);
        Boolean bl = responseEntity.getBody();
        log.info("existsByBankCode() end - " + bl);
        return bl;
    }

    @Override
    public Boolean existsByBankName(String bankName, Integer bankInfoId) {
        log.info("existsByBankName() start - bankName: " + bankName + ";bankInfoId: " + bankInfoId);
        SearchBankAccountInfoCriteriaVO searchVO = new SearchBankAccountInfoCriteriaVO();
        searchVO.setBankInfoId(bankInfoId);
        searchVO.setBankName(bankName);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_BANK_ACCOUNT_EXISTSBY_BANKNAME, searchVO, Boolean.class);
        Boolean bl = responseEntity.getBody();
        log.info("existsByBankName() end - " + bl);
        return bl;
    }

    @Override
    public Boolean existsByBankShortName(String bankShortName, Integer bankInfoId) {
        log.info("existsByBankShortName() start - bankShortName: " + bankShortName + ";bankInfoId: " + bankInfoId);
        SearchBankAccountInfoCriteriaVO searchVO = new SearchBankAccountInfoCriteriaVO();
        searchVO.setBankInfoId(bankInfoId);
        searchVO.setBankShortName(bankShortName);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_BANK_ACCOUNT_EXISTSBY_BANKSHORTNAME, searchVO, Boolean.class);
        Boolean bl = responseEntity.getBody();
        log.info("existsByBankShortName() end - " + bl);
        return bl;
    }

    @Override
    public List<BankInfoVO> findAll() {
        log.info("findAll() strat - ");
        ResponseEntity<List<BankInfoVO>> postForEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_BANK_ACCOUNT_FIND_ALL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BankInfoVO>>() {});
        List<BankInfoVO> body = postForEntity.getBody();
        log.info("findAll() end - ");
        return body;
    }

    @Override
    public BankInfoVO findByBankName(String bankName) {
        log.info("findByBankName() strat - bankName: " + bankName);
        BankInfoVO bankInfoVO = this.postForObject(RequestUriConstant.CLIENT_URI_BANK_ACCOUNT_FINDBY_BANKNAME, bankName,
            BankInfoVO.class);
        log.info("findByBankName() end - return: " + bankInfoVO);
        return bankInfoVO;
    }
}
