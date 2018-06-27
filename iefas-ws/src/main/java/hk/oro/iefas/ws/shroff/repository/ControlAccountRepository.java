package hk.oro.iefas.ws.shroff.repository;

import hk.oro.iefas.domain.shroff.entity.ShrCtlAc;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
public interface ControlAccountRepository extends BaseRepository<ShrCtlAc, Integer> {
    Boolean existsByCtlAcNameAndCtlAcIdNot(String ctlAcName, Integer ctlAcId);

    Boolean existsByCtlAcCodeAndCtlAcIdNot(String ctlAcCode, Integer ctlAcId);
}
