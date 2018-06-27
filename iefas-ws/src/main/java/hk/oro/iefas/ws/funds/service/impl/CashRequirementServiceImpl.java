package hk.oro.iefas.ws.funds.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.funds.dto.CashRequirementResultDTO;
import hk.oro.iefas.domain.funds.dto.DailyCashRequirementGenerateDTO;
import hk.oro.iefas.domain.funds.dto.DailyCashRequirementResultDTO;
import hk.oro.iefas.domain.funds.dto.InvestmentTypeDTO;
import hk.oro.iefas.domain.funds.dto.SearchCashRequirementCriteriaDTO;
import hk.oro.iefas.domain.funds.dto.SearchDailyCashRequirementCriteriaDTO;
import hk.oro.iefas.domain.funds.entity.AvaItemType;
import hk.oro.iefas.domain.funds.entity.CalOfAvailable;
import hk.oro.iefas.domain.funds.entity.CalOfAvailableItem;
import hk.oro.iefas.domain.funds.entity.CashReq;
import hk.oro.iefas.domain.funds.entity.CashReqItem;
import hk.oro.iefas.domain.funds.entity.DaiCashReq;
import hk.oro.iefas.domain.funds.entity.DaiCashReqItem;
import hk.oro.iefas.domain.funds.entity.InvType;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.constant.FundsConstant;
import hk.oro.iefas.ws.core.constant.FundsConstant.CashRequirementNatureConstant;
import hk.oro.iefas.ws.funds.repository.AvaItemTypeRepository;
import hk.oro.iefas.ws.funds.repository.CalOfAvailableItemRepository;
import hk.oro.iefas.ws.funds.repository.CalOfAvailableRepository;
import hk.oro.iefas.ws.funds.repository.CashReqItemRepository;
import hk.oro.iefas.ws.funds.repository.CashReqRepository;
import hk.oro.iefas.ws.funds.repository.DaiCashReqItemRepository;
import hk.oro.iefas.ws.funds.repository.DaiCashReqRepository;
import hk.oro.iefas.ws.funds.repository.InvTypeRepository;
import hk.oro.iefas.ws.funds.repository.assembler.CashRequirementDTOAssembler;
import hk.oro.iefas.ws.funds.repository.predicate.CalOfAvailableItemPredicate;
import hk.oro.iefas.ws.funds.repository.predicate.CalOfAvailablePredicate;
import hk.oro.iefas.ws.funds.repository.predicate.CashReqItemPredicate;
import hk.oro.iefas.ws.funds.repository.predicate.CashReqPredicate;
import hk.oro.iefas.ws.funds.repository.predicate.DaiCashReqItemPredicate;
import hk.oro.iefas.ws.funds.repository.predicate.DaiCashReqPredicate;
import hk.oro.iefas.ws.funds.service.CashRequirementService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class CashRequirementServiceImpl implements CashRequirementService {

    @Autowired
    private CashReqRepository cashRequirementRepository;

    @Autowired
    private DaiCashReqRepository daiCashReqRepository;

    @Autowired
    private DaiCashReqItemRepository daiCashReqItemRepository;

    @Autowired
    private CashReqItemRepository cashReqItemRepository;

    @Autowired
    private InvTypeRepository invTypeRepository;

    @Autowired
    private CalOfAvailableItemRepository calOfAvailableItemRepository;

    @Autowired
    private CalOfAvailableRepository calOfAvailableRepository;

    @Autowired
    private AvaItemTypeRepository avaItemTypeRepository;

    @Autowired
    private CashRequirementDTOAssembler cashRequirementDTOAssembler;

    @Transactional(readOnly = true)
    @Override
    public Page<CashRequirementResultDTO> findByCriteria(SearchDTO<SearchCashRequirementCriteriaDTO> searchDTO) {
        log.info("findByCriteria start - and param :" + searchDTO);
        Page<CashRequirementResultDTO> results = null;
        if (searchDTO != null) {
            Pageable pageable = null;
            if (searchDTO.getPage() != null) {
                pageable = searchDTO.getPage().toPageable();
            }

            if (searchDTO.getCriteria() != null) {
                Page<CashReq> page = cashRequirementRepository
                    .findAll(CashReqPredicate.findByCriteria(searchDTO.getCriteria()), pageable);
                if (page != null) {
                    results = cashRequirementDTOAssembler.toResultDTO(page);
                }
            }

        }
        log.info("findByCriteria end - and return :" + results);
        return results;
    }

    @Transactional(readOnly = true)
    @Override
    public CashRequirementResultDTO searchCashRequirement(Integer cashReqId) {
        log.info("searchCashRequirement start - and param :" + cashReqId);
        CashRequirementResultDTO dto = null;
        if (cashReqId == null || cashReqId == 0) {
            dto = new CashRequirementResultDTO();
            dto.setCashRequirementResultId(0);
            dto.setStatus(CoreConstant.STATUS_ACTIVE);
            dto.setInvestmentType(new InvestmentTypeDTO());
        } else {
            CashReq cashRequirement = cashRequirementRepository.findOne(cashReqId);
            if (cashRequirement != null) {
                dto = cashRequirementDTOAssembler.toDTO(cashRequirement);
            }
        }

        log.info("searchCashRequirement end - and return :" + dto);
        return dto;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveCaseRequirement(CashRequirementResultDTO cashRequirementResultDTO) {
        log.info("saveCaseRequirement start - and param :" + cashRequirementResultDTO);
        if (cashRequirementResultDTO != null) {
            String status = cashRequirementResultDTO.getStatus();
            Integer cashRequirementResultId = cashRequirementResultDTO.getCashRequirementResultId();
            if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(status)) {
                Date rqmtPeriodFrom = cashRequirementResultDTO.getRqmtPeriodFrom();
                Date rqmtPeriodTo = cashRequirementResultDTO.getRqmtPeriodTo();

                if (rqmtPeriodFrom != null && rqmtPeriodTo != null) {
                    cashRequirementResultDTO.setRqmtPeriodFrom(DateUtils.getStartDate(rqmtPeriodFrom));
                    cashRequirementResultDTO.setRqmtPeriodTo(DateUtils.getEndDate(rqmtPeriodTo));
                    CashReq cashReq = this.cashRequirementDTOAssembler.toEntity(cashRequirementResultDTO);
                    if (cashReq != null) {
                        this.cashRequirementRepository.save(cashReq);

                        InvestmentTypeDTO investmentType = cashRequirementResultDTO.getInvestmentType();

                        DailyCashRequirementGenerateDTO generateDTO = new DailyCashRequirementGenerateDTO();
                        generateDTO.setInvestmentType(investmentType);
                        generateDTO.setAmount(cashRequirementResultDTO.getDailyRequirement());
                        generateDTO.setEffectiveFrom(rqmtPeriodFrom);
                        generateDTO.setEffectiveTo(rqmtPeriodTo);
                        generateDTO.setNature(FundsConstant.CashRequirementNatureConstant.TARGET.getNature());
                        generateDTO.setMode(FundsConstant.GEN_REQ_MODE_UPDATE);
                        this.updateDailyCashRequirement(generateDTO);

                        log.info("saveCaseRequirement end - and return :" + cashRequirementResultId);
                        return cashRequirementResultId;
                    }
                }
            } else if (CoreConstant.STATUS_INACTIVE.equalsIgnoreCase(status)) {
                boolean successFlag = this.saveInactiveCaseRequirement(cashRequirementResultDTO);
                if (successFlag) {
                    log.info("saveCaseRequirement end - and return :" + cashRequirementResultId);
                    return cashRequirementResultId;
                }
            }

        }

        log.info("saveCaseRequirement end - and return :" + null);
        return null;
    }

    @Override
    public boolean saveCaseRequirementValidate(CashRequirementResultDTO cashRequirementResultDTO) {
        log.info("saveCaseRequirementValidate start - and param :" + cashRequirementResultDTO);
        if (cashRequirementResultDTO != null) {
            Date periodFrom = cashRequirementResultDTO.getRqmtPeriodFrom();
            Date periodTo = cashRequirementResultDTO.getRqmtPeriodTo();
            if (periodFrom != null && periodTo != null && !periodFrom.after(periodTo)) {
                Predicate periodOverlap = CashReqPredicate.periodOverlap(cashRequirementResultDTO);
                if (periodOverlap != null) {
                    boolean exists = !cashRequirementRepository.exists(periodOverlap);
                    log.info("saveCaseRequirementValidate end - and return :" + exists);
                    return exists;
                }
            }

        }
        log.info("saveCaseRequirementValidate end - and return :" + false);
        return false;
    }

    /**
     * @param generateDTO
     */
    private void updateDailyCashRequirement(DailyCashRequirementGenerateDTO generateDTO) {
        log.info("updateDailyCashRequirement start - and param :" + generateDTO);
        if (generateDTO != null) {
            Date effectiveFrom = generateDTO.getEffectiveFrom();
            Date effectiveTo = generateDTO.getEffectiveTo();
            InvestmentTypeDTO investmentType = generateDTO.getInvestmentType();
            if (effectiveFrom != null && effectiveTo != null && investmentType != null) {
                Calendar calendar = Calendar.getInstance();
                BigDecimal amount = generateDTO.getAmount();
                int mode = generateDTO.getMode();
                Integer investmentTypeId = investmentType.getInvestmentTypeId();
                InvType invType = invTypeRepository.findOne(investmentTypeId);

                CashReqItem cashReqItemType = null;
                AvaItemType avaItemType = null;
                if (generateDTO.getNature() != null && generateDTO.getNature().intValue() > 0) {
                    log.info("Current nature is " + generateDTO.getNature());
                    CashRequirementNatureConstant natureConstant = FundsConstant.CashRequirementNatureConstant
                        .getCashRequirementNatureConstant(generateDTO.getNature());
                    if (natureConstant != null) {
                        log.info("NatureConstant is " + natureConstant.name());
                        cashReqItemType
                            = cashReqItemRepository.findOne(CashReqItemPredicate.findByNameAndInvestTypeCode(
                                natureConstant.getCashRequirementItemType(), invType.getInvestTypeCode()));
                        avaItemType
                            = avaItemTypeRepository.findByFundsAvailableItemName(natureConstant.getAvaItemType());

                        log.info(("".equals(natureConstant.getCashRequirementItemType()))
                            ? "No need to update Daily Cash Requirement Item."
                            : "Need to update Daily Cash Requirement Item with Cash Requirement Item Type = "
                                + natureConstant.getCashRequirementItemType() + ".");
                        log.info(("".equals(natureConstant.getAvaItemType()))
                            ? "No need to update Calculation of Funds Available Item."
                            : "Need to update Calculation of Funds Available Item with Funds Available Item Type = "
                                + natureConstant.getAvaItemType() + ".");
                    } else {
                        log.info(
                            "For others value, no need to create/update Daily Cash Requirement Item and Calculation of Funds Available Item");
                    }
                }

                Integer cashReqItemTypeId = null;
                if (cashReqItemType != null && cashReqItemType.getCashRqmtItemTypeId() != null
                    && cashReqItemType.getCashRqmtItemTypeId().intValue() > 0) {
                    cashReqItemTypeId = cashReqItemType.getCashRqmtItemTypeId();
                }

                Integer avaItemTypeId = null;
                if (avaItemType != null && avaItemType.getFundsAvailableItemTypeId() != null
                    && avaItemType.getFundsAvailableItemTypeId().intValue() > 0) {
                    avaItemTypeId = avaItemType.getFundsAvailableItemTypeId();
                }

                DaiCashReq daiCashReq = null;
                DaiCashReqItem daiCashReqItem = null;
                CalOfAvailable calOfAvailable = null;
                CalOfAvailableItem calOfAvailableItem = null;
                while (!effectiveFrom.after(effectiveTo)) {
                    // Daily Cash requirement - Insert/Update Start
                    daiCashReq = daiCashReqRepository
                        .findOne(DaiCashReqPredicate.isExistingPredicate(effectiveFrom, investmentTypeId));
                    if (daiCashReq == null) {
                        daiCashReq = new DaiCashReq();
                        daiCashReq.setInvestDate(effectiveFrom);
                        daiCashReq.setRequTotalAmount(amount);
                        daiCashReq.setStatus(CoreConstant.STATUS_ACTIVE);
                        daiCashReq.setInvestType(invType);

                    } else {
                        if (FundsConstant.GEN_REQ_MODE_ADD == mode) {
                            BigDecimal amountResult = null;
                            if (daiCashReq.getRequTotalAmount() == null) {
                                amountResult = amount;
                            } else {
                                amountResult = daiCashReq.getRequTotalAmount().add(amount);
                            }
                            daiCashReq.setRequTotalAmount(amountResult);
                        } else if (FundsConstant.GEN_REQ_MODE_UPDATE == mode) {
                            daiCashReq.setRequTotalAmount(amount);
                        }
                    }
                    daiCashReqRepository.save(daiCashReq);
                    // Daily Cash requirement - Insert/Update End

                    if (daiCashReq != null && daiCashReq.getDailyCashRqmtId() != null
                        && daiCashReq.getDailyCashRqmtId().intValue() > 0 && cashReqItemTypeId != null
                        && cashReqItemTypeId.intValue() > 0) {
                        // Daily Cash requirement Item - Insert/Update Start
                        daiCashReqItem = daiCashReqItemRepository.findOne(DaiCashReqItemPredicate
                            .isExistingPredicate(investmentTypeId, cashReqItemTypeId, daiCashReq.getDailyCashRqmtId()));
                        if (daiCashReqItem == null) {
                            daiCashReqItem = new DaiCashReqItem();
                            daiCashReqItem.setInvestType(invType);
                            daiCashReqItem.setDaiCashReq(daiCashReq);
                            daiCashReqItem.setCashReqItem(cashReqItemType);
                            daiCashReqItem.setRequAmount(amount);
                            daiCashReqItem.setStatus(CoreConstant.STATUS_ACTIVE);
                        } else {
                            if (FundsConstant.GEN_REQ_MODE_ADD == mode) {
                                BigDecimal itemAmount = null;
                                if (daiCashReqItem.getRequAmount() == null) {
                                    itemAmount = amount;
                                } else {
                                    itemAmount = daiCashReqItem.getRequAmount().add(amount);
                                }
                                daiCashReqItem.setRequAmount(itemAmount);
                            } else if (FundsConstant.GEN_REQ_MODE_UPDATE == mode) {
                                daiCashReqItem.setRequAmount(amount);
                            }

                        }
                        daiCashReqItemRepository.save(daiCashReqItem);
                        // Daily Cash requirement Item - Insert/Update End
                    }

                    // Calculation of Funds Available - Insert/Update Start
                    calOfAvailable
                        = calOfAvailableRepository.findOne(CalOfAvailablePredicate.isExistingPredicate(effectiveFrom));
                    if (calOfAvailable == null) {
                        calOfAvailable = new CalOfAvailable();
                        // get current user
                        // calOfAvailable.setSubmittedBy(submittedBy);
                        calOfAvailable.setInvestmentDate(effectiveFrom);
                        // calOfAvailable.setApprovedBy(approvedBy);
                        calOfAvailable.setAvailableAmount(amount);
                        calOfAvailable.setStatus(CoreConstant.STATUS_ACTIVE);
                    } else {
                        if (FundsConstant.GEN_REQ_MODE_ADD == mode) {
                            BigDecimal amountResult = null;
                            if (calOfAvailable.getAvailableAmount() == null) {
                                amountResult = amount;
                            } else {
                                amountResult = calOfAvailable.getAvailableAmount().add(amount);
                            }
                            calOfAvailable.setAvailableAmount(amountResult);
                        } else if (FundsConstant.GEN_REQ_MODE_UPDATE == mode) {
                            calOfAvailable.setAvailableAmount(amount);
                        }
                    }
                    calOfAvailableRepository.save(calOfAvailable);
                    // Calculation of Funds Available - Insert/Update End

                    if (calOfAvailable != null && calOfAvailable.getCalculationOfFundsAvailableId() != null
                        && calOfAvailable.getCalculationOfFundsAvailableId().intValue() > 0 && avaItemTypeId != null
                        && avaItemTypeId.intValue() > 0) {
                        // Calculation of Funds Available Item - Insert/Update Start
                        calOfAvailableItem
                            = calOfAvailableItemRepository.findOne(CalOfAvailableItemPredicate.isExistingPredicate(
                                investmentTypeId, avaItemTypeId, calOfAvailable.getCalculationOfFundsAvailableId()));
                        if (calOfAvailableItem == null) {
                            calOfAvailableItem = new CalOfAvailableItem();
                            calOfAvailableItem
                                .setCalculationOfFundsAvailableId(calOfAvailable.getCalculationOfFundsAvailableId());
                            calOfAvailableItem.setInvestmentType(invType);
                            calOfAvailableItem.setFundsAvailableItemType(avaItemType);
                            calOfAvailableItem.setAvailableAmount(amount);
                            calOfAvailableItem.setStatus(CoreConstant.STATUS_ACTIVE);
                        } else {
                            if (FundsConstant.GEN_REQ_MODE_ADD == mode) {
                                BigDecimal itemAmount = null;
                                if (calOfAvailableItem.getAvailableAmount() == null) {
                                    itemAmount = amount;
                                } else {
                                    itemAmount = calOfAvailableItem.getAvailableAmount().add(amount);
                                }
                                calOfAvailableItem.setAvailableAmount(itemAmount);
                            } else if (FundsConstant.GEN_REQ_MODE_UPDATE == mode) {
                                calOfAvailableItem.setAvailableAmount(amount);
                            }
                        }
                        calOfAvailableItemRepository.save(calOfAvailableItem);
                        // Calculation of Funds Available Item - Insert/Update End
                    }

                    calendar.setTime(effectiveFrom);
                    calendar.add(Calendar.DATE, 1);
                    effectiveFrom = calendar.getTime();
                }

            }

        }

        log.info("updateDailyCashRequirement end -");
    }

    private boolean saveInactiveCaseRequirement(CashRequirementResultDTO cashRequirementResultDTO) {
        log.info("saveInactiveCaseRequirement start - and param :" + cashRequirementResultDTO);
        if (cashRequirementResultDTO != null) {
            CashReq cashReq = this.cashRequirementDTOAssembler.toEntity(cashRequirementResultDTO);
            cashReq.setStatus(CoreConstant.STATUS_INACTIVE);
            cashReq = cashRequirementRepository.save(cashReq);

            Date rqmtPeriodFrom = cashReq.getRqmtPeriodFrom();
            Date rqmtPeriodTo = cashReq.getRqmtPeriodTo();
            InvType investType = cashReq.getInvestType();
            CashReqItem cashReqItem = cashReqItemRepository.findOne(CashReqItemPredicate.findByNameAndInvestTypeCode(
                FundsConstant.DEFAULT_CASH_REQUIREMENT_ITEM_TYPE_NAME, investType.getInvestTypeCode()));
            Integer cashRqmtItemTypeId = cashReqItem.getCashRqmtItemTypeId();
            if (rqmtPeriodFrom != null && rqmtPeriodTo != null) {
                Calendar calendar = Calendar.getInstance();
                BigDecimal dailyRqmt = cashReq.getDailyRqmt();
                BigDecimal yearlyRqmt = cashReq.getYearlyRqmt();
                DaiCashReq daiCashReq = null;
                DaiCashReqItem daiCashReqItem = null;
                BigDecimal subtractResult = null;
                while (!rqmtPeriodFrom.after(rqmtPeriodTo)) {
                    daiCashReq = daiCashReqRepository.findOne(
                        DaiCashReqPredicate.isExistingPredicate(rqmtPeriodFrom, investType.getInvestmentTypeId()));
                    subtractResult = daiCashReq.getRequTotalAmount().subtract(dailyRqmt);
                    daiCashReq.setRequTotalAmount(subtractResult);
                    daiCashReqRepository.save(daiCashReq);

                    daiCashReqItem = daiCashReqItemRepository.findOne(DaiCashReqItemPredicate
                        .isExistingPredicate(investType.getInvestmentTypeId(), cashRqmtItemTypeId, rqmtPeriodFrom));
                    subtractResult = daiCashReqItem.getRequAmount().subtract(dailyRqmt);
                    daiCashReqItem.setRequAmount(subtractResult);
                    daiCashReqItemRepository.save(daiCashReqItem);

                    calendar.setTime(rqmtPeriodFrom);
                    calendar.add(Calendar.DATE, 1);
                    rqmtPeriodFrom = calendar.getTime();
                }

                log.info("saveInactiveCaseRequirement end - and return :" + true);
                return true;
            }
        }

        log.info("saveInactiveCaseRequirement end - and return :" + false);
        return false;
    }

    @Override
    public DailyCashRequirementResultDTO searchDailyCashRequirement(SearchDailyCashRequirementCriteriaDTO criteriaDTO) {
        log.info("searchDailyCashRequirement start - and param :" + criteriaDTO);
        List<DaiCashReq> daiCashReqs
            = (List<DaiCashReq>)daiCashReqRepository.findAll(DaiCashReqPredicate.findByCriteria(criteriaDTO));
        if (CommonUtils.isNotEmpty(daiCashReqs)) {
            for (DaiCashReq daiCashReq : daiCashReqs) {

            }
        }
        return null;
    }

}
