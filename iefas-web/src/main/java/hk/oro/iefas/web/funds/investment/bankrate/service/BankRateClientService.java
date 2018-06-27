package hk.oro.iefas.web.funds.investment.bankrate.service;

import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.bank.vo.BankDepositTypeVO;
import hk.oro.iefas.domain.bank.vo.BankRateVO;
import hk.oro.iefas.domain.bank.vo.CreateUploadBankRateVO;
import hk.oro.iefas.domain.bank.vo.SearchUploadBankRateCriteriaVO;
import hk.oro.iefas.domain.bank.vo.UploadBankRateVO;
import hk.oro.iefas.domain.report.DownloadFileVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public interface BankRateClientService {

    public ResultPageVO<List<BankRateVO>>
        searchUploadBankRateList(SearchVO<SearchUploadBankRateCriteriaVO> searchCriteria);

    public Integer createUploadBankRate(CreateUploadBankRateVO createUploadBankRateVO);

    public Boolean createUploadBankRateValidate(Date investDate);

    public UploadBankRateVO searchUploadBankRate(Integer bankRateId);

    public List<BankDepositTypeVO> findBankDepositTypeList();

    public Integer saveDailyBankRateList(UploadBankRateVO uploadBankRate);

    public DownloadFileVO downloadBankRateTemplate();
}
