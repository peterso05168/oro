package hk.oro.iefas.ws.dividend.repository;

import hk.oro.iefas.domain.adjudication.entity.AdjType;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface AdjTypeRepository extends BaseRepository<AdjType, Integer> {

    AdjType findByAdjTypeDesc(String adjTypeDesc);

}
