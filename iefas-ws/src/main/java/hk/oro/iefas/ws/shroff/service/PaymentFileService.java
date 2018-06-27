package hk.oro.iefas.ws.shroff.service;

import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentFileResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchPaymentFileDTO;
import org.springframework.data.domain.Page;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface PaymentFileService {
    Page<PaymentFileResultDTO> searchPaymentFile(SearchDTO<SearchPaymentFileDTO> criteria);
}
