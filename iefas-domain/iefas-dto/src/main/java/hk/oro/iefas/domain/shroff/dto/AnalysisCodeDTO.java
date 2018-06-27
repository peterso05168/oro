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
public class AnalysisCodeDTO extends TxnDTO {

    private Integer analysisCodeId;
    private String analysisCode;
    private VoucherTypeDTO voucherType;
    private AnalysisCodeTypeDTO analysisCodeType;
    private String shortName;
    private String fullName;
    private String realizationFee;
    private String status;
}
