package hk.oro.iefas.ws.adjudication.repository.assembler;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.adjudication.entity.AdjResultGro;
import hk.oro.iefas.domain.adjudication.entity.AdjResultItem;
import hk.oro.iefas.domain.adjudication.entity.PreAdjResultItem;
import hk.oro.iefas.domain.counter.entity.CtrProofDebtItem;
import hk.oro.iefas.domain.dividend.dto.AdjucationResultDTO;
import hk.oro.iefas.domain.dividend.dto.PreferentialAmountDTO;
import hk.oro.iefas.ws.adjudication.repository.PreAdjResultItemRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.CreditorDTOAssembler;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;
import hk.oro.iefas.ws.dividend.repository.assembler.CreditorTypeDTOAssembler;

/**
 * @version $Revision: 3022 $ $Date: 2018-06-10 17:23:43 +0800 (週日, 10 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class AdjucationResultDTOAssembler extends PagingAssemblerSupport<AdjucationResultDTO, AdjResult> {

    @Autowired
    private CreditorTypeDTOAssembler creditorTypeDTOAssembler;

    @Autowired
    private CreditorDTOAssembler creditorDTOAssembler;

    @Autowired
    private PreAdjResultItemRepository preAdjResultItemRepository;

    public AdjucationResultDTO toDTO(AdjResult entity) {
        if (entity != null && entity.getAdjResultId() != null && entity.getAdjResultId().intValue() > 0) {
            AdjucationResultDTO dto = DataUtils.copyProperties(entity, AdjucationResultDTO.class);
            dto.setAdjucationId(entity.getAdjResultId());
            dto.setCreditor(creditorDTOAssembler.toDTO(entity.getCaseCred()));
            dto.setCreditorType(creditorTypeDTOAssembler.toDTO(entity.getCreditorType()));
            dto.setAddressType(entity.getLocal());
            if (DividendConstant.ORDINARY.equals(entity.getNatureOfClaim())) {
                BigDecimal claimed = BigDecimal.ZERO;
                if (entity.getCaseCred() != null && entity.getCaseCred().getCtrProofDebt() != null) {
                    List<CtrProofDebtItem> proofDebtItems = entity.getCaseCred().getCtrProofDebt().getProofDebtItems();
                    if (CommonUtils.isNotEmpty(proofDebtItems)) {
                        List<CtrProofDebtItem> list = proofDebtItems.stream()
                            .filter(item -> CoreConstant.STATUS_ACTIVE.equals(item.getStatus()))
                            .collect(Collectors.toList());
                        if (CommonUtils.isNotEmpty(list)) {
                            for (CtrProofDebtItem item : list) {
                                claimed = claimed.add(item.getDebtAmount());
                            }
                        }
                    }
                    dto.setClaimed(claimed);
                }

                List<AdjResultItem> adjResultItems = entity.getAdjResultItems();
                if (CommonUtils.isNotEmpty(adjResultItems)) {
                    for (AdjResultItem adjResultItem : adjResultItems) {
                        if (CoreConstant.STATUS_ACTIVE.equals(adjResultItem.getStatus())) {
                            switch (adjResultItem.getAdjType().getAdjTypeDesc()) {
                                case DividendConstant.ADJTYPE_PRE:
                                    dto.setPreAdmitted(adjResultItem.getAdmAmount());
                                    break;
                                case DividendConstant.ADJTYPE_ORD:
                                    dto.setOrdAdmitted(adjResultItem.getAdmAmount());
                                    break;
                                case DividendConstant.ADJTYPE_DEF_PRE:
                                    dto.setDefPreAdmitted(adjResultItem.getAdmAmount());
                                    break;
                                case DividendConstant.ADJTYPE_DEF_ORD:
                                    dto.setDefOrdAdmitted(adjResultItem.getAdmAmount());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }

                List<AdjResultGro> adjResultGros = entity.getAdjResultGros();
                if (CommonUtils.isNotEmpty(adjResultGros)) {
                    StringBuilder sb = new StringBuilder();
                    List<AdjResultGro> list
                        = adjResultGros.stream().filter(item -> CoreConstant.STATUS_ACTIVE.equals(item.getStatus()))
                            .collect(Collectors.toList());
                    if (CommonUtils.isNotEmpty(list)) {
                        list.forEach(item -> sb.append(item.getGroundCode().getGroundCodeCode()).append(","));
                    }
                    if (sb.length() > 0) {
                        dto.setGroundNos(sb.toString().substring(0, sb.length() - 1));
                    }
                }
            } else {
                List<PreAdjResultItem> preAdjResultItems = preAdjResultItemRepository
                    .findByAdjResultIdAndStatus(entity.getAdjResultId(), CoreConstant.STATUS_ACTIVE);
                if (CommonUtils.isNotEmpty(preAdjResultItems)) {
                    PreferentialAmountDTO preAmount = new PreferentialAmountDTO();
                    PreferentialAmountDTO ordAmount = new PreferentialAmountDTO();
                    PreferentialAmountDTO rejAmount = new PreferentialAmountDTO();
                    for (PreAdjResultItem preAdjResultItem : preAdjResultItems) {
                        if (preAdjResultItem.getAdjType() != null) {
                            switch (preAdjResultItem.getAdjType().getAdjTypeDesc()) {
                                case DividendConstant.ADJTYPE_PRE:
                                    preAmount = setPreferentialAmountDTO(preAdjResultItem.getPreAdjResultType(),
                                        preAdjResultItem.getResultAmount(), preAmount);
                                    break;
                                case DividendConstant.ADJTYPE_ORD:
                                    ordAmount = setPreferentialAmountDTO(preAdjResultItem.getPreAdjResultType(),
                                        preAdjResultItem.getResultAmount(), ordAmount);
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            rejAmount = setPreferentialAmountDTO(preAdjResultItem.getPreAdjResultType(),
                                preAdjResultItem.getResultAmount(), rejAmount);
                        }
                    }
                    dto.setPreAmount(preAmount);
                    dto.setOrdAmount(ordAmount);
                    dto.setRejAmount(rejAmount);
                }
            }

            return dto;
        }
        return null;
    }

    private PreferentialAmountDTO setPreferentialAmountDTO(String pajType, BigDecimal amount,
        PreferentialAmountDTO preferentialAmountDTO) {
        switch (pajType) {
            case DividendConstant.PAJ_TYPE_SAL:
                preferentialAmountDTO.setSalary(amount);
                break;
            case DividendConstant.PAJ_TYPE_HOL:
                preferentialAmountDTO.setHoliday(amount);
                break;
            case DividendConstant.PAJ_TYPE_WIL:
                preferentialAmountDTO.setWilon(amount);
                break;
            case DividendConstant.PAJ_TYPE_SEV:
                preferentialAmountDTO.setSeverance(amount);
                break;
            case DividendConstant.PAJ_TYPE_OTH:
                preferentialAmountDTO.setOthers(amount);
                break;
            default:
                break;
        }
        return preferentialAmountDTO;
    }
}
