package hk.oro.iefas.ws.dividend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.dividend.dto.DividendParameterDTO;
import hk.oro.iefas.domain.dividend.entity.DivParameter;
import hk.oro.iefas.ws.dividend.repository.DivParameterRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.DividendParameterDTOAssembler;
import hk.oro.iefas.ws.dividend.service.DividendParameterService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class DividendParameterServiceImpl implements DividendParameterService {

    @Autowired
    private DivParameterRepository divParameterRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DividendParameterDTO> searchDividendParameter() {
        log.info("searchDividendParameter() start ");
        List<DivParameter> divParameters = divParameterRepository.findByStatusIgnoreCase(CoreConstant.STATUS_ACTIVE);
        List<DividendParameterDTO> parameterDTOs = DividendParameterDTOAssembler.toDtoList(divParameters);
        log.info("searchDividendParameter() end - " + parameterDTOs);
        return parameterDTOs;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveDividendParameter(List<DividendParameterDTO> parameterDTOs) {
        List<DivParameter> savedEntity
            = divParameterRepository.save(DividendParameterDTOAssembler.toEntity(parameterDTOs));
        return parameterDTOs.size() == savedEntity.size();
    }

}
