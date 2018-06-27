package hk.oro.iefas.ws.casemgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.AddressDTO;
import hk.oro.iefas.domain.casemgt.dto.CommonCreditorBasicDTO;
import hk.oro.iefas.domain.casemgt.dto.CommonCreditorDTO;
import hk.oro.iefas.domain.casemgt.dto.CommonCreditorSectionDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchCommonCreditorCriteriaDTO;
import hk.oro.iefas.domain.casemgt.entity.Address;
import hk.oro.iefas.domain.casemgt.entity.CommonCreditor;
import hk.oro.iefas.domain.casemgt.entity.CommonCreditorSection;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.casemgt.repository.AddressRepository;
import hk.oro.iefas.ws.casemgt.repository.CommonCreditorRepository;
import hk.oro.iefas.ws.casemgt.repository.CommonCreditorSectionRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.CommonCreditorBasicDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.assembler.CommonCreditorDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.predicate.CommonCreditorPredicate;
import hk.oro.iefas.ws.casemgt.service.DividendCommonCreditorService;
import hk.oro.iefas.ws.core.constant.SysSequenceEnum;
import hk.oro.iefas.ws.system.service.SysSequenceService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3264 $ $Date: 2018-06-25 10:26:40 +0800 (週一, 25 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Service
public class DividendCommonCreditorServiceImpl implements DividendCommonCreditorService {
    @Autowired
    private SysSequenceService sysSequenceService;

    @Autowired
    private CommonCreditorRepository commonCreditorRepository;

    @Autowired
    private CommonCreditorSectionRepository commonCreditorSectionRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CommonCreditorDTOAssembler commonCreditorDTOAssembler;

    @Autowired
    private CommonCreditorBasicDTOAssembler commonCreditorBasicDTOAssembler;

    @Override
    @Transactional(readOnly = true)
    public Page<CommonCreditorBasicDTO>
        searchCommonCreditorList(SearchDTO<SearchCommonCreditorCriteriaDTO> criteriaDTO) {
        log.info("searchCommonCreditorList() start - " + criteriaDTO);
        Page<CommonCreditorBasicDTO> results = null;
        if (criteriaDTO != null) {
            Pageable pageable = null;
            if (criteriaDTO.getPage() != null) {
                pageable = criteriaDTO.getPage().toPageable();
            }

            if (criteriaDTO.getCriteria() != null) {
                Page<CommonCreditor> page = commonCreditorRepository
                    .findAll(CommonCreditorPredicate.findByCriteria(criteriaDTO.getCriteria()), pageable);
                if (page != null) {
                    results = commonCreditorBasicDTOAssembler.toResultDTO(page);
                }
            }

        }
        log.info("searchCommonCreditorList() end - " + results);
        return results;
    }

    @Override
    @Transactional(readOnly = true)
    public CommonCreditorDTO searchCommonCreditor(Integer commonCreditorId) {
        log.info("searchCommonCreditor() start - commonCreditorId: " + commonCreditorId);
        CommonCreditor commonCreditor = commonCreditorRepository.findOne(commonCreditorId);
        CommonCreditorDTO dto = commonCreditorDTOAssembler.toDTO(commonCreditor);
        log.info("searchCommonCreditor() end - " + dto);
        return dto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveCommonCreditor(CommonCreditorDTO commonCreditorDTO) {
        log.info("saveCommonCreditor() start - commonCreditorDTO: " + commonCreditorDTO);
        CommonCreditor commonCreditor;
        // update
        if (commonCreditorDTO.getCommonCreditorId() != null && commonCreditorDTO.getCommonCreditorId().intValue() > 0) {
            if (commonCreditorDTO.getAddress() != null && commonCreditorDTO.getAddress().getAddressId() != null
                && commonCreditorDTO.getAddress().getAddressId().intValue() > 0) {
                Address address = DataUtils.copyProperties(commonCreditorDTO.getAddress(), Address.class);
                addressRepository.save(address);
            } else {
                log.error("Common creditor data invalid: no reference adddress!");
            }
            commonCreditor = DataUtils.copyProperties(commonCreditorDTO, CommonCreditor.class);
            // create
        } else {
            if (commonCreditorDTO.getAddress() == null) {
                AddressDTO addressDTO = new AddressDTO();
                addressDTO.setStatus(CoreConstant.STATUS_ACTIVE);
                commonCreditorDTO.setAddress(addressDTO);
            }
            Address address = DataUtils.copyProperties(commonCreditorDTO.getAddress(), Address.class);
            addressRepository.save(address);
            commonCreditor = DataUtils.copyProperties(commonCreditorDTO, CommonCreditor.class);
            commonCreditor.setAddress(address);

            String commonCredSeqNo
                = sysSequenceService.generateCommonCreditorSeqNo(SysSequenceEnum.COMMON_CREDITOR_SEQ_NO.name());
            commonCreditor.setCommonCreditorSeqNo(commonCredSeqNo);
        }
        commonCreditor = commonCreditorRepository.save(commonCreditor);
        List<CommonCreditorSectionDTO> sections = commonCreditorDTO.getCommonCreditorSections();
        if (CommonUtils.isNotEmpty(sections)) {
            for (CommonCreditorSectionDTO commonCreditorSectionDTO : sections) {
                Address sectionAddress = null;
                if (commonCreditorSectionDTO.getAddress() != null) {
                    commonCreditorSectionDTO.getAddress().setStatus(CoreConstant.STATUS_ACTIVE);
                    sectionAddress = DataUtils.copyProperties(commonCreditorSectionDTO.getAddress(), Address.class);
                    addressRepository.save(sectionAddress);
                }

                CommonCreditorSection section
                    = DataUtils.copyProperties(commonCreditorSectionDTO, CommonCreditorSection.class);
                section.setCommonCreditorId(commonCreditor.getCommonCreditorId());
                section.setAddress(sectionAddress);
                if (CoreConstant.STATUS_INACTIVE.equals(commonCreditor.getStatus())) {
                    section.setStatus(CoreConstant.STATUS_INACTIVE);
                }
                commonCreditorSectionRepository.save(section);
            }
        }
        log.info("saveCommonCreditor() end - " + commonCreditor.getCommonCreditorId());
        return commonCreditor.getCommonCreditorId();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCommonCreditorName(String commonCreditorName, Integer commonCreditorId) {
        log.info("existsByCommonCreditorName() start - commonCreditorName: " + commonCreditorName);
        boolean exists = commonCreditorRepository.existsByCommonCreditorNameAndCommonCreditorIdNot(commonCreditorName,
            commonCreditorId);
        log.info("existsByCommonCreditorName() end - " + exists);
        return exists;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommonCreditorDTO> searchAllActCommonCreditors() {
        log.info("searchAllActCommonCreditors() start ");
        List<CommonCreditor> commonCreditors
            = commonCreditorRepository.findByStatusIgnoreCaseOrderByCommonCreditorName(CoreConstant.STATUS_ACTIVE);
        List<CommonCreditorDTO> list = commonCreditorDTOAssembler.toDTOList(commonCreditors);
        log.info("searchAllActCommonCreditors() end - list: " + list);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommonCreditorDTO> searchCommonCreditorByName(String commonCreditorName) {
        log.info("searchCommonCreditorByName() start - commonCreditorName: " + commonCreditorName);
        Iterable<CommonCreditor> commonCreditor
            = commonCreditorRepository.findAll(CommonCreditorPredicate.findByCommonCreditorName(commonCreditorName));
        List<CommonCreditorDTO> dto = commonCreditorDTOAssembler.toDTOList(commonCreditor);
        log.info("searchCommonCreditorByName() end - " + dto);
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommonCreditorDTO> searchCommonCreditorBySeqNo(String commonCreditorSeqNo) {
        log.info("searchCommonCreditorBySeqNo() start - commonCreditorSeqNo: " + commonCreditorSeqNo);
        Iterable<CommonCreditor> commonCreditor
            = commonCreditorRepository.findAll(CommonCreditorPredicate.findByCommonCreditorSeqNo(commonCreditorSeqNo));
        List<CommonCreditorDTO> dto = commonCreditorDTOAssembler.toDTOList(commonCreditor);
        log.info("searchCommonCreditorBySeqNo() end - " + dto);
        return dto;
    }
}
