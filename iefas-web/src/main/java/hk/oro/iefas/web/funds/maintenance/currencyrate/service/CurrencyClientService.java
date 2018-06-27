package hk.oro.iefas.web.funds.maintenance.currencyrate.service;

import java.util.List;

import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.bank.vo.CurrencyVO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface CurrencyClientService {

    /**
     * Save Currency
     * 
     * @param currencyVO
     * @return Currency Id
     */
    Integer save(CurrencyVO currencyVO);

    /**
     * Find one currency by id
     * 
     * @param curcyId
     * @return CurrencyVO
     */
    CurrencyVO findOne(Integer curcyId);

    /**
     * Whether currency code is exist
     * 
     * @param curcyCode
     * @param curcyId
     * @return Boolean
     */
    Boolean existsByCurcyCode(String curcyCode, Integer curcyId);

    /**
     * Find all active currency record
     * 
     * @return list of Currency
     */
    List<CurrencyBasicInfoVO> findAll();
}
