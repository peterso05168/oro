package hk.oro.iefas.ws.casemgt.repository;

import hk.oro.iefas.domain.casemgt.entity.Outsider;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2187 $ $Date: 2018-04-24 11:23:12 +0800 (週二, 24 四月 2018) $
 * @author $Author: Garrett.han $
 */
public interface OutsiderRepository extends BaseRepository<Outsider, Integer> {
    Boolean existsByOutsiderNameIgnoreCaseAndOutsiderIdNot(String outsiderName, Integer outsiderId);
}
