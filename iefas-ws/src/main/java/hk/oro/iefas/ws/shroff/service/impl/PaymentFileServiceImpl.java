package hk.oro.iefas.ws.shroff.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentFileResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchPaymentFileDTO;
import hk.oro.iefas.domain.shroff.entity.ShrPaymentFile;
import hk.oro.iefas.ws.shroff.repository.PaymentFileRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.PaymentFileResultDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.predicate.PaymentFilePredicate;
import hk.oro.iefas.ws.shroff.service.PaymentFileService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Service
@Slf4j
public class PaymentFileServiceImpl implements PaymentFileService {
    @Autowired
    private PaymentFileRepository paymentFileRepository;
    @Autowired
    private PaymentFileResultDTOAssembler paymentFileResultDTOAssembler;

    @Override
    public Page<PaymentFileResultDTO> searchPaymentFile(SearchDTO<SearchPaymentFileDTO> criteria) {
        log.info("searchPaymentFile start - criteria: " + criteria);
        Page<ShrPaymentFile> page = null;
        Page<PaymentFileResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = paymentFileRepository.findAll(PaymentFilePredicate.searchPaymentFile(criteria.getCriteria()),
                    pageable);
                result = paymentFileResultDTOAssembler.toResultDTO(page);
            }
        }
        log.info("searchRosterList() end");
        return result;
    }
}
