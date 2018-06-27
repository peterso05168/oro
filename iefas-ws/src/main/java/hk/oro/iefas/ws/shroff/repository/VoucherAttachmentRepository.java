package hk.oro.iefas.ws.shroff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hk.oro.iefas.domain.shroff.entity.ShrVcrAttachment;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2941 $ $Date: 2018-06-06 14:56:42 +0800 (週三, 06 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface VoucherAttachmentRepository extends BaseRepository<ShrVcrAttachment, Integer> {

    @Query("select a from ShrVcrAttachment a where a.voucher.voucherId = ?1 and a.status = 'ACT'")
    List<ShrVcrAttachment> findVoucherAttachmentByVoucher(Integer voucherId);
}
