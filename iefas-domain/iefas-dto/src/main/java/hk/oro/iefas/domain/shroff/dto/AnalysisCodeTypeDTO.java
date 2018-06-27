package hk.oro.iefas.domain.shroff.dto;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisCodeTypeDTO extends TxnDTO {

    private Integer analysisCodeTypeId;
    private String analysisCodeTypeName;
    private String analysisCodeTypeDesc;
    private String status;
}
