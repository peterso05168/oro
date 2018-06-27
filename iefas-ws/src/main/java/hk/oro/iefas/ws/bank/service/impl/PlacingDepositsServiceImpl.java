package hk.oro.iefas.ws.bank.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.bank.dto.CalculationOfFundsAvailableDTO;
import hk.oro.iefas.domain.bank.dto.CalculationOfFundsAvailableItemDTO;
import hk.oro.iefas.domain.bank.dto.FundsAvailableItemTypeDTO;
import hk.oro.iefas.domain.bank.dto.SearchPlacingDepositsCriteriaDTO;
import hk.oro.iefas.domain.funds.dto.InvestmentTypeDTO;
import hk.oro.iefas.domain.funds.entity.AvaItemType;
import hk.oro.iefas.domain.funds.entity.CalOfAvailable;
import hk.oro.iefas.domain.funds.entity.CalOfAvailableItem;
import hk.oro.iefas.domain.funds.entity.InvType;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.bank.service.PlacingDepositsService;
import hk.oro.iefas.ws.funds.repository.AvaItemTypeRepository;
import hk.oro.iefas.ws.funds.repository.CalOfAvailableItemRepository;
import hk.oro.iefas.ws.funds.repository.CalOfAvailableRepository;
import hk.oro.iefas.ws.funds.repository.InvTypeRepository;
import hk.oro.iefas.ws.funds.repository.assembler.CalculationOfFundsAvailableDTOAssembler;
import hk.oro.iefas.ws.funds.repository.predicate.CalOfAvailablePredicate;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class PlacingDepositsServiceImpl implements PlacingDepositsService {
    @Autowired
    private CalOfAvailableRepository calOfAvailableRepository;

    @Autowired
    private CalOfAvailableItemRepository calOfAvailableItemRepository;

    @Autowired
    private AvaItemTypeRepository avaItemTypeRepository;

    @Autowired
    private InvTypeRepository invTypeRepository;

    @Autowired
    private CalculationOfFundsAvailableDTOAssembler calculationOfFundsAvailableDTOAssembler;

    @Override
    @Transactional(readOnly = true)
    public Page<CalculationOfFundsAvailableDTO>
        searchPlacingDepositsList(SearchDTO<SearchPlacingDepositsCriteriaDTO> criteriaDTO) {
        log.info("findByCriteria() start - " + criteriaDTO);
        Pageable pageable = criteriaDTO.getPage().toPageable();
        Sort sort = new Sort(Direction.DESC, "investmentDate");
        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<CalOfAvailable> page = calOfAvailableRepository
            .findAll(CalOfAvailablePredicate.findByCriteria(criteriaDTO.getCriteria()), pageRequest);
        Page<CalculationOfFundsAvailableDTO> results = calculationOfFundsAvailableDTOAssembler.toResultDTO(page);

        log.info("findByCriteria() end - " + results);
        return results;
    }

    @Override
    @Transactional(readOnly = true)
    public CalculationOfFundsAvailableDTO findOne(Integer calculationOfFundsAvailableId) {
        log.info("findOne() start - bankInfoId: " + calculationOfFundsAvailableId);
        CalOfAvailable calOfAvailable = calOfAvailableRepository.findOne(calculationOfFundsAvailableId);
        CalculationOfFundsAvailableDTO dto = calculationOfFundsAvailableDTOAssembler.toDTO(calOfAvailable);
        log.info("findOne() start - " + dto);
        return dto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(CalculationOfFundsAvailableDTO calculationOfFundsAvailableDTO) {
        log.info("save() start  - calculationOfFundsAvailableDTO : " + calculationOfFundsAvailableDTO);
        CalOfAvailable calOfAvailable = DataUtils.copyProperties(calculationOfFundsAvailableDTO, CalOfAvailable.class);
        calOfAvailable = calOfAvailableRepository.save(calOfAvailable);

        List<CalculationOfFundsAvailableItemDTO> calculationOfFundsAvailableItems
            = calculationOfFundsAvailableDTO.getCalculationOfFundsAvailableItems();
        if (CommonUtils.isNotEmpty(calculationOfFundsAvailableItems)) {
            for (CalculationOfFundsAvailableItemDTO calculationOfFundsAvailableItemDTO : calculationOfFundsAvailableItems) {
                CalOfAvailableItem item;
                // create
                if (calculationOfFundsAvailableItemDTO.getCalculationOfFundsAvailableItemId() == null
                    || calculationOfFundsAvailableItemDTO.getCalculationOfFundsAvailableItemId().intValue() <= 0) {
                    item = new CalOfAvailableItem();
                    item.setCalculationOfFundsAvailableId(calOfAvailable.getCalculationOfFundsAvailableId());
                    FundsAvailableItemTypeDTO fundsAvailableItemType
                        = calculationOfFundsAvailableItemDTO.getFundsAvailableItemType();
                    if (fundsAvailableItemType != null) {
                        AvaItemType avaItemType
                            = avaItemTypeRepository.findOne(fundsAvailableItemType.getFundsAvailableItemTypeId());
                        item.setFundsAvailableItemType(avaItemType);
                    }
                    InvestmentTypeDTO investmentType = calculationOfFundsAvailableItemDTO.getInvestmentType();
                    if (investmentType != null) {
                        InvType invType = invTypeRepository.findOne(investmentType.getInvestmentTypeId());
                        item.setInvestmentType(invType);
                    }
                    item.setStatus(CoreConstant.STATUS_ACTIVE);
                } else {
                    item = calOfAvailableItemRepository
                        .findOne(calculationOfFundsAvailableItemDTO.getCalculationOfFundsAvailableItemId());
                }
                item.setAvailableAmount(calculationOfFundsAvailableItemDTO.getAvailableAmount());
                calOfAvailableItemRepository.save(item);
            }
        }
        log.info("save() end entityId:" + calOfAvailable.getCalculationOfFundsAvailableId());
        return calOfAvailable.getCalculationOfFundsAvailableId();
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByInvestmentDate(Date invtDate) {
        log.info("existsByInvestmentDate() start - invtDate: " + invtDate);
        boolean exists = calOfAvailableRepository.existsByInvestmentDateAndStatus(invtDate, CoreConstant.STATUS_ACTIVE);
        log.info("existsByInvestmentDate() end - exists: " + exists);
        return exists;
    }

}
