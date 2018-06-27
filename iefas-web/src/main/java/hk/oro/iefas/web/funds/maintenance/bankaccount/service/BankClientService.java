package hk.oro.iefas.web.funds.maintenance.bankaccount.service;

import java.util.List;

import hk.oro.iefas.domain.bank.vo.BankInfoVO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface BankClientService {
    Integer save(BankInfoVO bankInfoVO);

    BankInfoVO findOne(Integer bankInfoId);

    Boolean existsByBankCode(String bankCode, Integer bankInfoId);

    Boolean existsByBankName(String bankName, Integer bankInfoId);

    Boolean existsByBankShortName(String bankShortName, Integer bankInfoId);

    List<BankInfoVO> findAll();

    BankInfoVO findByBankName(String bankName);
}
