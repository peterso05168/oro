package hk.oro.iefas.ws.bank.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.bank.dto.BankAccountInfoResultDTO;
import hk.oro.iefas.domain.bank.dto.BankInfoDTO;
import hk.oro.iefas.domain.bank.dto.SearchBankAccountInfoCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface BankAccountService {
    Page<BankAccountInfoResultDTO> findByCriteria(SearchDTO<SearchBankAccountInfoCriteriaDTO> criteriaDTO);

    Integer save(BankInfoDTO bankInfo);

    BankInfoDTO findOne(Integer bankInfoId);

    List<BankInfoDTO> findAll();

    boolean existsByBankCodeAndBankInfoIdNot(String bankCode, Integer bankInfoId);

    boolean existsByBankNameAndBankInfoIdNot(String bankName, Integer bankInfoId);

    boolean existsByBankShortNameAndBankInfoIdNot(String bankShortName, Integer bankInfoId);

    BankInfoDTO findByBankName(String bankName);
}
