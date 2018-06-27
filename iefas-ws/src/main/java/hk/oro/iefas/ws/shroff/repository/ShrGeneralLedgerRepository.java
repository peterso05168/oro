/**
 * 
 */
package hk.oro.iefas.ws.shroff.repository;

import java.util.List;

import hk.oro.iefas.domain.shroff.entity.ShrGeneralLedger;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2776 $ $Date: 2018-05-31 17:21:14 +0800 (Thu, 31 May 2018) $
 * @author $Author: george.wu $
 */
public interface ShrGeneralLedgerRepository extends BaseRepository<ShrGeneralLedger, Integer> {
    List<ShrGeneralLedger> findByAnalysisCodeAndStatus(String analysisCode, String status);
}
