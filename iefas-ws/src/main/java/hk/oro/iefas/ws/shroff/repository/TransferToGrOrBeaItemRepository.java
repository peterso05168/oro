package hk.oro.iefas.ws.shroff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hk.oro.iefas.domain.shroff.entity.ShrTxfGrBeaItem;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 3189 $ $Date: 2018-06-19 13:34:32 +0800 (週二, 19 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface TransferToGrOrBeaItemRepository extends BaseRepository<ShrTxfGrBeaItem, Integer> {

    @Query("select t from ShrTxfGrBeaItem t where t.transferId = ?1")
    public List<ShrTxfGrBeaItem> findTransferItemByTransfer(Integer transfer);

}
