package hk.oro.iefas.ws.bank.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.bank.dto.CurrencyBasicInfoDTO;
import hk.oro.iefas.domain.bank.dto.CurrencyDTO;
import hk.oro.iefas.domain.bank.dto.CurrencyResultDTO;
import hk.oro.iefas.domain.bank.dto.CurrencySearchDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 981 $ $Date: 2018-01-31 10:26:00 +0800 (週三, 31 一月 2018) $
 * @author $Author: vicki.huang $
 */
public interface CurrencyService {

    boolean existsByCurcyCodeAndCurcyIdNot(String curcyCode, Integer curcyId);

    Page<CurrencyResultDTO> findByCriteria(SearchDTO<CurrencySearchDTO> searchDTO);

    CurrencyDTO findOne(Integer curcyId);

    Integer save(CurrencyDTO currencyDTO);

    List<CurrencyBasicInfoDTO> findAll();
}
