package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AdjucationResultIntItemVO extends TxnVO {

    private Integer adjIntResultItemId;
    private String accountNumber;
    private BigDecimal principleAmount;
    private Date fromDate;
    private Date toDate;
    private BigDecimal judgmentRate;
    private BigDecimal interest;
    private String status;
    // --------------- help to calculation ---------------
    private BigDecimal totalDivScheduleAmount;
}