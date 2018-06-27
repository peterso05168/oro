/**
 * 
 */
package hk.oro.iefas.ws.system.repository;

import hk.oro.iefas.domain.system.entity.DashboardType;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface DashboardTypeRepository extends BaseRepository<DashboardType, Integer> {

    DashboardType findByPrivilegePrivilegeId(Integer privilegeId);

}
