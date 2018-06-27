package hk.oro.iefas.ws.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.bank.dto.BankDepositTypeDTO;
import hk.oro.iefas.domain.bank.dto.BankRateDTO;
import hk.oro.iefas.domain.bank.dto.CreateUploadBankRateDTO;
import hk.oro.iefas.domain.bank.dto.SearchUploadBankRateCriteriaDTO;
import hk.oro.iefas.domain.bank.dto.UploadBankRateDTO;
import hk.oro.iefas.domain.report.DownloadFileDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface BankRateService {

    public Page<BankRateDTO> searchUploadBankRateList(SearchDTO<SearchUploadBankRateCriteriaDTO> searchCriteria);

    public Integer createUploadBankRate(CreateUploadBankRateDTO createUploadBankRateDTO);

    public boolean createUploadBankRateValidate(Date investDate);

    public UploadBankRateDTO searchUploadBankRate(Integer bankRateId);

    public Integer saveDailyBankRateList(UploadBankRateDTO uploadBankRateDTO);

    public List<BankDepositTypeDTO> findBankDepositTypeList();

    public DownloadFileDTO downloadBankRateTemplate();
}
