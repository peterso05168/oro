package hk.oro.iefas.ws.funds.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.funds.dto.FundsParameterDTO;
import hk.oro.iefas.domain.funds.entity.FundsParameter;
import hk.oro.iefas.ws.funds.repository.FundsParameterRepository;
import hk.oro.iefas.ws.funds.repository.assembler.FundsParameterDTOAssembler;
import hk.oro.iefas.ws.funds.repository.predicate.FundsParameterPredicate;
import hk.oro.iefas.ws.funds.service.FundsParameterService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class FundsParameterServiceImpl implements FundsParameterService {

    @Autowired
    private FundsParameterRepository fundsParameterRepository;

    @Transactional(readOnly = true)
    @Override
    public List<FundsParameterDTO> loadParameter() {
        log.info("loadParameter() start");
        Iterable<FundsParameter> iterableFundsParameter
            = fundsParameterRepository.findAll(FundsParameterPredicate.findAll());
        List<FundsParameter> fundsParameterList = IterableUtils.toList(iterableFundsParameter);
        List<FundsParameterDTO> result = FundsParameterDTOAssembler.toDtoList(fundsParameterList);

        log.info("loadParameter() end - " + result);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateParameter(List<FundsParameterDTO> fundsParameterList) {
        log.info("updateParameter() start - " + fundsParameterList);
        List<FundsParameter> list = FundsParameterDTOAssembler.toEntity(fundsParameterList);
        fundsParameterRepository.save(list);
        log.info("updateParameter() end");
    }
}
