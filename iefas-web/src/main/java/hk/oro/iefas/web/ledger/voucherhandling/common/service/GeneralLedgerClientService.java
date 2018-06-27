package hk.oro.iefas.web.ledger.voucherhandling.common.service;

import hk.oro.iefas.domain.shroff.vo.GeneralLedgerVO;

/**
 * @version $Revision: 3049 $ $Date: 2018-06-11 21:15:30 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
public interface GeneralLedgerClientService {
    
    GeneralLedgerVO findGeneralLedger(Integer generalLedgerId);

    Integer saveGeneralLedger(GeneralLedgerVO generalLedger);

    GeneralLedgerVO findGeneralLedgerByControlAcId(Integer controlAccountId);
    
}
