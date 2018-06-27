package hk.oro.iefas.domain.dividend.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DividendScheduleCreditorDTO {
    private Integer scheduleCredId;
    private BigDecimal divScheduledId;
    private String remark;
    private String status;
    private CreditorTypeDTO adjCredType;
}
