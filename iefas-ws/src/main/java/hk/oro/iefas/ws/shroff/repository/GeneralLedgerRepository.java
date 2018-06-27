package hk.oro.iefas.ws.shroff.repository;

import java.math.BigDecimal;

import hk.oro.iefas.domain.shroff.entity.ShrGeneralLedger;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * 
 * @version $Revision$ $Date$
 * @author $Author$
 */
public interface GeneralLedgerRepository extends BaseRepository<ShrGeneralLedger, Integer> {

    ShrGeneralLedger findBycontrolAcId(Integer controlAccountId);

}
