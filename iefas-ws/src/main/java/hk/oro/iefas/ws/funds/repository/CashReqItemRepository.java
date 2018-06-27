package hk.oro.iefas.ws.funds.repository;

import hk.oro.iefas.domain.funds.entity.CashReqItem;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface CashReqItemRepository extends BaseRepository<CashReqItem, Integer> {
    public CashReqItem findByCashRqmtItemTypeName(String cashRqmtItemTypeName);
}