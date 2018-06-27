package hk.oro.iefas.ws.adjudication.repository;

import java.util.List;

import hk.oro.iefas.domain.adjudication.entity.PreAdjResultGro;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface PreAdjResultGroRepository extends BaseRepository<PreAdjResultGro, Integer> {
    List<PreAdjResultGro> findByPreAdjResultItemIdAndStatus(Integer preAdjResultItemId, String status);

}
