/**
 * 
 */
package hk.oro.iefas.ws.shroff.service;

import java.math.BigDecimal;

/**
 * @version $Revision: 2968 $ $Date: 2018-06-06 22:25:31 +0800 (週三, 06 六月 2018) $
 * @author $Author: marvel.ma $
 */
public interface ShrGeneralLedgerService {

    Integer saveGeneralLedger(String analysisCode, Integer voucherId, BigDecimal credit, BigDecimal debit,
        BigDecimal balance, Integer caseAcId, Integer controlAcId, String particulars);
}
