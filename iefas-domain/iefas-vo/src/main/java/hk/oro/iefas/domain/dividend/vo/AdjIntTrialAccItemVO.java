package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AdjIntTrialAccItemVO extends TxnVO {

    private Integer intTrialAccItemId;
    private String accountNumber;
    private BigDecimal admAmount;
    private BigDecimal contractualRate;
    private BigDecimal finalRate;
    private String status;
}
