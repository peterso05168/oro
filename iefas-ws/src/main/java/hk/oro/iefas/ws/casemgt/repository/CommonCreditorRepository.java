package hk.oro.iefas.ws.casemgt.repository;

import java.util.List;

import hk.oro.iefas.domain.casemgt.entity.CommonCreditor;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface CommonCreditorRepository extends BaseRepository<CommonCreditor, Integer> {
    boolean existsByCommonCreditorNameAndCommonCreditorIdNot(String commonCreditorName, Integer commonCreditorId);

    List<CommonCreditor> findByStatusIgnoreCaseOrderByCommonCreditorName(String status);
}
