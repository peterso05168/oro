package hk.oro.iefas.domain.report;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReportParamVO extends TxnVO {

    private Integer reportSessionId;

    private String reportSessionStatus;

    private String reportFormat;

    private String caseTypeFrom;

    private String caseNoFrom;

    private String caseYearFrom;

    private String caseTypeTo;

    private String caseNoTo;

    private String caseYearTo;

    private Date dateFrom;

    private Date dateTo;

    private Date dateReference1;

    private Date dateReference2;

    private Date dateReference3;

    private Date dateReference4;

    private BigDecimal estateBalanceFrom;

    private BigDecimal estateBalanceTo;

    private BigDecimal cashBalanceFrom;

    private BigDecimal cashBalanceTo;

    private BigDecimal investmentBalanceFrom;

    private BigDecimal investmentBalanceTo;

    private BigDecimal amountReference1;

    private BigDecimal amountReference2;

    private BigDecimal amountReference3;

    private BigDecimal amountReference4;

    private BigDecimal amountReference5;

    private String referenceFrom;

    private String referenceTo;

    private String reference1;

    private String reference2;

    private String reference3;

    private String reference4;

    private String reference5;

    private BigDecimal numericFrom;

    private BigDecimal numericTo;

    private BigDecimal analysisCodebalFrom;

    private BigDecimal analysisCodebalTo;

    private String invStaPlaced;

    private String invStaUplifted;

    private String invStaRenewed;

    private String invStaRolledOver;

    private String isDivAcc;

    private String isBea;

    private String isRev;

    private String isPaid;

    private String isUnpaid;

    private String period;

    private String creditorType;

    private String proofNoStart;

    private String proofNoEnd;

    private String natureOfClaim;

    private String remark;

    private String reportId;
}
