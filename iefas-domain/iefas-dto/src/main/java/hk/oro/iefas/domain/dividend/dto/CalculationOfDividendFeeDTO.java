package hk.oro.iefas.domain.dividend.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2981 $ $Date: 2018-06-07 14:13:44 +0800 (週四, 07 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CalculationOfDividendFeeDTO extends TxnDTO {
    private Integer calOfDivFeeId;
    private BigDecimal alreadyChargedOrFee;
    private BigDecimal bb1ActualAssetAmount;
    private BigDecimal bb1ActualNumTranscation;
    private BigDecimal bb1AssetAmount;
    private BigDecimal bb1NumTranscation;
    private BigDecimal bb1TotalReaFee;
    private BigDecimal bb2AprovedCompo;
    private BigDecimal bb2UnaprpvedCompo;
    private BigDecimal bb3PrevDebeDistFee;

    // not db field
    private BigDecimal distributedToCreditors;

    private Date bb4EndInterimRec;
    private BigDecimal bb4OrderAndPeriodChgs;
    private Date bb4StartInterimRec;
    private BigDecimal bb5NumberOfCreditor;
    private BigDecimal bb5NumberOfDedtor;
    private BigDecimal bb5ParticipantsCharged;
    private BigDecimal bb5ParticipantsUncharged;
    private BigDecimal bb5TotalParticipants;

    private BigDecimal bb5StatFeeCharged;
    private BigDecimal bb5StatFeeUncharged;

    private BigDecimal bb6MeetingFee;
    private BigDecimal bb9ActualAssets;
    private BigDecimal bb9ActualAssetsCharged;
    private BigDecimal bb9Assets;
    private BigDecimal bb9AssetsCharged;
    private BigDecimal bb9AssetsUncharged;
    private BigDecimal lbiAdvoilCharged;
    private BigDecimal lbiAdvoilUncharged;
    private BigDecimal lbiAssets;
    private BigDecimal lbiAssetsCharged;
    private BigDecimal lbiAssetsUncharged;

    private BigDecimal lbiActualAssets;
    private BigDecimal lbiActualAssetsCharged;

    private BigDecimal lbiiPrevLiquFee;
    private BigDecimal lbiiiSpecManFee;
    private BigDecimal lbivCredNumber;
    private BigDecimal lbivDebtNumber;
    private BigDecimal lbivMembers;
    private BigDecimal lbivNumTranscation;
    private BigDecimal lbivParticipantsCharged;
    private BigDecimal lbivParticipantsUncharged;
    private BigDecimal lbivRealizationFee;
    private BigDecimal lbivRealizedAsset;
    private BigDecimal lbivStatFeeCharged;
    private BigDecimal lbivStatFeeUncharged;
    private BigDecimal lbivTotalParticipants;

    private BigDecimal lbivActualAssetAmount;
    private BigDecimal lbivActualNumTranscation;

    private BigDecimal lbvDebeDistFee;
    private BigDecimal lbvDebeHolder;
    private BigDecimal lbvDebeRealiz;
    private BigDecimal lbviSecuCredFee;
    private BigDecimal lbviiPrevSpecDutiFee;
    private BigDecimal minOrFee;
    private Boolean minOrFeeDisabled;
    private BigDecimal othersAmount;
    private String othersItem;

    private BigDecimal calculatedOrFee;

    private List<CaseFeeForDividendCalculationDTO> caseFeeForDividendCalculations;
    private String caseFeeTypeName;
    private List<DistributedAmountDTO> distributedAmounts;
    private String status;

}
