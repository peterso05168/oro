package hk.oro.iefas.ws.dividend.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.exceptions.BusinessException;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.adjudication.entity.AppAdjResultItem;
import hk.oro.iefas.domain.adjudication.entity.CredType;
import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.casemgt.entity.CaseAccountInfo;
import hk.oro.iefas.domain.casemgt.entity.CaseType;
import hk.oro.iefas.domain.dividend.dto.AdjudicationTypeDTO;
import hk.oro.iefas.domain.dividend.dto.ApproveHistoryDTO;
import hk.oro.iefas.domain.dividend.dto.ApprovedAdjucationResultItemDTO;
import hk.oro.iefas.domain.dividend.dto.CalculatedCreditorDividendDistributionDTO;
import hk.oro.iefas.domain.dividend.dto.CalculationOfDividendFeeDTO;
import hk.oro.iefas.domain.dividend.dto.CaseFeeForDividendCalculationDTO;
import hk.oro.iefas.domain.dividend.dto.CaseFeeMaintenanceDTO;
import hk.oro.iefas.domain.dividend.dto.CreateOrFeeComputationDTO;
import hk.oro.iefas.domain.dividend.dto.DistributedAmountDTO;
import hk.oro.iefas.domain.dividend.dto.DividendCalculationCreditorDTO;
import hk.oro.iefas.domain.dividend.dto.DividendCalculationDTO;
import hk.oro.iefas.domain.dividend.dto.DividendParameterDTO;
import hk.oro.iefas.domain.dividend.dto.ProvisionsDTO;
import hk.oro.iefas.domain.dividend.dto.SearchOrFeeComputationCriteriaDTO;
import hk.oro.iefas.domain.dividend.dto.ValidateOrFeeComputationDTO;
import hk.oro.iefas.domain.dividend.entity.CaseFeeMain;
import hk.oro.iefas.domain.dividend.entity.CaseFeeType;
import hk.oro.iefas.domain.dividend.entity.DivCalCred;
import hk.oro.iefas.domain.dividend.entity.DivCalCredDist;
import hk.oro.iefas.domain.dividend.entity.DivCalOfDiv;
import hk.oro.iefas.domain.dividend.entity.DivCaseFeeCal;
import hk.oro.iefas.domain.dividend.entity.DivParameter;
import hk.oro.iefas.domain.dividend.entity.DivProvision;
import hk.oro.iefas.domain.dividend.entity.DivSchedule;
import hk.oro.iefas.domain.dividend.entity.DivScheduleDist;
import hk.oro.iefas.domain.dividend.entity.DivScheduleItem;
import hk.oro.iefas.domain.dividend.entity.DividendCal;
import hk.oro.iefas.domain.dividend.entity.FeeCharg;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.entity.AnalysisCode;
import hk.oro.iefas.domain.shroff.entity.ShrGeneralLedger;
import hk.oro.iefas.domain.system.entity.SysApprovalWf;
import hk.oro.iefas.ws.adjudication.repository.AdjResultRepository;
import hk.oro.iefas.ws.adjudication.repository.AppAdjResultItemRepository;
import hk.oro.iefas.ws.adjudication.repository.CredTypeRepository;
import hk.oro.iefas.ws.adjudication.repository.predicate.AdjResultPredicate;
import hk.oro.iefas.ws.casemgt.repository.CaseAccountInfoRepository;
import hk.oro.iefas.ws.casemgt.repository.CaseRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.CaseAccountInfoDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.assembler.CaseDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.predicate.CaseAccountInfoPredicate;
import hk.oro.iefas.ws.common.service.CommonService;
import hk.oro.iefas.ws.dividend.repository.AdjTypeRepository;
import hk.oro.iefas.ws.dividend.repository.CaseFeeMainRepository;
import hk.oro.iefas.ws.dividend.repository.CaseFeeTypeRepository;
import hk.oro.iefas.ws.dividend.repository.DivCalCredDistRepository;
import hk.oro.iefas.ws.dividend.repository.DivCalCredRepository;
import hk.oro.iefas.ws.dividend.repository.DivCalOfDivRepository;
import hk.oro.iefas.ws.dividend.repository.DivCaseFeeCalRepository;
import hk.oro.iefas.ws.dividend.repository.DivParameterRepository;
import hk.oro.iefas.ws.dividend.repository.DivProvisionRepository;
import hk.oro.iefas.ws.dividend.repository.DivScheduleDistRepository;
import hk.oro.iefas.ws.dividend.repository.DivScheduleItemRepository;
import hk.oro.iefas.ws.dividend.repository.DivScheduleRepository;
import hk.oro.iefas.ws.dividend.repository.DividendCalRepository;
import hk.oro.iefas.ws.dividend.repository.FeeChargRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.ApproveHistoryDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.ApprovedAdjucationResultItemDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.CalculatedCreditorDividendDistributionDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.CalculationOfDividendFeeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.CaseFeeForDividendCalculationDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.CaseFeeMaintenanceDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.DividendCalculationCreditorDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.DividendCalculationDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.ProvisionsDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.predicate.AppAdjResultItemPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.CaseFeeMainPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.CaseFeeTypePredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivCalCredPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivCaseFeeCalPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivScheduleDistPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivScheduleItemPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivSchedulePredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DividendCalPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.FeeChargPredicate;
import hk.oro.iefas.ws.dividend.service.DividendParameterService;
import hk.oro.iefas.ws.dividend.service.OrFeeComputationService;
import hk.oro.iefas.ws.shroff.repository.ShrGeneralLedgerRepository;
import hk.oro.iefas.ws.system.repository.SysApprovalWFRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3274 $ $Date: 2018-06-25 15:52:20 +0800 (週一, 25 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Service
public class OrFeeComputationServiceImpl implements OrFeeComputationService {
    @Autowired
    private CommonService commonService;

    @Autowired
    private DividendParameterService dividendParameterService;

    @Autowired
    private DividendCalRepository dividendCalRepository;

    @Autowired
    private DivCalCredRepository divCalCredRepository;

    @Autowired
    private CredTypeRepository credTypeRepository;

    @Autowired
    private DivCalOfDivRepository divCalOfDivRepository;

    @Autowired
    private DivProvisionRepository divProvisionRepository;

    @Autowired
    private DivCalCredDistRepository divCalCredDistRepository;

    @Autowired
    private SysApprovalWFRepository sysApprovalWFRepository;

    @Autowired
    private DivCaseFeeCalRepository divCaseFeeCalRepository;

    @Autowired
    private CaseFeeMainRepository caseFeeMainRepository;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private CaseAccountInfoRepository caseAccountInfoRepository;

    @Autowired
    private CaseFeeTypeRepository caseFeeTypeRepository;

    @Autowired
    private AdjResultRepository adjResultRepository;

    @Autowired
    private AppAdjResultItemRepository appAdjResultItemRepository;

    @Autowired
    private AdjTypeRepository adjTypeRepository;

    @Autowired
    private FeeChargRepository feeChargRepository;

    @Autowired
    private ShrGeneralLedgerRepository shrGeneralLedgerRepository;

    @Autowired
    private DivScheduleRepository divScheduleRepository;

    @Autowired
    private DivScheduleItemRepository divScheduleItemRepository;

    @Autowired
    private DivScheduleDistRepository divScheduleDistRepository;

    @Autowired
    private DivParameterRepository divParameterRepository;

    @Autowired
    private DividendCalculationDTOAssembler dividendCalculationDTOAssembler;

    @Autowired
    private DividendCalculationCreditorDTOAssembler dividendCalculationCreditorDTOAssembler;

    @Autowired
    private CalculationOfDividendFeeDTOAssembler calculationOfDividendFeeDTOAssembler;

    @Autowired
    private ProvisionsDTOAssembler provisionsDTOAssembler;

    @Autowired
    private CalculatedCreditorDividendDistributionDTOAssembler calculatedCreditorDividendDistributionDTOAssembler;

    @Autowired
    private ApproveHistoryDTOAssembler approveHistoryDTOAssembler;

    @Autowired
    private CaseFeeForDividendCalculationDTOAssembler caseFeeForDividendCalculationDTOAssembler;

    @Autowired
    private CaseFeeMaintenanceDTOAssembler caseFeeMaintenanceDTOAssembler;

    @Autowired
    private CaseAccountInfoDTOAssembler caseAccountInfoDTOAssembler;

    @Autowired
    private ApprovedAdjucationResultItemDTOAssembler approvedAdjucationResultItemDTOAssembler;

    @Override
    @Transactional(readOnly = true)
    public Page<DividendCalculationDTO>
        searchORFeeComputationList(SearchDTO<SearchOrFeeComputationCriteriaDTO> criteriaDTO) {
        log.info("searchORFeeComputationList start criteriaDTO:" + criteriaDTO);
        Pageable pageable = criteriaDTO.getPage().toPageable();
        Page<DividendCal> page
            = dividendCalRepository.findAll(DividendCalPredicate.findByCriteria(criteriaDTO.getCriteria()), pageable);
        Page<DividendCalculationDTO> resultDTO = dividendCalculationDTOAssembler.toResultDTO(page);
        return resultDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveORFeeComputation(DividendCalculationDTO dividendCalculationDTO) {
        log.info("saveORFeeComputation start dividendCalculationDTO:" + dividendCalculationDTO);
        Integer dividendCalculationId = dividendCalculationDTO.getDividendCalculationId();
        DividendCal dividendCal = DataUtils.copyProperties(dividendCalculationDTO, DividendCal.class);
        dividendCal.setDivCalId(dividendCalculationId);
        dividendCal.setDivDate(dividendCalculationDTO.getPaymentDate());
        CaseDTO vcase = dividendCalculationDTO.getVcase();
        if (vcase != null) {
            dividendCal.setCaseInfo(caseRepository.findOne(vcase.getCaseId()));
        }
        if (dividendCalculationDTO.getMainCaseAccount() != null) {
            dividendCal.setCaseAcId(dividendCalculationDTO.getMainCaseAccount().getCaseAcId());
        }
        if (dividendCalculationId == null || dividendCalculationId.intValue() <= 0) {
            dividendCal.setDivDate(DateUtils.getCurrentDate());
        }
        String paymentType = dividendCalculationDTO.getPaymentType();
        dividendCal.setDivType(paymentType);
        Integer workFlowId = dividendCal.getWorkFlowId();
        workFlowId = commonService.saveSysApprovalWf(workFlowId, dividendCalculationDTO.getSysApprovalWf());
        dividendCal.setWorkFlowId(workFlowId);
        dividendCalRepository.save(dividendCal);

        // save creditor type for or fee computation
        dividendCalculationId = dividendCal.getDivCalId();
        List<DividendCalculationCreditorDTO> dividendCalculationCreditors
            = dividendCalculationDTO.getDividendCalculationCreditors();
        saveDivCalCreds(dividendCalculationCreditors, dividendCal);

        // save values of detail tab(DivCalOfDivFee for input numbers ; DivCaseFeeCals for gazette data table)
        CalculationOfDividendFeeDTO calculationOfDividendFee = dividendCalculationDTO.getCalculationOfDividendFee();
        saveDivCalOfDivFeeAndDivCaseFeeCals(calculationOfDividendFee, dividendCalculationId);

        // save values of provision tab for input numbers
        ProvisionsDTO provisions = dividendCalculationDTO.getProvisions();
        if (provisions != null) {
            DivProvision divProvision = DataUtils.copyProperties(provisions, DivProvision.class);
            divProvision.setDivCalId(dividendCalculationId);
            divProvisionRepository.save(divProvision);
        }

        // save values for distributions tab for data table
        List<CalculatedCreditorDividendDistributionDTO> distributions
            = dividendCalculationDTO.getCalculatedCreditorDividendDistributions();
        if (CommonUtils.isNotEmpty(distributions)) {
            for (CalculatedCreditorDividendDistributionDTO distritubtion : distributions) {
                DivCalCredDist dist = DataUtils.copyProperties(distritubtion, DivCalCredDist.class);
                dist.setDivCalId(dividendCalculationId);
                if (dist.getDistAmount() == null) {
                    dist.setDistAmount(BigDecimal.ZERO);
                }
                if (dist.getPercent() == null) {
                    dist.setPercent(BigDecimal.ZERO);
                }
                AdjudicationTypeDTO adjudicationType = distritubtion.getAdjudicationType();
                if (adjudicationType != null) {
                    dist.setAdjType(adjTypeRepository.findOne(adjudicationType.getAdjudicationTypeId()));
                }
                divCalCredDistRepository.save(dist);
            }
        }

        // TODO save Work flow
        log.info("saveORFeeComputation end return:" + dividendCalculationId);
        return dividendCalculationId;
    }

    private void saveDivCalCreds(List<DividendCalculationCreditorDTO> dividendCalculationCreditors,
        DividendCal dividendCal) {
        if (CommonUtils.isNotEmpty(dividendCalculationCreditors)) {
            for (DividendCalculationCreditorDTO creType : dividendCalculationCreditors) {
                DivCalCred divCalCred = null;
                if (creType.getDivCalCredId() != null && CoreConstant.STATUS_INACTIVE.equals(creType.getStatus())) {
                    divCalCred = divCalCredRepository.findOne(creType.getDivCalCredId());
                    divCalCred.setStatus(CoreConstant.STATUS_INACTIVE);
                } else if (creType.getDivCalCredId() == null
                    && CoreConstant.STATUS_ACTIVE.equals(creType.getStatus())) {
                    divCalCred = DataUtils.copyProperties(creType, DivCalCred.class);
                    divCalCred.setCredType(credTypeRepository.findOne(creType.getCreditorTypeId()));
                    divCalCred.setDivCalId(dividendCal.getDivCalId());
                }
                if (divCalCred != null) {
                    divCalCredRepository.save(divCalCred);
                }
            }
        }
    }

    private void saveDivCalOfDivFeeAndDivCaseFeeCals(CalculationOfDividendFeeDTO calculationOfDividendFee,
        Integer dividendCalculationId) {
        if (calculationOfDividendFee != null) {
            DivCalOfDiv divCalOfDiv = DataUtils.copyProperties(calculationOfDividendFee, DivCalOfDiv.class);
            divCalOfDiv.setDivCalId(dividendCalculationId);
            divCalOfDivRepository.save(divCalOfDiv);

            List<CaseFeeForDividendCalculationDTO> caseFeeForDividendCalculations
                = calculationOfDividendFee.getCaseFeeForDividendCalculations();
            if (CommonUtils.isNotEmpty(caseFeeForDividendCalculations)) {
                CaseFeeType caseFeeType = null;
                String caseFeeTypeName = calculationOfDividendFee.getCaseFeeTypeName();
                if (caseFeeTypeName != null) {
                    caseFeeType = caseFeeTypeRepository.findOne(CaseFeeTypePredicate.findByName(caseFeeTypeName));
                }
                if (caseFeeType == null) {
                    throw new BusinessException(caseFeeTypeName + "not exists in db");
                }
                for (CaseFeeForDividendCalculationDTO caseFee : caseFeeForDividendCalculations) {
                    if (caseFee.getCaseFeeCalId() == null && CoreConstant.STATUS_INACTIVE.equals(caseFee.getStatus())) {
                        continue;
                    }
                    DivCaseFeeCal divCaseFeeCal = DataUtils.copyProperties(caseFee, DivCaseFeeCal.class);
                    divCaseFeeCal.setDivCalId(dividendCalculationId);
                    divCaseFeeCal.setDivCaseFeeType(caseFeeType);
                    divCaseFeeCalRepository.save(divCaseFeeCal);
                }
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DividendCalculationDTO searchORFeeComputation(CreateOrFeeComputationDTO createORFeeComputationDTO) {
        DividendCalculationDTO dto = new DividendCalculationDTO();
        CaseDTO vcase = createORFeeComputationDTO.getVcase();
        dto.setVcase(createORFeeComputationDTO.getVcase());
        String paymentType = createORFeeComputationDTO.getPaymentType();
        dto.setPaymentType(paymentType);
        CalculationOfDividendFeeDTO feeDTO = new CalculationOfDividendFeeDTO();

        boolean isBankruptcy = false;
        if (DividendConstant.CASE_TYPE_B.equals(vcase.getCaseType().getCaseTypeCode())) {
            isBankruptcy = true;
        }
        List<CaseAccountInfo> caseAccountInfos = (List<CaseAccountInfo>)caseAccountInfoRepository
            .findAll(CaseAccountInfoPredicate.findMainAccountByCase(vcase.getCaseId()));
        if (CommonUtils.isNotEmpty(caseAccountInfos)) {
            CaseAccountInfo mainCaseAccountInfo = caseAccountInfos.get(0);
            dto.setMainCaseAccount(caseAccountInfoDTOAssembler.toDTO(mainCaseAccountInfo));
            // cash in hand = liquid cash + investment
            dto.setCaseInHand(
                CommonUtils.add(mainCaseAccountInfo.getLiquidCashAmount(), mainCaseAccountInfo.getInvestmentAmount()));
        }

        // get latest or fee computation of the case
        Integer latestDivCalId = null;
        DivCalOfDiv divCalOfDiv = null;
        DividendCal dividendCal = getLatestFeeComputation(dto.getVcase().getCaseId(), null);
        if (dividendCal != null) {
            latestDivCalId = dividendCal.getDivCalId();
            List<DivCalOfDiv> divCalOfDivs
                = divCalOfDivRepository.findByDivCalIdAndStatus(latestDivCalId, CoreConstant.STATUS_ACTIVE);
            if (CommonUtils.isNotEmpty(divCalOfDivs)) {
                divCalOfDiv = divCalOfDivs.get(0);
                feeDTO.setAlreadyChargedOrFee(divCalOfDiv.getCalculatedOrFee());
                // if the latest computation of this case has selected Minimum Official Receiver's Fee, then it will be
                // disabled
                feeDTO.setMinOrFee(CommonUtils.getBigDecimal(divCalOfDiv.getMinOrFee()));
                feeDTO.setMinOrFeeDisabled(true);
            } else {
                feeDTO.setAlreadyChargedOrFee(BigDecimal.ZERO);
            }
        }

        if (isBankruptcy) {
            getBankruptcyData(feeDTO, divCalOfDiv);
        } else {
            getLiquidationData(feeDTO, divCalOfDiv);
        }

        // B3/BIV(3) shows the infomation of latest calculation
        setDistributedToCreditors(latestDivCalId, paymentType, feeDTO);

        feeDTO.setStatus(CoreConstant.STATUS_ACTIVE);
        dto.setCalculationOfDividendFee(feeDTO);

        // when check up creditor types, use case/creditor type to get the adjudication result
        dto.setCalculatedCreditorDividendDistributions(getDivCalCredDists(paymentType));

        Integer caseId = createORFeeComputationDTO.getVcase().getCaseId();
        dto.setDistributions(getAppAdjResultItemsByCase(caseId));

        ProvisionsDTO provisions = new ProvisionsDTO();
        DividendParameterDTO param;
        List<DividendParameterDTO> dividendParameters = dividendParameterService.searchDividendParameter();
        if (CommonUtils.isNotEmpty(dividendParameters)) {
            param = dividendParameters.get(0);
        } else {
            throw new BusinessException("DB value of DIV_PARAMETER is wrong.");
        }

        if (isBankruptcy) {
            provisions.setOldBalance(param.getProvisionOddBalBank());
        } else {
            provisions.setOldBalance(param.getProvisionOddBalLiqu());
        }
        provisions.setStatus(CoreConstant.STATUS_ACTIVE);
        dto.setProvisions(provisions);
        return dto;
    }

    private void getBankruptcyData(CalculationOfDividendFeeDTO feeDTO, DivCalOfDiv divCalOfDiv) {
        // B9
        List<ShrGeneralLedger> ledgerListOfB9 = getLedgersByCaseFeeType(DividendConstant.CASE_FEE_TYPE_B9);
        BigDecimal totalBalanceOfB9 = getTotalBalance(ledgerListOfB9);
        feeDTO.setBb9Assets(totalBalanceOfB9);
        feeDTO.setBb9ActualAssets(totalBalanceOfB9);
        feeDTO.setBb9ActualAssetsCharged(BigDecimal.ZERO);
        feeDTO.setBb9AssetsUncharged(totalBalanceOfB9);
        // B1
        feeDTO = caculateOfB1(feeDTO, DividendConstant.CASE_FEE_TYPE_B1);
        // B2(1)
        feeDTO.setBb2UnaprpvedCompo(commonCaculate(DividendConstant.CASE_FEE_TYPE_B2));

        if (divCalOfDiv != null) {
            // B5
            feeDTO.setBb5NumberOfCreditor(CommonUtils.getBigDecimal(divCalOfDiv.getBb5NumberOfCreditor()));
            feeDTO.setBb5NumberOfDedtor(CommonUtils.getBigDecimal(divCalOfDiv.getBb5NumberOfDedtor()));
            BigDecimal bb5TotalParticipants = feeDTO.getBb5NumberOfCreditor().add(feeDTO.getBb5NumberOfDedtor());
            feeDTO.setBb5TotalParticipants(bb5TotalParticipants);
            feeDTO.setBb5ParticipantsCharged(bb5TotalParticipants);
            feeDTO.setBb5ParticipantsUncharged(BigDecimal.ZERO);

            BigDecimal statFee = BigDecimal.ZERO;
            List<CaseFeeMain> caseFeeMains = (List<CaseFeeMain>)caseFeeMainRepository.findAll(CaseFeeMainPredicate
                .findByCaseFeeTypeNameAndDate(DividendConstant.CASE_FEE_TYPE_B5, DateUtils.getCurrentDate()));
            if (CommonUtils.isEmpty(caseFeeMains)) {
                log.info("CaseFeeMain of " + DividendConstant.CASE_FEE_TYPE_B5 + " is missing !");
                throw new BusinessException("CaseFeeMain of " + DividendConstant.CASE_FEE_TYPE_B5 + " is missing !");
            }
            statFee = caculate(caseFeeMains.get(0), bb5TotalParticipants);

            BigDecimal statFeeUncharged
                = statFee.subtract(CommonUtils.getBigDecimal(divCalOfDiv.getBb5StatFeeCharged()));
            feeDTO.setBb5StatFeeUncharged(statFeeUncharged);

            feeDTO.setBb5StatFeeCharged(
                CommonUtils.add(divCalOfDiv.getBb5StatFeeCharged(), divCalOfDiv.getLbivStatFeeUncharged()));

            // B6
            feeDTO.setBb6MeetingFee(CommonUtils.getBigDecimal(divCalOfDiv.getBb6MeetingFee()));

            // B9
            feeDTO.setBb9AssetsCharged(CommonUtils.getBigDecimal(divCalOfDiv.getBb9ActualAssets()));
        }
    }

    private void getLiquidationData(CalculationOfDividendFeeDTO feeDTO, DivCalOfDiv divCalOfDiv) {
        // BI
        List<ShrGeneralLedger> ledgerListOfBiv1 = getLedgersByCaseFeeType(DividendConstant.CASE_FEE_TYPE_BI);
        BigDecimal totalBalanceOfBiv1 = getTotalBalance(ledgerListOfBiv1);
        feeDTO.setLbiAssets(totalBalanceOfBiv1);
        feeDTO.setLbiActualAssets(totalBalanceOfBiv1);
        feeDTO.setLbiActualAssetsCharged(BigDecimal.ZERO);
        feeDTO.setLbiAssetsUncharged(totalBalanceOfBiv1);
        // BIV(2)
        feeDTO = caculateOfB1(feeDTO, DividendConstant.CASE_FEE_TYPE_BIV2);

        if (divCalOfDiv != null) {
            // BIV(1)
            feeDTO.setLbivMembers(CommonUtils.getBigDecimal(divCalOfDiv.getLbivMembers()));
            feeDTO.setLbivCredNumber(CommonUtils.getBigDecimal(divCalOfDiv.getLbivCredNumber()));
            feeDTO.setLbivDebtNumber(CommonUtils.getBigDecimal(divCalOfDiv.getLbivDebtNumber()));
            BigDecimal lbivTotalParticipants
                = feeDTO.getLbivMembers().add(feeDTO.getLbivCredNumber()).add(feeDTO.getLbivDebtNumber());
            feeDTO.setLbivTotalParticipants(lbivTotalParticipants);
            feeDTO.setLbivParticipantsCharged(lbivTotalParticipants);
            feeDTO.setLbivParticipantsUncharged(BigDecimal.ZERO);

            BigDecimal statFee = BigDecimal.ZERO;
            List<CaseFeeMain> caseFeeMains = (List<CaseFeeMain>)caseFeeMainRepository.findAll(CaseFeeMainPredicate
                .findByCaseFeeTypeNameAndDate(DividendConstant.CASE_FEE_TYPE_BIV1, DateUtils.getCurrentDate()));
            if (CommonUtils.isEmpty(caseFeeMains)) {
                log.info("CaseFeeMain of " + DividendConstant.CASE_FEE_TYPE_BIV1 + " is missing !");
                throw new BusinessException("CaseFeeMain of " + DividendConstant.CASE_FEE_TYPE_BIV1 + " is missing !");
            }
            statFee = caculate(caseFeeMains.get(0), lbivTotalParticipants);

            BigDecimal statFeeUncharged
                = statFee.subtract(CommonUtils.getBigDecimal(divCalOfDiv.getLbivStatFeeCharged()));
            feeDTO.setLbivStatFeeUncharged(statFeeUncharged);

            feeDTO.setLbivStatFeeCharged(
                CommonUtils.add(divCalOfDiv.getLbivStatFeeCharged(), divCalOfDiv.getLbivStatFeeUncharged()));

            // BI
            feeDTO.setLbiAssetsCharged(CommonUtils.getBigDecimal(divCalOfDiv.getLbiAssetsCharged()));
        }
    }

    private DividendCal getLatestFeeComputation(Integer caseId, DividendCal dividendCal) {
        // get latest or fee computation of the case
        Integer dividendCalId = null;
        if (dividendCal != null) {
            dividendCalId = dividendCal.getDivCalId();
        }
        List<DividendCal> dividendCals = (List<DividendCal>)dividendCalRepository.findAll(
            DividendCalPredicate.existedByCaseAndCalNot(caseId, dividendCalId), DividendCalPredicate.orderByDivDate());
        if (CommonUtils.isNotEmpty(dividendCals)) {
            if (dividendCalId != null) {
                List<DividendCal> dividendCalsBefore = dividendCals.stream()
                    .filter(item -> item.getDivDate().before(dividendCal.getDivDate())).collect(Collectors.toList());
                if (CommonUtils.isNotEmpty(dividendCalsBefore)) {
                    return dividendCalsBefore.get(0);
                }
            } else {
                return dividendCals.get(0);
            }
        }
        return null;
    }

    private List<ShrGeneralLedger> getLedgersByCaseFeeType(String caseFeeTypeName) {
        List<FeeCharg> feeChargs
            = (List<FeeCharg>)feeChargRepository.findAll(FeeChargPredicate.findByCaseFeeType(caseFeeTypeName));
        List<ShrGeneralLedger> ledgerList = new ArrayList<>();
        if (CommonUtils.isNotEmpty(feeChargs)) {
            feeChargs.stream().forEach(item -> {
                AnalysisCode analysisCode = item.getAnalysisCode();
                if (analysisCode != null) {
                    List<ShrGeneralLedger> ledgers = shrGeneralLedgerRepository
                        .findByAnalysisCodeAndStatus(analysisCode.getAnalysisCode(), CoreConstant.STATUS_ACTIVE);
                    ledgerList.addAll(ledgers);
                }
            });
        }
        return ledgerList;
    }

    public CalculationOfDividendFeeDTO caculateOfB1(CalculationOfDividendFeeDTO feeDTO, String caseFeeType) {
        List<ShrGeneralLedger> ledgerListOfB1 = getLedgersByCaseFeeType(caseFeeType);
        BigDecimal totalAmountOfB1 = BigDecimal.ZERO;
        BigDecimal bb1NumTranscation = BigDecimal.ZERO;
        BigDecimal totalOfB1B1a = BigDecimal.ZERO;
        if (CommonUtils.isNotEmpty(ledgerListOfB1)) {
            // find the calculation type by case fee type
            List<CaseFeeMain> caseFeeMains = (List<CaseFeeMain>)caseFeeMainRepository
                .findAll(CaseFeeMainPredicate.findByCaseFeeTypeNameAndDate(caseFeeType, null));
            if (CommonUtils.isNotEmpty(caseFeeMains)) {
                try {
                    Date compulatin
                        = DateUtils.getFormatedDate(DividendConstant.OR_FEE_COMPUTATION_DATE, "yyyy-MM-dd HH:mm");

                    // case fee main of B1
                    CaseFeeMain caseFeeMainForB1
                        = caseFeeMains.stream().filter(item -> item.getPeriodTo().before(compulatin)).findFirst().get();
                    // case fee main of B1a
                    CaseFeeMain caseFeeMainForB1a = caseFeeMains.stream()
                        .filter(item -> item.getPeriodFrom().compareTo(compulatin) >= 0).findFirst().get();

                    if (caseFeeMainForB1 == null || caseFeeMainForB1a == null) {
                        log.info("CaseFeeMain of " + DividendConstant.CASE_FEE_TYPE_B1 + " is missing !");
                        throw new BusinessException(
                            "CaseFeeMain of " + DividendConstant.CASE_FEE_TYPE_B1 + " is missing !");
                    }

                    List<ShrGeneralLedger> ledgerListB1 = ledgerListOfB1.stream()
                        .filter(item -> item.getWorkingDate() != null && item.getWorkingDate().before(compulatin))
                        .collect(Collectors.toList());
                    totalAmountOfB1 = getTotalBalance(ledgerListB1);

                    BigDecimal totalAmountOfB1a = BigDecimal.ZERO;
                    List<ShrGeneralLedger> ledgerListB1a = ledgerListOfB1.stream()
                        .filter(
                            item -> item.getWorkingDate() != null && item.getWorkingDate().compareTo(compulatin) >= 0)
                        .collect(Collectors.toList());
                    totalAmountOfB1a = getTotalBalance(ledgerListB1a);
                    bb1NumTranscation = (CommonUtils.isNotEmpty(ledgerListB1a) ? new BigDecimal(ledgerListB1a.size())
                        : BigDecimal.ZERO);

                    totalOfB1B1a = caculate(caseFeeMainForB1, totalAmountOfB1)
                        .add(caculate(caseFeeMainForB1a, totalAmountOfB1a));
                } catch (Exception e) {
                    log.info("Time formatting error !");
                    throw new BusinessException("Time formatting error !");
                }

            }
        }

        if (DividendConstant.CASE_FEE_TYPE_B1.equals(caseFeeType)) {
            // first line of b1 is the total of ledgers's balance witch before 01/03/2013
            feeDTO.setBb1AssetAmount(totalAmountOfB1);
            feeDTO.setBb1ActualAssetAmount(totalAmountOfB1);
            // second line of b1 is b1a the number of ledgers witch after 01/03/2013
            feeDTO.setBb1NumTranscation(bb1NumTranscation);
            feeDTO.setBb1ActualNumTranscation(bb1NumTranscation);
            feeDTO.setBb1TotalReaFee(totalOfB1B1a);
        } else if (DividendConstant.CASE_FEE_TYPE_BIV2.equals(caseFeeType)) {
            // first line of b1 is the total of ledgers's balance witch before 01/03/2013
            feeDTO.setLbivRealizedAsset(totalAmountOfB1);
            feeDTO.setLbivActualAssetAmount(totalAmountOfB1);
            // second line of b1 is b1a the number of ledgers witch after 01/03/2013
            feeDTO.setLbivNumTranscation(bb1NumTranscation);
            feeDTO.setLbivActualNumTranscation(bb1NumTranscation);
            feeDTO.setLbivRealizationFee(totalOfB1B1a);
        }
        return feeDTO;
    }

    private BigDecimal commonCaculate(String caseFeeTypeName) {
        List<ShrGeneralLedger> ledgerList = getLedgersByCaseFeeType(caseFeeTypeName);
        BigDecimal totalAmount = getTotalBalance(ledgerList);

        // find the calculation type by case fee type
        List<CaseFeeMain> caseFeeMains = (List<CaseFeeMain>)caseFeeMainRepository
            .findAll(CaseFeeMainPredicate.findByCaseFeeTypeNameAndDate(caseFeeTypeName, DateUtils.getCurrentDate()));
        if (CommonUtils.isEmpty(caseFeeMains)) {
            log.info("CaseFeeMain of " + caseFeeTypeName + " is missing !");
            throw new BusinessException("CaseFeeMain of " + caseFeeTypeName + " is missing !");
        }
        return caculate(caseFeeMains.get(0), totalAmount);
    }

    private BigDecimal getTotalBalance(List<ShrGeneralLedger> ledgerList) {
        BigDecimal total = BigDecimal.ZERO;
        if (CommonUtils.isNotEmpty(ledgerList)) {
            for (ShrGeneralLedger shrGeneralLedger : ledgerList) {
                BigDecimal balance = shrGeneralLedger.getBalance();
                if (balance != null) {
                    total = total.add(balance);
                }
            }
        }
        return total;
    }

    private BigDecimal caculate(CaseFeeMain caseFeeMain, BigDecimal totalAmount) {
        switch (caseFeeMain.getCalType()) {
            case DividendConstant.CALCULATION_TYPE_FIX:
                return caseFeeMain.getFeeAmount();
            case DividendConstant.CALCULATION_TYPE_RAT:
                return CommonUtils.multiply(false, totalAmount, caseFeeMain.getPercent());
            case DividendConstant.CALCULATION_TYPE_ADD:
                if (new BigDecimal(caseFeeMain.getValueFrom()).compareTo(totalAmount) <= 0
                    && new BigDecimal(caseFeeMain.getValueTo()).compareTo(totalAmount) > 0) {
                    return CommonUtils.multiply(false, totalAmount, caseFeeMain.getValuePercent());
                }
                break;
            case DividendConstant.CALCULATION_TYPE_SLI:
                BigDecimal scale = totalAmount.setScale(caseFeeMain.getRoundingUnit());
                if (new BigDecimal(scale.intValue()).compareTo(scale) != 0) {
                    scale = new BigDecimal(scale.intValue() + 1);
                }
                return CommonUtils.multiply(false, scale, new BigDecimal(caseFeeMain.getRoundingAmount()));
            default:
                break;
        }
        return totalAmount;
    }

    @Override
    @Transactional(readOnly = true)
    public DividendCalculationDTO searchORFeeComputation(Integer orFeeComputationId) {
        log.info("searchORFeeComputation start orFeeComputationId:" + orFeeComputationId);
        DividendCalculationDTO dto = new DividendCalculationDTO();
        DividendCal dividendCal = dividendCalRepository.findOne(orFeeComputationId);
        if (dividendCal != null) {
            Integer divCalId = dividendCal.getDivCalId();
            dto = dividendCalculationDTOAssembler.toDTO(dividendCal);

            dto.setVcase(CaseDTOAssembler.toDTO(dividendCal.getCaseInfo()));

            Integer caseId = dto.getVcase().getCaseId();
            List<CaseAccountInfo> caseAccountInfos = (List<CaseAccountInfo>)caseAccountInfoRepository
                .findAll(CaseAccountInfoPredicate.findMainAccountByCase(caseId));
            if (CommonUtils.isNotEmpty(caseAccountInfos)) {
                CaseAccountInfo caseAccountInfo = caseAccountInfos.get(0);
                dto.setMainCaseAccount(caseAccountInfoDTOAssembler.toDTO(caseAccountInfo));
                // cash in hand = liquid cash + investment
                dto.setCaseInHand(
                    CommonUtils.add(caseAccountInfo.getLiquidCashAmount(), caseAccountInfo.getInvestmentAmount()));
            }

            List<DivCalCred> list
                = (List<DivCalCred>)divCalCredRepository.findAll(DivCalCredPredicate.findByDivCal(divCalId));
            List<Integer> credTypeIds = null;
            if (CommonUtils.isNotEmpty(list)) {
                credTypeIds = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                List<DividendCalculationCreditorDTO> dividendCalculationCreditors = new ArrayList<>();
                for (DivCalCred calCred : list) {
                    DividendCalculationCreditorDTO dividendCalculationCreditor
                        = dividendCalculationCreditorDTOAssembler.toDTO(calCred);
                    dividendCalculationCreditors.add(dividendCalculationCreditor);
                    credTypeIds.add(dividendCalculationCreditor.getCreditorTypeId());
                    sb.append(dividendCalculationCreditor.getCreditorTypeName()).append(",");
                }
                dto.setDividendCalculationCreditors(dividendCalculationCreditors);
                if (sb != null && sb.length() > 0) {
                    dto.setCreditorTypeStr(sb.toString().substring(0, (sb.length() - 1)));
                }
            }

            String divType = dividendCal.getDivType();

            List<DivCalOfDiv> divCalOfDivs
                = divCalOfDivRepository.findByDivCalIdAndStatus(divCalId, CoreConstant.STATUS_ACTIVE);
            if (CommonUtils.isNotEmpty(divCalOfDivs)) {
                CalculationOfDividendFeeDTO feeDTO = calculationOfDividendFeeDTOAssembler.toDTO(divCalOfDivs.get(0));
                if (feeDTO != null) {
                    Case caseInfo = dividendCal.getCaseInfo();
                    String caseFeeTypeName = null;
                    if (caseInfo != null && CoreConstant.STATUS_ACTIVE.equals(caseInfo.getStatus())) {
                        CaseType caseType = caseInfo.getCaseType();
                        if (caseType != null) {
                            switch (caseType.getCaseTypeCode()) {
                                case DividendConstant.CASE_TYPE_B:
                                    caseFeeTypeName = DividendConstant.DETAIL_OF_CASE_FEE_TYPE_B;
                                    break;
                                case DividendConstant.CASE_TYPE_L:
                                    caseFeeTypeName = DividendConstant.DETAIL_OF_CASE_FEE_TYPE_L;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    feeDTO.setCaseFeeTypeName(caseFeeTypeName);
                    List<DivCaseFeeCal> divCaseFeeCals = (List<DivCaseFeeCal>)divCaseFeeCalRepository
                        .findAll(DivCaseFeeCalPredicate.exists(divCalId, caseFeeTypeName));
                    feeDTO.setCaseFeeForDividendCalculations(
                        caseFeeForDividendCalculationDTOAssembler.toDTOList(divCaseFeeCals));

                    // B3
                    Integer latestDivCalId = null;
                    DividendCal latestDividendCal = getLatestFeeComputation(dto.getVcase().getCaseId(), dividendCal);
                    if (latestDividendCal != null) {
                        latestDivCalId = latestDividendCal.getDivCalId();
                    }
                    // show latest info
                    setDistributedToCreditors(latestDivCalId, divType, feeDTO);

                    // calculate for this time
                    BigDecimal bb3PrevDebeDistFee = BigDecimal.ZERO;
                    Map<String, BigDecimal> map = distributeAmountByAdjType(divCalId, divType);
                    if (CommonUtils.isNotEmpty(map)) {
                        // total amount * paymentInterestRate
                        BigDecimal paymentInterestRate = getPaymentInterestRate();
                        for (String adjType : map.keySet()) {
                            bb3PrevDebeDistFee = bb3PrevDebeDistFee.add(map.get(adjType));
                        }
                        bb3PrevDebeDistFee = CommonUtils.multiply(false, bb3PrevDebeDistFee, paymentInterestRate);
                    }
                    feeDTO.setBb3PrevDebeDistFee(bb3PrevDebeDistFee);

                    dto.setCalculationOfDividendFee(feeDTO);
                }
            }
            List<DivProvision> divProvisions
                = divProvisionRepository.findByDivCalIdAndStatus(divCalId, CoreConstant.STATUS_ACTIVE);
            if (CommonUtils.isNotEmpty(divProvisions)) {
                dto.setProvisions(provisionsDTOAssembler.toDTO(divProvisions.get(0)));
            }
            List<DivCalCredDist> divCalCredDists
                = divCalCredDistRepository.findByDivCalIdAndStatus(divCalId, CoreConstant.STATUS_ACTIVE);
            if (CommonUtils.isNotEmpty(divCalCredDists)) {
                Map<String, List<AppAdjResultItem>> map
                    = getAppAdjResultItemsByAdjType(dividendCal.getCaseInfo().getCaseId(), credTypeIds);

                List<CalculatedCreditorDividendDistributionDTO> calculatedCreditorDividendDistributions
                    = new ArrayList<>();
                divCalCredDists.stream().forEach(item -> {
                    CalculatedCreditorDividendDistributionDTO distributionDTO
                        = calculatedCreditorDividendDistributionDTOAssembler.toDTO(item);
                    BigDecimal totalAmountPaid = BigDecimal.ZERO;
                    List<AppAdjResultItem> appAdjResultItems = map.get(item.getAdjType().getAdjTypeDesc());
                    if (CommonUtils.isNotEmpty(appAdjResultItems)) {
                        for (AppAdjResultItem appRslt : appAdjResultItems) {
                            BigDecimal amountPaid = appRslt.getAmountPaid();
                            if (amountPaid != null) {
                                totalAmountPaid = totalAmountPaid.add(amountPaid);
                            }
                        }
                    }
                    distributionDTO.setAmountPaid(totalAmountPaid);
                    calculatedCreditorDividendDistributions.add(distributionDTO);
                });
                dto.setCalculatedCreditorDividendDistributions(calculatedCreditorDividendDistributions);
            } else {
                log.info("error data!");
                throw new BusinessException("error data!");
            }
            Integer workFlowId = dividendCal.getWorkFlowId();
            if (workFlowId != null && workFlowId.intValue() > 0) {
                List<SysApprovalWf> sysApprovalWfs
                    = sysApprovalWFRepository.findByWorkflowIdAndStatusIgnoreCaseOrderByApprovalWorkflowId(workFlowId,
                        CoreConstant.STATUS_ACTIVE);
                List<ApproveHistoryDTO> histories = approveHistoryDTOAssembler.toDTOList(sysApprovalWfs);
                dto.setApproveHistories(histories);
            }

            // TODO only edit mode needs these
            dto.setDistributions(getAppAdjResultItemsByCase(caseId));
        }
        log.info("searchORFeeComputation end return:" + dto);
        return dto;
    }

    private BigDecimal getPaymentInterestRate() {
        List<DivParameter> divParameters = divParameterRepository.findByStatusIgnoreCase(CoreConstant.STATUS_ACTIVE);
        if (CommonUtils.isEmpty(divParameters)) {
            // throw new Exception("miss dividend parameter");
            return null;
        }
        return divParameters.get(0).getPaymentInterestRate();
    }

    private void setDistributedToCreditors(Integer divCalId, String paymentType, CalculationOfDividendFeeDTO feeDTO) {
        List<DistributedAmountDTO> distributedAmounts = new ArrayList<DistributedAmountDTO>();
        Map<String, BigDecimal> map = distributeAmountByAdjType(divCalId, paymentType);
        if (CommonUtils.isNotEmpty(map)) {
            // total amount * paymentInterestRate
            BigDecimal distributedToCreditors = BigDecimal.ZERO;
            List<CaseFeeMain> caseFeeMains = (List<CaseFeeMain>)caseFeeMainRepository.findAll(CaseFeeMainPredicate
                .findByCaseFeeTypeNameAndDate(DividendConstant.CASE_FEE_TYPE_B3, DateUtils.getCurrentDate()));
            if (CommonUtils.isEmpty(caseFeeMains)) {
                log.info("CaseFeeMain of " + DividendConstant.CASE_FEE_TYPE_B3 + " is missing !");
                throw new BusinessException("CaseFeeMain of " + DividendConstant.CASE_FEE_TYPE_B3 + " is missing !");
            }
            for (String adjType : map.keySet()) {
                DistributedAmountDTO distributionAmount = new DistributedAmountDTO();
                distributionAmount.setDistribution(adjType);
                BigDecimal amount = map.get(adjType);
                distributionAmount.setAmount(amount);
                distributionAmount.setCharged(caculate(caseFeeMains.get(0), amount));
                distributedToCreditors = distributedToCreditors.add(distributionAmount.getCharged());
                distributedAmounts.add(distributionAmount);
            }
            feeDTO.setDistributedToCreditors(distributedToCreditors);
        }
        feeDTO.setDistributedAmounts(distributedAmounts);
    }

    private Map<String, BigDecimal> distributeAmountByAdjType(Integer divCalId, String paymentType) {
        Map<String, BigDecimal> map = new HashMap<>();
        List<AdjudicationTypeDTO> adjudicationTypes = commonService.searchAdjTypeList();
        if (!DividendConstant.DIVIDEND_TYPE_INTEREST.equals(paymentType)) {
            adjudicationTypes = adjudicationTypes.stream()
                .filter(item -> !DividendConstant.ADJTYPE_INT.equals(item.getAdjudicationTypeName()))
                .collect(Collectors.toList());
        }
        adjudicationTypes.stream().forEach(item -> {
            // to calculate mount by dividend schedule
            BigDecimal totalAmountOfAdjType = BigDecimal.ZERO;
            if (divCalId != null && divCalId.intValue() > 0) {
                // TODO need to get confirmed schedule? find dividend schedule by div cal id
                List<DivSchedule> divSchedules
                    = (List<DivSchedule>)divScheduleRepository.findAll(DivSchedulePredicate.findByDivCal(divCalId));
                if (CommonUtils.isNotEmpty(divSchedules)) {
                    List<DivScheduleItem> divScheduleItems = new ArrayList<>();
                    for (DivSchedule divSchedule : divSchedules) {
                        divScheduleItems.addAll((List<DivScheduleItem>)divScheduleItemRepository
                            .findAll(DivScheduleItemPredicate.findByDivScheduleId(divSchedule.getDivSchdId())));
                    }
                    // use dividend schedule item adjudication distribution to connect approved adjudication result
                    // item, and get adjType from approved adjudication result item to calculate distribute amount of
                    // dividend schedule item
                    if (CommonUtils.isNotEmpty(divScheduleItems)) {
                        for (DivScheduleItem divScheduleItem : divScheduleItems) {
                            List<DivScheduleDist> dists = (List<DivScheduleDist>)divScheduleDistRepository
                                .findAll(DivScheduleDistPredicate.existByDivScheduleItemAndAdjType(
                                    divScheduleItem.getDivScheduleItemId(), item.getAdjudicationTypeId()));
                            BigDecimal distAmount = divScheduleItem.getDistAmount();
                            if (CommonUtils.isNotEmpty(dists) && distAmount != null) {
                                totalAmountOfAdjType = totalAmountOfAdjType.add(distAmount);
                            }
                        }
                    }
                }
            }
            map.put(item.getAdjudicationTypeName(), totalAmountOfAdjType);
        });
        return map;
    }

    public List<CalculatedCreditorDividendDistributionDTO> getDivCalCredDists(String divType) {
        List<CalculatedCreditorDividendDistributionDTO> divCalCredDistsList
            = new ArrayList<CalculatedCreditorDividendDistributionDTO>();
        List<AdjudicationTypeDTO> adjudicationTypes = commonService.searchAdjTypeList();
        if (DividendConstant.DIVIDEND_TYPE_DIVIDEND.equals(divType)) {
            // show four lines in Distribution:Preferential Payment/Deferred Preferential Payment/Ordinary
            // Dividend/Deferred Ordinary Dividend
            if (CommonUtils.isNotEmpty(adjudicationTypes)) {
                adjudicationTypes = adjudicationTypes.stream()
                    .filter(item -> !DividendConstant.ADJTYPE_INT.equals(item.getAdjudicationTypeName()))
                    .collect(Collectors.toList());
                adjudicationTypes.stream().forEach(item -> {
                    divCalCredDistsList.add(createCalculatedCreditorDividendDistributionDTO(item));
                });
            }
        } else if (DividendConstant.DIVIDEND_TYPE_INTEREST.equals(divType)) {
            // show one line in Distribution:Interest Payment
            AdjudicationTypeDTO adjudicationType = adjudicationTypes.stream()
                .filter(item -> DividendConstant.ADJTYPE_INT.equals(item.getAdjudicationTypeName())).findFirst().get();
            divCalCredDistsList.add(createCalculatedCreditorDividendDistributionDTO(adjudicationType));
        }
        return divCalCredDistsList;
    }

    private CalculatedCreditorDividendDistributionDTO
        createCalculatedCreditorDividendDistributionDTO(AdjudicationTypeDTO adjudicationType) {
        CalculatedCreditorDividendDistributionDTO distribution = new CalculatedCreditorDividendDistributionDTO();
        distribution.setAdjudicationType(adjudicationType);
        distribution.setStatus(CoreConstant.STATUS_ACTIVE);
        distribution.setDistAmount(BigDecimal.ZERO);
        distribution.setPercent(BigDecimal.ZERO);
        return distribution;
    }

    private Map<Integer, Map<String, ApprovedAdjucationResultItemDTO>> getAppAdjResultItemsByCase(Integer caseId) {
        // appAdjResultItem map, key is adjTypeDesc, value item
        Map<Integer, Map<String, ApprovedAdjucationResultItemDTO>> map = new HashMap<>();
        List<AdjResult> approvedAdjList
            = (List<AdjResult>)adjResultRepository.findAll(AdjResultPredicate.findApprovedAdjByCase(caseId));
        if (CommonUtils.isNotEmpty(approvedAdjList)) {
            for (AdjResult adjResult : approvedAdjList) {
                Integer adjRsltId = adjResult.getAdjResultId();
                CredType creditorType = adjResult.getCreditorType();
                if (creditorType != null) {
                    Integer creditorTypeId = creditorType.getCreditorTypeId();
                    Map<String, ApprovedAdjucationResultItemDTO> mapByAdjType;
                    if (map.containsKey(creditorTypeId)) {
                        mapByAdjType = map.get(creditorTypeId);
                    } else {
                        mapByAdjType = new HashMap<>();
                    }

                    List<AppAdjResultItem> appAdjResultItems = (List<AppAdjResultItem>)appAdjResultItemRepository
                        .findAll(AppAdjResultItemPredicate.findByAdjResultId(adjRsltId));
                    if (CommonUtils.isNotEmpty(appAdjResultItems)) {
                        for (AppAdjResultItem appAdjResultItem : appAdjResultItems) {
                            ApprovedAdjucationResultItemDTO dto
                                = approvedAdjucationResultItemDTOAssembler.toDTO(appAdjResultItem);
                            String adjTypeDesc = appAdjResultItem.getAdjType().getAdjTypeDesc();
                            mapByAdjType.put(adjTypeDesc, dto);
                        }
                    } else {
                        throw new BusinessException("DB records' error!");
                    }
                    map.put(creditorTypeId, mapByAdjType);
                } else {
                    throw new BusinessException("DB records' error!");
                }
            }
        }
        return map;
    }

    private Map<String, List<AppAdjResultItem>> getAppAdjResultItemsByAdjType(Integer caseId,
        List<Integer> creditorTypeIds) {
        // appAdjResultItem map, key is adjTypeDesc, value item
        Map<String, List<AppAdjResultItem>> map = new HashMap<>();
        if (CommonUtils.isNotEmpty(creditorTypeIds)) {
            for (Integer typeId : creditorTypeIds) {
                List<AdjResult> list = (List<AdjResult>)adjResultRepository
                    .findAll(AdjResultPredicate.findByCaseAndCreditorType(caseId, typeId));
                if (CommonUtils.isNotEmpty(list)) {
                    for (AdjResult adjResult : list) {
                        Integer adjRsltId = adjResult.getAdjResultId();
                        List<AppAdjResultItem> appAdjResultItems = (List<AppAdjResultItem>)appAdjResultItemRepository
                            .findAll(AppAdjResultItemPredicate.findByAdjResultId(adjRsltId));
                        if (CommonUtils.isNotEmpty(appAdjResultItems)) {
                            appAdjResultItems.forEach(item -> {
                                if (item.getAdjType() != null) {
                                    String key = item.getAdjType().getAdjTypeDesc();
                                    if (map.containsKey(key)) {
                                        map.get(key).add(item);
                                    } else {
                                        List<AppAdjResultItem> itemList = new ArrayList<>();
                                        itemList.add(item);
                                        map.put(key, itemList);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateCaseCreatable(ValidateOrFeeComputationDTO validateOrFeeComputationDTO) {
        if (validateOrFeeComputationDTO.isCreateValidation()) {
            return validateSchedule(validateOrFeeComputationDTO.getCaseId());
        } else {
            List<Integer> credTypeIds = validateOrFeeComputationDTO.getCredTypes();
            if (CommonUtils.isNotEmpty(credTypeIds)) {
                List<DividendCal> dividendCals
                    = (List<DividendCal>)dividendCalRepository.findAll(DividendCalPredicate.existedByCaseAndCalNot(
                        validateOrFeeComputationDTO.getCaseId(), validateOrFeeComputationDTO.getDividendCalId()));
                if (CommonUtils.isNotEmpty(dividendCals)) {
                    for (DividendCal dividendCal : dividendCals) {
                        List<DivCalCred> list = (List<DivCalCred>)divCalCredRepository
                            .findAll(DivCalCredPredicate.findByDivCal(dividendCal.getDivCalId()));
                        if (CommonUtils.isNotEmpty(list)) {
                            for (DivCalCred divCalCred : list) {
                                if (credTypeIds.contains(divCalCred.getCredType().getCreditorTypeId())) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
    }

    /**
     * return false if current case already has OR Fee computation with no schedule or with schedule witch has not
     * confirmed
     * 
     * @param caseId
     * @return
     */
    private boolean validateSchedule(Integer caseId) {
        List<DividendCal> dividendCals = (List<DividendCal>)dividendCalRepository
            .findAll(DividendCalPredicate.existedByCaseAndCalNot(caseId, null));
        if (CommonUtils.isNotEmpty(dividendCals)) {
            // with no schedule
            List<
                DividendCal> withNoSchd
                    = dividendCals.stream()
                        .filter(item -> CommonUtils.isEmpty((List<DivSchedule>)divScheduleRepository
                            .findAll(DivSchedulePredicate.findByDivCal(item.getDivCalId()))))
                        .collect(Collectors.toList());
            if (CommonUtils.isNotEmpty(withNoSchd)) {
                return false;
            }

            // with schedule witch has not confirmed
            for (DividendCal cal : dividendCals) {
                List<DivSchedule> list = (List<DivSchedule>)divScheduleRepository
                    .findAll(DivSchedulePredicate.findByDivCal(cal.getDivCalId()));
                if (CommonUtils.isNotEmpty(list)) {
                    List<DivSchedule> confirmedList
                        = list.stream().filter(schd -> !CoreConstant.SCHEDULE_STATUS_CONFIRMED.equals(schd.getStatus()))
                            .collect(Collectors.toList());
                    if (CommonUtils.isNotEmpty(confirmedList)) {
                        return false;
                    }
                }
            } ;
        }
        return true;
    }

    // to get all case fee maintenance from db , when date of gazette changed , user can get fee from the list
    @Override
    @Transactional(readOnly = true)
    public List<CaseFeeMaintenanceDTO> findCaseFeeMainsByType(String caseFeeType) {
        log.info("findCaseFeeMains start");
        List<CaseFeeMaintenanceDTO> list = null;
        List<CaseFeeMain> caseFeeMains = (List<CaseFeeMain>)caseFeeMainRepository
            .findAll(CaseFeeMainPredicate.findByCaseFeeTypeNameAndDate(caseFeeType, null));
        if (CommonUtils.isNotEmpty(caseFeeMains)) {
            list = caseFeeMaintenanceDTOAssembler.toDTOList(caseFeeMains);
        }
        log.info("findCaseFeeMains end return:" + list);
        return list;
    }
}
