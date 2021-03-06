package hk.oro.iefas.domain.shroff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SearchAnalysisCodeResultDTO {

    private Integer analysisCodeId;
    private String analysisCode;
    private String voucherType;
    private String analysisCodeType;
    private String shortName;
    private String fullName;
    private String realizationFee;
    private String status;
}
