/**
 * 
 */
package hk.oro.iefas.ws.organization.repository;

import hk.oro.iefas.domain.organization.entity.Rank;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2080 $ $Date: 2018-04-17 10:02:47 +0800 (週二, 17 四月 2018) $
 * @author $Author: dante.fang $
 */
public interface RankRepository extends BaseRepository<Rank, Integer> {

    public Rank findByRankName(String rankName);

}
