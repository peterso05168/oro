package hk.oro.iefas.domain.shroff.vo;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisCodeVO extends TxnVO {

    private Integer analysisCodeId;
    private String analysisCode;
    private VoucherTypeVO voucherType;
    private AnalysisCodeTypeVO analysisCodeType;
    private String shortName;
    private String fullName;
    private String realizationFee;
    private String status;
}
