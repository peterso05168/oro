package hk.oro.iefas.ws.casemgt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.OutsiderDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchOutsiderDetailCriteriaDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchOutsiderDetailResultDTO;
import hk.oro.iefas.domain.casemgt.entity.Address;
import hk.oro.iefas.domain.casemgt.entity.Outsider;
import hk.oro.iefas.domain.casemgt.entity.OutsiderType;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.casemgt.repository.AddressRepository;
import hk.oro.iefas.ws.casemgt.repository.OutsiderRepository;
import hk.oro.iefas.ws.casemgt.repository.OutsiderTypeRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.OutsiderDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.assembler.SearchOutsiderDetailResultDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.predicate.OutsiderPredicate;
import hk.oro.iefas.ws.casemgt.service.OutsiderService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class OutsiderServiceImpl implements OutsiderService {

    @Autowired
    private OutsiderRepository outsiderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OutsiderTypeRepository outsiderTypeRepository;

    @Autowired
    private OutsiderDTOAssembler outsiderDTOAssembler;

    @Autowired
    private SearchOutsiderDetailResultDTOAssembler searchOutsiderDetailResultDTOAssembler;

    @Override
    @Transactional(readOnly = true)
    public Page<SearchOutsiderDetailResultDTO> findByCriteria(SearchDTO<SearchOutsiderDetailCriteriaDTO> criteria) {
        log.info("findByCriteria() start + " + criteria);
        Page<Outsider> page = null;
        Page<SearchOutsiderDetailResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = outsiderRepository.findAll(OutsiderPredicate.findByCriteria(criteria.getCriteria()), pageable);
                result = searchOutsiderDetailResultDTOAssembler.toResultDTO(page);
            }
        }
        log.info("findByCriteria() end result " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public OutsiderDTO getOutsiderDetail(Integer outsiderId) {
        log.info("getOutsiderDetail() start outsiderId = " + outsiderId);
        OutsiderDTO outsiderDTO = null;
        Outsider outsider;
        if (outsiderId != null) {
            outsider = outsiderRepository.findOne(outsiderId);
            if (outsider != null) {
                outsiderDTO = outsiderDTOAssembler.toDTO(outsider);
            }
        }
        log.info("getOutsiderDetail() end result:" + outsiderDTO);
        return outsiderDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveOutsider(OutsiderDTO outsiderDTO) {
        log.info("saveOutsider() start - outsiderDTO = " + outsiderDTO);
        Outsider outsider = DataUtils.copyProperties(outsiderDTO, Outsider.class);
        OutsiderType outsiderType = outsiderTypeRepository.findOne(outsider.getOutsiderType().getOutsiderTypeId());
        outsider.setOutsiderType(outsiderType);
        Address address = addressRepository.save(outsider.getAddress());
        outsider.setAddress(address);
        Integer outsiderId = outsiderRepository.save(outsider).getOutsiderId();
        log.info("saveOutsider() end outsiderId = " + outsiderId);
        return outsiderId;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByOutsiderName(SearchOutsiderDetailCriteriaDTO criteriaDTO) {
        log.info("existsByOutsiderName() start" + criteriaDTO);
        Boolean result = outsiderRepository
            .existsByOutsiderNameIgnoreCaseAndOutsiderIdNot(criteriaDTO.getOutsiderName(), criteriaDTO.getOutsiderId());
        log.info("existsByOutsiderName end result = " + result);
        return result;
    }

}
