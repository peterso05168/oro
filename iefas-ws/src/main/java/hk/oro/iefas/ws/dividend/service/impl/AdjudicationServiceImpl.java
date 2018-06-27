package hk.oro.iefas.ws.dividend.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import hk.oro.iefas.core.constant.WorkFlowAction;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjAccNumber;
import hk.oro.iefas.domain.adjudication.entity.AdjApplyItem;
import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.adjudication.entity.AdjResultGro;
import hk.oro.iefas.domain.adjudication.entity.AdjResultItem;
import hk.oro.iefas.domain.adjudication.entity.AdjType;
import hk.oro.iefas.domain.adjudication.entity.AppAdjResultItem;
import hk.oro.iefas.domain.adjudication.entity.PreAdjResultGro;
import hk.oro.iefas.domain.adjudication.entity.PreAdjResultItem;
import hk.oro.iefas.domain.bank.dto.CurrencyBasicInfoDTO;
import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.casemgt.entity.Address;
import hk.oro.iefas.domain.casemgt.entity.CaseCred;
import hk.oro.iefas.domain.common.dto.ApplicationCodeTableDTO;
import hk.oro.iefas.domain.counter.entity.CtrProofDebtItem;
import hk.oro.iefas.domain.dividend.dto.AdjucationAccountDTO;
import hk.oro.iefas.domain.dividend.dto.AdjucationApplyItemDTO;
import hk.oro.iefas.domain.dividend.dto.AdjucationDTO;
import hk.oro.iefas.domain.dividend.dto.AdjucationGroundDTO;
import hk.oro.iefas.domain.dividend.dto.AdjucationItemDTO;
import hk.oro.iefas.domain.dividend.dto.AdjucationResultDTO;
import hk.oro.iefas.domain.dividend.dto.ApproveHistoryDTO;
import hk.oro.iefas.domain.dividend.dto.PreAdjucationItemDTO;
import hk.oro.iefas.domain.dividend.dto.SearchAdjudicationCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.system.entity.SysApprovalWf;
import hk.oro.iefas.ws.adjudication.repository.AdjAccNumberRepository;
import hk.oro.iefas.ws.adjudication.repository.AdjApplyItemRepository;
import hk.oro.iefas.ws.adjudication.repository.AdjResultGroRepository;
import hk.oro.iefas.ws.adjudication.repository.AdjResultItemRepository;
import hk.oro.iefas.ws.adjudication.repository.AdjResultRepository;
import hk.oro.iefas.ws.adjudication.repository.AppAdjResultItemRepository;
import hk.oro.iefas.ws.adjudication.repository.CredTypeRepository;
import hk.oro.iefas.ws.adjudication.repository.GroundCodeRepository;
import hk.oro.iefas.ws.adjudication.repository.PreAdjResultGroRepository;
import hk.oro.iefas.ws.adjudication.repository.PreAdjResultItemRepository;
import hk.oro.iefas.ws.adjudication.repository.assembler.AdjucationDTOAssembler;
import hk.oro.iefas.ws.adjudication.repository.assembler.AdjucationResultDTOAssembler;
import hk.oro.iefas.ws.adjudication.repository.predicate.AdjApplyItemPredicate;
import hk.oro.iefas.ws.adjudication.repository.predicate.AdjResultPredicate;
import hk.oro.iefas.ws.bank.repository.CurrencyRepository;
import hk.oro.iefas.ws.casemgt.repository.AddressRepository;
import hk.oro.iefas.ws.casemgt.repository.CaseCredRepository;
import hk.oro.iefas.ws.common.service.CommonService;
import hk.oro.iefas.ws.dividend.repository.AdjTypeRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.AdjTypeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.AdjucationApplyItemDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.AdjucationGroundDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.AdjucationItemDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.ApproveHistoryDTOAssembler;
import hk.oro.iefas.ws.dividend.service.AdjudicationService;
import hk.oro.iefas.ws.system.repository.SysApprovalWFRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3274 $ $Date: 2018-06-25 15:52:20 +0800 (週一, 25 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Service
public class AdjudicationServiceImpl implements AdjudicationService {
    @Autowired
    private AdjResultRepository adjResultRepository;

    @Autowired
    private AdjAccNumberRepository adjAccNumberRepository;

    @Autowired
    private AdjApplyItemRepository adjApplyItemRepository;

    @Autowired
    private AdjResultItemRepository adjResultItemRepository;

    @Autowired
    private AdjResultGroRepository adjResultGroRepository;

    @Autowired
    private GroundCodeRepository groundCodeRepository;

    @Autowired
    private CaseCredRepository caseCredRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SysApprovalWFRepository sysApprovalWFRepository;

    @Autowired
    private CredTypeRepository credTypeRepository;

    @Autowired
    private AdjTypeRepository adjTypeRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private AppAdjResultItemRepository appAdjResultItemRepository;

    @Autowired
    private PreAdjResultGroRepository preAdjResultGroRepository;

    @Autowired
    private PreAdjResultItemRepository preAdjResultItemRepository;

    @Autowired
    private AdjucationDTOAssembler adjucationDTOAssembler;

    @Autowired
    private AdjucationResultDTOAssembler adjucationResultDTOAssembler;

    @Autowired
    private AdjucationItemDTOAssembler adjucationItemDTOAssembler;

    @Autowired
    private AdjucationGroundDTOAssembler adjucationGroundDTOAssembler;

    @Autowired
    private ApproveHistoryDTOAssembler approveHistoryDTOAssembler;

    @Autowired
    private AdjTypeDTOAssembler adjTypeDTOAssembler;

    @Autowired
    private AdjucationApplyItemDTOAssembler adjucationApplyItemDTOAssembler;

    @Autowired
    private CommonService commonService;

    @Transactional(readOnly = true)
    @Override
    public Page<AdjucationResultDTO> searchPreAdjudicationList(SearchDTO<SearchAdjudicationCriteriaDTO> criteriaDTO) {
        log.info("searchPreAdjudicationList() start and param : " + criteriaDTO);
        SearchAdjudicationCriteriaDTO criteria = criteriaDTO.getCriteria();
        Pageable pageable = criteriaDTO.getPage().toPageable();
        Page<AdjResult> page = adjResultRepository
            .findAll(AdjResultPredicate.findByCriteria(criteria, DividendConstant.PREFERENTIAL), pageable);
        Page<AdjucationResultDTO> resultDTO = adjucationResultDTOAssembler.toResultDTO(page);
        log.info("searchPreAdjudicationList() end and return : " + resultDTO);
        return resultDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AdjucationResultDTO> searchOrdAdjudicationList(SearchDTO<SearchAdjudicationCriteriaDTO> criteriaDTO) {
        log.info("searchPreAdjudicationList() start and param : " + criteriaDTO);
        SearchAdjudicationCriteriaDTO criteria = criteriaDTO.getCriteria();
        Pageable pageable = criteriaDTO.getPage().toPageable();
        Page<AdjResult> page = adjResultRepository
            .findAll(AdjResultPredicate.findByCriteria(criteria, DividendConstant.ORDINARY), pageable);
        Page<AdjucationResultDTO> resultDTO = adjucationResultDTOAssembler.toResultDTO(page);
        log.info("searchPreAdjudicationList() end and return : " + resultDTO);
        return resultDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveAdjudication(AdjucationDTO dto) throws Exception {
        log.info("saveAdjudication() start and param : " + dto);
        AdjResult entity = DataUtils.copyProperties(dto, AdjResult.class);
        entity.setAdjResultId(dto.getAdjucationId());
        entity.setLocal(dto.getAddressType());

        CreditorDTO creditor = dto.getCreditor();
        Address address = null;
        if (creditor.getAddress() != null) {
            address = DataUtils.copyProperties(creditor.getAddress(), Address.class);
            address.setStatus(CoreConstant.STATUS_ACTIVE);
            address = addressRepository.save(address);
        }
        CaseCred caseCred = DataUtils.copyProperties(creditor, CaseCred.class);
        caseCred.setAddress(address);

        // TODO no need to generate a proof number
        caseCred.setProofNo("proofNo");
        caseCredRepository.save(caseCred);
        entity.setCaseCred(caseCred);

        entity.setCreditorType(credTypeRepository.findOne(dto.getCreditorType().getCreditorTypeId()));

        Integer workFlowId = entity.getWorkFlowId();
        workFlowId = commonService.saveSysApprovalWf(workFlowId, dto.getSysApprovalWf());
        entity.setWorkFlowId(workFlowId);
        adjResultRepository.save(entity);

        Integer adjResultId = entity.getAdjResultId();

        List<AdjucationAccountDTO> adjucationAccounts = dto.getAdjucationAccounts();
        saveAdjucationAccounts(adjResultId, adjucationAccounts);

        if (DividendConstant.PREFERENTIAL.equals(entity.getNatureOfClaim())) {
            // TODO pre datatable
            List<PreAdjucationItemDTO> preAdjucationItems = dto.getPreAdjucationItems();
            if (CommonUtils.isNotEmpty(preAdjucationItems)) {
                BigDecimal zero = BigDecimal.ZERO;
                BigDecimal preferentialTotal = zero;
                BigDecimal ordinaryTotal = zero;
                BigDecimal rejectTotal = zero;
                for (PreAdjucationItemDTO item : preAdjucationItems) {
                    if (!DividendConstant.PAJ_TYPE_TOT.equals(item.getPajTypeCode())) {
                        preferentialTotal = preferentialTotal.add(item.getPreferential());
                        ordinaryTotal = ordinaryTotal.add(item.getOrdinary());
                        rejectTotal = rejectTotal.add(item.getReject());
                    }
                }
                for (PreAdjucationItemDTO totItem : preAdjucationItems) {
                    if (DividendConstant.PAJ_TYPE_TOT.equals(totItem.getPajTypeCode())) {
                        totItem.setPreferential(preferentialTotal);
                        totItem.setOrdinary(ordinaryTotal);
                        totItem.setReject(rejectTotal);
                        break;
                    }
                }

                preAdjucationItems.forEach(item -> {
                    // one pajtype maped one record of PreAdjucationItemDTO
                    // update preferential preAdjResultItem
                    if (item.getPreAdjRsltItemId() != null && item.getPreAdjRsltItemId().intValue() > 0) {
                        PreAdjResultItem preItem = preAdjResultItemRepository.findOne(item.getPreAdjRsltItemId());
                        if (DividendConstant.ADJTYPE_PRE.equals(preItem.getAdjType().getAdjTypeDesc())) {
                            preItem.setResultAmount(item.getPreferential());
                        }
                        preAdjResultItemRepository.save(preItem);
                    } else {
                        // create preferential preAdjResultItem
                        createPreferentialPreAdjResultItem(item, adjResultId);

                    }

                    // update ordinary preAdjResultItem
                    if (item.getPreOrdAdjRsltItemId() != null && item.getPreOrdAdjRsltItemId().intValue() > 0) {
                        PreAdjResultItem ordItem = preAdjResultItemRepository.findOne(item.getPreOrdAdjRsltItemId());
                        if (DividendConstant.ADJTYPE_ORD.equals(ordItem.getAdjType().getAdjTypeDesc())) {
                            ordItem.setResultAmount(item.getOrdinary());
                        }
                        preAdjResultItemRepository.save(ordItem);

                        // inactive
                        List<PreAdjResultGro> preAdjResultGros
                            = preAdjResultGroRepository.findByPreAdjResultItemIdAndStatus(
                                ordItem.getPreAdjResultItemId(), CoreConstant.STATUS_ACTIVE);
                        preAdjResultGros.forEach(gro -> gro.setStatus(CoreConstant.STATUS_INACTIVE));
                        preAdjResultGroRepository.save(preAdjResultGros);
                        String[] ordGroundNos = item.getOrdGroundNos();
                        if (ordGroundNos != null && ordGroundNos.length > 0) {
                            List<PreAdjResultGro> groundNos
                                = getGroundNos(ordGroundNos, ordItem.getPreAdjResultItemId());
                            preAdjResultGroRepository.save(groundNos);
                        }
                    } else {

                        // create ordinary preAdjResultItem
                        createOrdinaryPreAdjResultItem(item, adjResultId);

                    }

                    // update reject preAdjResultItem
                    if (item.getPreRejAdjRsltItemId() != null && item.getPreRejAdjRsltItemId().intValue() > 0) {
                        PreAdjResultItem rejItem = preAdjResultItemRepository.findOne(item.getPreRejAdjRsltItemId());
                        if (DividendConstant.IS_REJECT_Y.equals(rejItem.getIsRejectType())) {
                            rejItem.setResultAmount(item.getReject());
                        }
                        preAdjResultItemRepository.save(rejItem);

                        // inactive
                        List<PreAdjResultGro> preAdjResultGros
                            = preAdjResultGroRepository.findByPreAdjResultItemIdAndStatus(
                                rejItem.getPreAdjResultItemId(), CoreConstant.STATUS_ACTIVE);
                        preAdjResultGros.forEach(gro -> gro.setStatus(CoreConstant.STATUS_INACTIVE));
                        preAdjResultGroRepository.save(preAdjResultGros);
                        String[] rejGroundNos = item.getRejGroundNos();
                        if (rejGroundNos != null && rejGroundNos.length > 0) {
                            List<PreAdjResultGro> groundNos
                                = getGroundNos(rejGroundNos, rejItem.getPreAdjResultItemId());
                            preAdjResultGroRepository.save(groundNos);
                        }
                    } else {

                        // create reject preAdjResultItem
                        createRejectPreAdjResultItem(item, adjResultId);
                    }

                });
            }
        } else {
            List<AdjucationGroundDTO> groundNos = dto.getGroundNos();
            if (CommonUtils.isNotEmpty(groundNos)) {
                for (AdjucationGroundDTO ground : groundNos) {
                    if (ground.getAdjResultGroundId() == null
                        && CoreConstant.STATUS_INACTIVE.equals(ground.getStatus())) {
                        continue;
                    }
                    AdjResultGro adjResultGro = DataUtils.copyProperties(ground, AdjResultGro.class);
                    adjResultGro.setAdjResultId(adjResultId);
                    adjResultGro.setGroundCode(groundCodeRepository.findOne(ground.getGroundCodeId()));
                    adjResultGroRepository.save(adjResultGro);
                }
            }
            List<AdjucationApplyItemDTO> adjucationApplyItems = dto.getAdjucationApplyItems();
            saveAdjucationApplyItems(adjResultId, adjucationApplyItems);
        }

        List<AdjucationItemDTO> adjucationItems = dto.getAdjucationItems();
        saveAdjucationItems(adjResultId, adjucationItems);

        // after approve record , create a new record of Approved Adjudication Result Item
        if (dto.getSysApprovalWf() != null
            && WorkFlowAction.Approve.name().equals(dto.getSysApprovalWf().getAction())) {
            saveAppAdjResultItem(adjResultId, adjucationItems);
        }
        return adjResultId;
    }

    private void createPreferentialPreAdjResultItem(PreAdjucationItemDTO item, Integer adjResultId) {
        // create preferential preAdjResultItem
        PreAdjResultItem preItem = new PreAdjResultItem();
        preItem.setAdjResultId(adjResultId);
        preItem.setAdjType(adjTypeRepository.findByAdjTypeDesc(DividendConstant.ADJTYPE_PRE));
        preItem.setIsRejectType(DividendConstant.IS_REJECT_N);
        preItem.setPreAdjResultType(item.getPajTypeCode());
        preItem.setResultAmount(item.getPreferential());
        preItem.setStatus(CoreConstant.STATUS_ACTIVE);
        preAdjResultItemRepository.save(preItem);
    }

    private void createOrdinaryPreAdjResultItem(PreAdjucationItemDTO item, Integer adjResultId) {
        // create ordinary preAdjResultItem
        PreAdjResultItem ordItem = new PreAdjResultItem();
        ordItem.setAdjResultId(adjResultId);
        ordItem.setAdjType(adjTypeRepository.findByAdjTypeDesc(DividendConstant.ADJTYPE_ORD));
        ordItem.setIsRejectType(DividendConstant.IS_REJECT_N);
        ordItem.setPreAdjResultType(item.getPajTypeCode());
        ordItem.setResultAmount(item.getOrdinary());
        ordItem.setStatus(CoreConstant.STATUS_ACTIVE);
        preAdjResultItemRepository.save(ordItem);
        String[] ordGroundNos = item.getOrdGroundNos();
        if (ordGroundNos != null && ordGroundNos.length > 0) {
            List<PreAdjResultGro> groundNos = getGroundNos(ordGroundNos, ordItem.getPreAdjResultItemId());
            preAdjResultGroRepository.save(groundNos);
        }
    }

    private void createRejectPreAdjResultItem(PreAdjucationItemDTO item, Integer adjResultId) {
        // create reject preAdjResultItem
        PreAdjResultItem rejItem = new PreAdjResultItem();
        rejItem.setAdjResultId(adjResultId);
        rejItem.setIsRejectType(DividendConstant.IS_REJECT_Y);
        rejItem.setPreAdjResultType(item.getPajTypeCode());
        rejItem.setResultAmount(item.getReject());
        rejItem.setStatus(CoreConstant.STATUS_ACTIVE);
        preAdjResultItemRepository.save(rejItem);
        String[] rejGroundNos = item.getRejGroundNos();
        if (rejGroundNos != null && rejGroundNos.length > 0) {
            List<PreAdjResultGro> groundNos = getGroundNos(rejGroundNos, rejItem.getPreAdjResultItemId());
            preAdjResultGroRepository.save(groundNos);
        }
    }

    private List<PreAdjResultGro> getGroundNos(String[] groundNos, Integer preAdjResultItemId) {
        List<PreAdjResultGro> list = new ArrayList<PreAdjResultGro>();
        if (groundNos != null && groundNos.length > 0) {
            for (String groundCodeId : groundNos) {
                PreAdjResultGro gro = new PreAdjResultGro();
                gro.setGroundCode(groundCodeRepository.findOne(Integer.valueOf(groundCodeId)));
                gro.setPreAdjResultItemId(preAdjResultItemId);
                gro.setStatus(CoreConstant.STATUS_ACTIVE);
                list.add(gro);
            }
        }
        return list;
    }

    private void saveAdjucationAccounts(Integer adjResultId, List<AdjucationAccountDTO> adjucationAccounts) {
        if (CommonUtils.isNotEmpty(adjucationAccounts)) {
            for (AdjucationAccountDTO account : adjucationAccounts) {
                if (account.getAdjucationAccountId() == null
                    && CoreConstant.STATUS_INACTIVE.equals(account.getStatus())) {
                    continue;
                }
                AdjAccNumber adjAccNumber = DataUtils.copyProperties(account, AdjAccNumber.class);
                adjAccNumber.setAdjResultId(adjResultId);
                adjAccNumberRepository.save(adjAccNumber);
            }
        }
    }

    private void saveAdjucationApplyItems(Integer adjResultId, List<AdjucationApplyItemDTO> adjucationApplyItems) {
        if (CommonUtils.isNotEmpty(adjucationApplyItems)) {
            for (AdjucationApplyItemDTO applyItem : adjucationApplyItems) {
                if (applyItem.getAdjApplyItemId() == null
                    && CoreConstant.STATUS_INACTIVE.equals(applyItem.getStatus())) {
                    continue;
                }
                AdjApplyItem adjApplyItem = DataUtils.copyProperties(applyItem, AdjApplyItem.class);
                adjApplyItem.setAdjResultId(adjResultId);
                adjApplyItem.setCurrency(currencyRepository.findOne(applyItem.getCurrencyBasicInfo().getCurcyId()));
                adjApplyItemRepository.save(adjApplyItem);
            }
        }
    }

    private void saveAdjucationItems(Integer adjResultId, List<AdjucationItemDTO> adjucationItems) {
        if (CommonUtils.isNotEmpty(adjucationItems)) {
            for (AdjucationItemDTO item : adjucationItems) {
                if (item.getAdjResultItemId() == null && CoreConstant.STATUS_INACTIVE.equals(item.getStatus())) {
                    continue;
                }
                AdjResultItem adjResultItem = DataUtils.copyProperties(item, AdjResultItem.class);
                adjResultItem.setAdjType(adjTypeRepository.findOne(item.getAdjudicationType().getAdjudicationTypeId()));
                adjResultItemRepository.save(adjResultItem);
            }
        }
    }

    private void saveAppAdjResultItem(Integer adjResultId, List<AdjucationItemDTO> adjucationItems) {
        AppAdjResultItem appAdjResultItem = null;
        BigDecimal zero = BigDecimal.ZERO;
        if (CommonUtils.isNotEmpty(adjucationItems)) {
            for (AdjucationItemDTO adjResultItem : adjucationItems) {
                if (CoreConstant.STATUS_ACTIVE.equals(adjResultItem.getStatus())) {
                    appAdjResultItem = new AppAdjResultItem();
                    appAdjResultItem.setStatus(CoreConstant.STATUS_ACTIVE);
                    appAdjResultItem.setAdjResultId(adjResultId);
                    appAdjResultItem
                        .setAdmAmount(adjResultItem.getAdmAmount() != null ? adjResultItem.getAdmAmount() : zero);
                    appAdjResultItem.setAmountPaid(zero);
                    appAdjResultItem.setPercentPaid(zero);
                    if (adjResultItem.getAdjudicationType() != null) {
                        appAdjResultItem.setAdjType(
                            adjTypeRepository.findOne(adjResultItem.getAdjudicationType().getAdjudicationTypeId()));
                    }
                    appAdjResultItemRepository.save(appAdjResultItem);
                }
            }

        }
    }

    @Transactional(readOnly = true)
    @Override
    public AdjucationDTO searchAdjudication(Integer adjucationId) {
        log.info("searchAdjudication() start and adjucationId : " + adjucationId);
        AdjucationDTO dto = null;
        if (adjucationId == null || adjucationId.intValue() <= 0) {
            dto = new AdjucationDTO();
            dto.setPreAdjucationItems(getPreAdjucationItems(null));
        } else {
            AdjResult adjResult = adjResultRepository.findOne(adjucationId);
            if (adjResult != null && adjResult.getAdjResultId() != null && adjResult.getAdjResultId().intValue() > 0) {
                dto = adjucationDTOAssembler.toDTO(adjResult);

                List<AdjApplyItem> adjApplyItems = (List<AdjApplyItem>)adjApplyItemRepository
                    .findAll(AdjApplyItemPredicate.findByAdjResult(adjucationId));
                if (CommonUtils.isNotEmpty(adjApplyItems)) {
                    dto.setAdjucationApplyItems(adjucationApplyItemDTOAssembler.toDTOList(adjApplyItems));
                } else {
                    if (adjResult.getCaseCred() != null && adjResult.getCaseCred().getCtrProofDebt() != null) {
                        List<CtrProofDebtItem> proofDebtItems
                            = adjResult.getCaseCred().getCtrProofDebt().getProofDebtItems();
                        if (CommonUtils.isNotEmpty(proofDebtItems)) {
                            List<CtrProofDebtItem> list = proofDebtItems.stream()
                                .filter(item -> CoreConstant.STATUS_ACTIVE.equals(item.getStatus()))
                                .collect(Collectors.toList());
                            if (CommonUtils.isNotEmpty(list)) {
                                List<AdjucationApplyItemDTO> applyItemList = new ArrayList<>();
                                list.forEach(item -> applyItemList.add(getAdjucationApplyItemDTO(item)));
                                dto.setAdjucationApplyItems(applyItemList);
                            }
                        }
                    }
                }

                List<AdjResultItem> adjResultItems
                    = adjResultItemRepository.findByAdjResultIdAndStatus(adjucationId, CoreConstant.STATUS_ACTIVE);
                if (CommonUtils.isNotEmpty(adjResultItems)) {
                    dto.setAdjucationItems(adjucationItemDTOAssembler.toDTOList(adjResultItems));
                } else {
                    List<AdjucationItemDTO> adjucationItems = new ArrayList<>();
                    String[] adjTypes = new String[] {DividendConstant.ADJTYPE_PRE, DividendConstant.ADJTYPE_ORD,
                        DividendConstant.ADJTYPE_DEF_PRE, DividendConstant.ADJTYPE_DEF_ORD};
                    for (String adjTypeDesc : adjTypes) {
                        AdjType adjType = adjTypeRepository.findByAdjTypeDesc(adjTypeDesc);
                        if (adjType != null && adjType.getAdjTypeId() != null
                            && adjType.getAdjTypeId().intValue() > 0) {
                            AdjucationItemDTO adjucationItem = new AdjucationItemDTO();
                            adjucationItem.setAdjResultId(adjucationId);
                            adjucationItem.setAdjudicationType(adjTypeDTOAssembler.toDTO(adjType));
                            adjucationItem.setStatus(CoreConstant.STATUS_ACTIVE);
                            adjucationItem.setAdmAmount(BigDecimal.ZERO);
                            adjucationItems.add(adjucationItem);
                        }
                    }
                    dto.setAdjucationItems(adjucationItems);
                }

                List<AdjResultGro> adjResultGros
                    = adjResultGroRepository.findByAdjResultIdAndStatus(adjucationId, CoreConstant.STATUS_ACTIVE);
                if (CommonUtils.isNotEmpty(adjResultGros)) {
                    dto.setGroundNos(adjucationGroundDTOAssembler.toDTOList(adjResultGros));
                }
                // Preferential
                List<PreAdjResultItem> preAdjResultItems
                    = preAdjResultItemRepository.findByAdjResultIdAndStatus(adjucationId, CoreConstant.STATUS_ACTIVE);
                dto.setPreAdjucationItems(getPreAdjucationItems(preAdjResultItems));
                Integer workFlowId = adjResult.getWorkFlowId();
                if (workFlowId != null && workFlowId.intValue() > 0) {
                    List<SysApprovalWf> sysApprovalWfs
                        = sysApprovalWFRepository.findByWorkflowIdAndStatusIgnoreCaseOrderByApprovalWorkflowId(
                            workFlowId, CoreConstant.STATUS_ACTIVE);
                    List<ApproveHistoryDTO> histories = approveHistoryDTOAssembler.toDTOList(sysApprovalWfs);
                    dto.setApproveHistories(histories);
                }
            }

        }
        log.info("searchAdjudication() end and return : " + dto);
        return dto;
    }

    private AdjucationApplyItemDTO getAdjucationApplyItemDTO(CtrProofDebtItem item) {
        AdjucationApplyItemDTO itemDTO = new AdjucationApplyItemDTO();
        itemDTO.setAppHkAmount(item.getDebtAmount());
        itemDTO.setCurrencyBasicInfo(new CurrencyBasicInfoDTO());
        itemDTO.getCurrencyBasicInfo().setCurcyId(item.getCurcyId());
        itemDTO.setStatus(CoreConstant.STATUS_ACTIVE);
        return itemDTO;
    }

    private List<PreAdjucationItemDTO> getPreAdjucationItems(List<PreAdjResultItem> items) {
        Map<String, List<PreAdjResultItem>> mapByPreAdjResultType = mapPreAdjResultItemByPreAdjResultType(items);

        List<PreAdjucationItemDTO> preAdjucationItems = new ArrayList<>();
        List<ApplicationCodeTableDTO> pajTypes = commonService.searchPreferentialAdjudicationType();
        if (CommonUtils.isNotEmpty(pajTypes)) {
            pajTypes.forEach(pajType -> preAdjucationItems
                .add(getPreAdjucationItemDTO(pajType, mapByPreAdjResultType.get(pajType.getCodeValue()))));
        }
        return preAdjucationItems;
    }

    private Map<String, List<PreAdjResultItem>> mapPreAdjResultItemByPreAdjResultType(List<PreAdjResultItem> items) {
        Map<String, List<PreAdjResultItem>> map = new HashMap<String, List<PreAdjResultItem>>();
        if (CommonUtils.isNotEmpty(items)) {
            for (PreAdjResultItem preAdjResultItem : items) {
                if (map.containsKey(preAdjResultItem.getPreAdjResultType())) {
                    map.get(preAdjResultItem.getPreAdjResultType()).add(preAdjResultItem);
                } else {
                    List<PreAdjResultItem> mapList = new ArrayList<>();
                    mapList.add(preAdjResultItem);
                    map.put(preAdjResultItem.getPreAdjResultType(), mapList);
                }
            }
        }
        return map;
    }

    private PreAdjucationItemDTO getPreAdjucationItemDTO(ApplicationCodeTableDTO pajType,
        List<PreAdjResultItem> preAdjResultItems) {
        PreAdjucationItemDTO preAdjucationItemDTO = new PreAdjucationItemDTO();
        preAdjucationItemDTO.setPajTypeCode(pajType.getCodeValue());
        preAdjucationItemDTO.setPajTypeDesc(pajType.getCodeDesc());
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal preferential = zero;
        BigDecimal ordinary = zero;
        BigDecimal reject = zero;
        if (CommonUtils.isNotEmpty(preAdjResultItems)) {
            // to save id of ground codes
            StringBuilder ordGroundNos = new StringBuilder();
            StringBuilder rejGroundNos = new StringBuilder();
            // to save code of ground codes
            StringBuilder ordGroundCodes = new StringBuilder();
            StringBuilder rejGroundCodes = new StringBuilder();
            for (PreAdjResultItem preAdjResultItem : preAdjResultItems) {
                if (preAdjResultItem.getAdjType() != null
                    && DividendConstant.ADJTYPE_PRE.equals(preAdjResultItem.getAdjType().getAdjTypeDesc())) {
                    preferential = preferential.add(preAdjResultItem.getResultAmount());
                    preAdjucationItemDTO.setPreAdjRsltItemId(preAdjResultItem.getPreAdjResultItemId());
                }
                if (preAdjResultItem.getAdjType() != null
                    && DividendConstant.ADJTYPE_ORD.equals(preAdjResultItem.getAdjType().getAdjTypeDesc())) {
                    ordinary = ordinary.add(preAdjResultItem.getResultAmount());
                    formGroundNos(preAdjResultItem, ordGroundNos, ordGroundCodes);
                    preAdjucationItemDTO.setPreOrdAdjRsltItemId(preAdjResultItem.getPreAdjResultItemId());
                }
                if (CoreConstant.STATUS_OPTION.equals(preAdjResultItem.getIsRejectType())) {
                    reject = reject.add(preAdjResultItem.getResultAmount());
                    formGroundNos(preAdjResultItem, rejGroundNos, rejGroundCodes);
                    preAdjucationItemDTO.setPreRejAdjRsltItemId(preAdjResultItem.getPreAdjResultItemId());
                }
            }
            if (ordGroundNos.length() > 0) {
                preAdjucationItemDTO.setOrdGroundNos(
                    ordGroundNos.toString().substring(0, (ordGroundNos.toString().length() - 1)).split(","));
            }
            if (ordGroundCodes.length() > 0) {
                preAdjucationItemDTO.setOrdGroundCodes(
                    ordGroundCodes.toString().substring(0, (ordGroundCodes.toString().length() - 1)).split(","));
            }
            if (rejGroundNos.length() > 0) {
                preAdjucationItemDTO.setRejGroundNos(
                    rejGroundNos.toString().substring(0, (rejGroundNos.toString().length() - 1)).split(","));
            }
            if (rejGroundCodes.length() > 0) {
                preAdjucationItemDTO.setRejGroundCodes(
                    rejGroundCodes.toString().substring(0, (rejGroundCodes.toString().length() - 1)).split(","));
            }
        }
        preAdjucationItemDTO.setPreferential(preferential);
        preAdjucationItemDTO.setOrdinary(ordinary);
        preAdjucationItemDTO.setReject(reject);
        return preAdjucationItemDTO;
    }

    private void formGroundNos(PreAdjResultItem preAdjResultItem, StringBuilder groundNos, StringBuilder groundCodes) {
        List<PreAdjResultGro> preAdjResultGros = preAdjResultItem.getPreAdjResultGros();
        if (CommonUtils.isNotEmpty(preAdjResultGros)) {
            List<PreAdjResultGro> list = preAdjResultGros.stream()
                .filter(gro -> CoreConstant.STATUS_ACTIVE.equals(gro.getStatus())).collect(Collectors.toList());
            if (CommonUtils.isNotEmpty(list)) {
                list.forEach(gro -> {
                    if (gro.getGroundCode() != null && gro.getGroundCode().getGroundCodeId() != null
                        && gro.getGroundCode().getGroundCodeId().intValue() > 0) {
                        groundNos.append(gro.getGroundCode().getGroundCodeId()).append(",");
                        groundCodes.append(gro.getGroundCode().getGroundCodeCode()).append(",");
                    }
                });
            }
        }
    }
}
