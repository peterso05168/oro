package hk.oro.iefas.ws.shroff.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.oro.iefas.domain.shroff.dto.PaymentFileTypeDTO;
import hk.oro.iefas.domain.shroff.entity.PaymentFileType;
import hk.oro.iefas.ws.shroff.repository.PaymentFileTypeRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.PaymentFileTypeDTOAssembler;
import hk.oro.iefas.ws.shroff.service.PaymentFileTypeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Service
@Slf4j
public class PaymentFileTypeServiceImpl implements PaymentFileTypeService {

    @Autowired
    private PaymentFileTypeRepository paymentFileTypeRepository;

    @Autowired
    private PaymentFileTypeDTOAssembler paymentFileTypeDTOAssembler;

    @Override
    public List<PaymentFileTypeDTO> findAllPaymentFileType() {
        log.info("findAllPaymentFileType start");
        List<PaymentFileTypeDTO> result = null;
        List<PaymentFileType> paymentFileTypes = paymentFileTypeRepository.findAll();
        result = paymentFileTypeDTOAssembler.toDTOList(paymentFileTypes);
        log.info("findAllPaymentFileType end - " + result);
        return result;
    }
}
