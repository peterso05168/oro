package hk.oro.iefas.ws.shroff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hk.oro.iefas.domain.shroff.entity.ShrVcrItem;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 3189 $ $Date: 2018-06-19 13:34:32 +0800 (週二, 19 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface ShrVcrItemRepository extends BaseRepository<ShrVcrItem, Integer> {

    @Query("select svi from ShrVcrItem svi where svi.voucherId = :voucherId and svi.status != 'DEL' order by voucherItemNo asc")
    List<ShrVcrItem> findAvaiableVoucherItem(@Param("voucherId") Integer voucherId);

    List<ShrVcrItem> findByVoucherIdOrderByVoucherItemNoAsc(Integer voucherId);

    List<ShrVcrItem> findByVoucherId(Integer voucherId);

}
