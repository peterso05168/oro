package hk.oro.iefas.domain.dividend.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2981 $ $Date: 2018-06-07 14:13:44 +0800 (週四, 07 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Entity
@Table(name = "DIV_CAL_OF_DIV")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivCalOfDiv extends UpdateTxnKeyable {

    @Id
    @Column(name = "CAL_OF_DIV_FEE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CAL_OF_DIV_FEE_SEQ")
    @TableGenerator(name = "CAL_OF_DIV_FEE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CAL_OF_DIV_FEE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer calOfDivFeeId;

    @Column(name = "ALREADY_CHARGED_OR_FEE", precision = 16, scale = 2)
    private BigDecimal alreadyChargedOrFee;

    @Column(name = "B_B1_ACTUAL_ASSET_AMOUNT", precision = 16, scale = 2)
    private BigDecimal bb1ActualAssetAmount;

    @Column(name = "B_B1_ACTUAL_NUM_TRANSCATION", precision = 16, scale = 2)
    private BigDecimal bb1ActualNumTranscation;

    @Column(name = "B_B1_ASSET_AMOUNT", precision = 16, scale = 2)
    private BigDecimal bb1AssetAmount;

    @Column(name = "B_B1_NUM_TRANSCATION", precision = 16, scale = 2)
    private BigDecimal bb1NumTranscation;

    @Column(name = "B_B1_TOTAL_REA_FEE", precision = 16, scale = 2)
    private BigDecimal bb1TotalReaFee;

    @Column(name = "B_B2_APROVED_COMPO", precision = 16, scale = 2)
    private BigDecimal bb2AprovedCompo;

    @Column(name = "B_B2_UNAPRPVED_COMPO", precision = 16, scale = 2)
    private BigDecimal bb2UnaprpvedCompo;

    @Column(name = "B_B3_PREV_DEBE_DIST_FEE", precision = 16, scale = 2)
    private BigDecimal bb3PrevDebeDistFee;

    @Column(name = "B_B4_END_INTERIM_REC")
    private Date bb4EndInterimRec;

    @Column(name = "B_B4_ORDER_AND_PERIOD_CHGS", precision = 16, scale = 2)
    private BigDecimal bb4OrderAndPeriodChgs;

    @Column(name = "B_B4_START_INTERIM_REC")
    private Date bb4StartInterimRec;

    @Column(name = "B_B5_NUMBER_OF_CREDITOR", precision = 16, scale = 2)
    private BigDecimal bb5NumberOfCreditor;

    @Column(name = "B_B5_NUMBER_OF_DEDTOR", precision = 16, scale = 2)
    private BigDecimal bb5NumberOfDedtor;

    @Column(name = "B_B5_PARTICIPANTS_CHARGED", precision = 16, scale = 2)
    private BigDecimal bb5ParticipantsCharged;

    @Column(name = "B_B5_PARTICIPANTS_UNCHARGED", precision = 16, scale = 2)
    private BigDecimal bb5ParticipantsUncharged;

    @Column(name = "B_B5_TOTAL_PARTICIPANTS", precision = 16, scale = 2)
    private BigDecimal bb5TotalParticipants;

    @Column(name = "B_B5_STAT_FEE_CHARGED", precision = 16, scale = 2)
    private BigDecimal bb5StatFeeCharged;

    @Column(name = "B_B5_STAT_FEE_UNCHARGED", precision = 16, scale = 2)
    private BigDecimal bb5StatFeeUncharged;

    @Column(name = "B_B6_MEETING_FEE", precision = 16, scale = 2)
    private BigDecimal bb6MeetingFee;

    @Column(name = "B_B9_ACTUAL_ASSETS", precision = 16, scale = 2)
    private BigDecimal bb9ActualAssets;

    @Column(name = "B_B9_ACTUAL_ASSETS_CHARGED", precision = 16, scale = 2)
    private BigDecimal bb9ActualAssetsCharged;

    @Column(name = "B_B9_ASSETS", precision = 16, scale = 2)
    private BigDecimal bb9Assets;

    @Column(name = "B_B9_ASSETS_CHARGED", precision = 16, scale = 2)
    private BigDecimal bb9AssetsCharged;

    @Column(name = "B_B9_ASSETS_UNCHARGED", precision = 16, scale = 2)
    private BigDecimal bb9AssetsUncharged;

    @Column(name = "L_BI_ADVOIL_CHARGED", precision = 16, scale = 2)
    private BigDecimal lbiAdvoilCharged;

    @Column(name = "L_BI_ADVOIL_UNCHARGED", precision = 16, scale = 2)
    private BigDecimal lbiAdvoilUncharged;

    @Column(name = "L_BI_ASSETS", precision = 16, scale = 2)
    private BigDecimal lbiAssets;

    @Column(name = "L_BI_ASSETS_CHARGED", precision = 16, scale = 2)
    private BigDecimal lbiAssetsCharged;

    @Column(name = "L_BI_ASSETS_UNCHARGED", precision = 16, scale = 2)
    private BigDecimal lbiAssetsUncharged;

    @Column(name = "L_BI_ACTUAL_ASSETS", precision = 16, scale = 2)
    private BigDecimal lbiActualAssets;

    @Column(name = "L_BI_ACTUAL_ASSETS_CHARGED", precision = 16, scale = 2)
    private BigDecimal lbiActualAssetsCharged;

    @Column(name = "L_BII_PREV_LIQU_FEE", precision = 16, scale = 2)
    private BigDecimal lbiiPrevLiquFee;

    @Column(name = "L_BIII_SPEC_MAN_FEE", precision = 16, scale = 2)
    private BigDecimal lbiiiSpecManFee;

    @Column(name = "L_BIV_CRED_NUMBER", precision = 16, scale = 2)
    private BigDecimal lbivCredNumber;

    @Column(name = "L_BIV_DEBT_NUMBER", precision = 16, scale = 2)
    private BigDecimal lbivDebtNumber;

    @Column(name = "L_BIV_MEMBERS", precision = 16, scale = 2)
    private BigDecimal lbivMembers;

    @Column(name = "L_BIV_NUM_TRANSCATION", precision = 16, scale = 2)
    private BigDecimal lbivNumTranscation;

    @Column(name = "L_BIV_PARTICIPANTS_CHARGED", precision = 16, scale = 2)
    private BigDecimal lbivParticipantsCharged;

    @Column(name = "L_BIV_PARTICIPANTS_UNCHARGED", precision = 16, scale = 2)
    private BigDecimal lbivParticipantsUncharged;

    @Column(name = "L_BIV_REALIZATION_FEE", precision = 16, scale = 2)
    private BigDecimal lbivRealizationFee;

    @Column(name = "L_BIV_REALIZED_ASSET", precision = 16, scale = 2)
    private BigDecimal lbivRealizedAsset;

    @Column(name = "L_BIV_STAT_FEE_CHARGED", precision = 16, scale = 2)
    private BigDecimal lbivStatFeeCharged;

    @Column(name = "L_BIV_STAT_FEE_UNCHARGED", precision = 16, scale = 2)
    private BigDecimal lbivStatFeeUncharged;

    @Column(name = "L_BIV_TOTAL_PARTICIPANTS", precision = 16, scale = 2)
    private BigDecimal lbivTotalParticipants;

    @Column(name = "L_BIV_ACTUAL_ASSET_AMOUNT", precision = 16, scale = 2)
    private BigDecimal lbivActualAssetAmount;

    @Column(name = "L_BIV_ACTUAL_NUM_TRANSCATION", precision = 16, scale = 2)
    private BigDecimal lbivActualNumTranscation;

    @Column(name = "L_BV_DEBE_DIST_FEE", precision = 16, scale = 2)
    private BigDecimal lbvDebeDistFee;

    @Column(name = "L_BV_DEBE_HOLDER", precision = 16, scale = 2)
    private BigDecimal lbvDebeHolder;

    @Column(name = "L_BV_DEBE_REALIZ", precision = 16, scale = 2)
    private BigDecimal lbvDebeRealiz;

    @Column(name = "L_BVI_SECU_CRED_FEE", precision = 16, scale = 2)
    private BigDecimal lbviSecuCredFee;

    @Column(name = "L_BVII_PREV_SPEC_DUTI_FEE", precision = 16, scale = 2)
    private BigDecimal lbviiPrevSpecDutiFee;

    @Column(name = "MIN_OR_FEE", precision = 16, scale = 2)
    private BigDecimal minOrFee;

    @Column(name = "OTHERS_AMOUNT", precision = 16, scale = 2)
    private BigDecimal othersAmount;

    @Column(name = "OTHERS_ITEM", length = 100)
    private String othersItem;

    @Column(name = "CALCULATED_OR_FEE", precision = 16, scale = 2)
    private BigDecimal calculatedOrFee;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "DIV_CAL_ID")
    private Integer divCalId;

}