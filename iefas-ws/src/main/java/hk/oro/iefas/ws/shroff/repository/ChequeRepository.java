package hk.oro.iefas.ws.shroff.repository;

import hk.oro.iefas.domain.shroff.entity.ShrCheque;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */

public interface ChequeRepository extends BaseRepository<ShrCheque, Integer> {

    boolean existsByChequeNoAndChequeIdNot(String chequeNo, Integer chequeId);

    ShrCheque getByChequeNo(String chequeNo);
}
