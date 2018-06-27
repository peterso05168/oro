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
public class AnalysisCodeTypeVO extends TxnVO {

    private Integer analysisCodeTypeId;
    private String analysisCodeTypeName;
    private String analysisCodeTypeDesc;
    private String status;
}
