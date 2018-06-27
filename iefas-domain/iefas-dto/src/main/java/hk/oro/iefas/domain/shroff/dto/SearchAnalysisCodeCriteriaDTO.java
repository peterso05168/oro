package hk.oro.iefas.domain.shroff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SearchAnalysisCodeCriteriaDTO {

    private Integer analysisCodeId;
    private String analysisCode;
    private Integer voucherTypeId;
    private String voucherTypeCodeValue;
    private Integer analysisCodeTypeId;
    private String shortName;
    private String fullName;
    private String realizationFee;
    private String status;
}
