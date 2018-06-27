package hk.oro.iefas.ws.shroff.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.ControlAccountDTO;
import hk.oro.iefas.domain.shroff.dto.ControlAccountResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchControlAccountDTO;
import hk.oro.iefas.domain.shroff.entity.ShrCtlAc;
import hk.oro.iefas.ws.shroff.repository.ControlAccountRepository;
import hk.oro.iefas.ws.shroff.repository.ControlAccountTypeRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.ControlAccountDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.ControlAccountResultDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.predicate.ControlAccountPredicate;
import hk.oro.iefas.ws.shroff.service.ControlAccountService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Service
public class ControlAccountServiceImpl implements ControlAccountService {

    @Autowired
    private ControlAccountRepository controlAccountRepository;

    @Autowired
    private ControlAccountTypeRepository controlAccountTypeRepository;

    @Autowired
    private ControlAccountResultDTOAssembler controlAccountResultDTOAssembler;

    @Autowired
    private ControlAccountDTOAssembler controlAccountDTOAssembler;

    @Transactional(readOnly = true)
    @Override
    public List<ControlAccountDTO> findAllControlAccounts() {
        log.info("findAllControlAccounts() start - ");
        Iterable<ShrCtlAc> controlAccountEntities = controlAccountRepository.findAll(ControlAccountPredicate.findAll(),
            ControlAccountPredicate.sortByControllerAccountName());
        List<ControlAccountDTO> controlAccountDTOs = this.controlAccountDTOAssembler.toDTOList(controlAccountEntities);
        log.info("findAllControlAccounts() end - ");
        return controlAccountDTOs;
    }

    @Override
    public Boolean existsByControlAccountName(SearchControlAccountDTO criteria) {
        log.info("existsByControlAccountName start - criteria: " + criteria);
        Boolean result = null;
        if (criteria != null) {
            result = controlAccountRepository.existsByCtlAcNameAndCtlAcIdNot(criteria.getCtlAcName(),
                criteria.getCtlAcId());
        }
        log.info("existsByControlAccountName end - " + result);
        return result;
    }

    @Override
    public Boolean existsByControlAccountCode(SearchControlAccountDTO criteria) {
        log.info("existsByControlAccountCode start - criteria: " + criteria);
        Boolean result = null;
        if (criteria != null) {
            result = controlAccountRepository.existsByCtlAcCodeAndCtlAcIdNot(criteria.getCtlAcCode(),
                criteria.getCtlAcId());
        }
        log.info("existsByControlAccountCode end - " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ControlAccountResultDTO> searchControlAccountResultList(SearchDTO<SearchControlAccountDTO> criteria) {
        log.info("searchControlAccountResultList() start - criteria : " + criteria);
        Page<ShrCtlAc> page = null;
        Page<ControlAccountResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = controlAccountRepository.findAll(ControlAccountPredicate.findByCriteria(criteria.getCriteria()),
                    pageable);
                result = controlAccountResultDTOAssembler.toResultDTO(page);
            }
        }
        log.info("searchControlAccountResultList end - " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ControlAccountDTO getControlAccountDetail(Integer ctlAcId) {
        log.info("getControlAccountDetail() start - ctlAcId : " + ctlAcId);
        ControlAccountDTO controlAccountDTO = null;
        ShrCtlAc shrCtlAc;
        if (ctlAcId != null) {
            shrCtlAc = controlAccountRepository.findOne(ctlAcId);
            controlAccountDTO = controlAccountDTOAssembler.toDTO(shrCtlAc);
        }
        log.info("getControlAccountDetail end - " + controlAccountDTO);
        return controlAccountDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveControlAccount(ControlAccountDTO controlAccountDTO) {
        log.info("saveControlAccount() start - controlAccountDTO : " + controlAccountDTO);
        Integer result = null;
        if (controlAccountDTO != null) {
            ShrCtlAc shrCtlAc = DataUtils.copyProperties(controlAccountDTO, ShrCtlAc.class);
            if (shrCtlAc.getShrCtlAcType() != null) {
                shrCtlAc
                    .setShrCtlAcType(controlAccountTypeRepository.findOne(shrCtlAc.getShrCtlAcType().getCtlAcTypeId()));
            }
            controlAccountRepository.save(shrCtlAc);
            result = shrCtlAc.getCtlAcId();
        }
        log.info("saveControlAccount end - " + result);
        return result;
    }

}
