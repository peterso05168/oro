package hk.oro.iefas.ws.shroff.service;

import java.math.BigDecimal;

import hk.oro.iefas.domain.shroff.dto.GeneralLedgerDTO;

/**
 * @version $Revision: 3065 $ $Date: 2018-06-11 21:27:01 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
public interface GeneralLedgerService {

    Integer saveGeneralLedger(GeneralLedgerDTO generalLedger);

//    GeneralLedgerDTO findGeneralLedger(BigDecimal generalLedgerId);

    GeneralLedgerDTO findGeneralLedgerByControlAcId(Integer controlAccountId);

}
