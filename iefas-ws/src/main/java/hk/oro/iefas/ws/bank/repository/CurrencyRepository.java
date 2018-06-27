package hk.oro.iefas.ws.bank.repository;

import hk.oro.iefas.domain.bank.entity.Currency;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
public interface CurrencyRepository extends BaseRepository<Currency, Integer> {

    boolean existsByCurcyCodeIgnoreCaseAndCurcyIdNot(String curcyCode, Integer curcyId);

}
