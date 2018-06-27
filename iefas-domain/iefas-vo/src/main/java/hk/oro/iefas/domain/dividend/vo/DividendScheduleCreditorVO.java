package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DividendScheduleCreditorVO {
    private Integer scheduleCredId;
    private BigDecimal divScheduledId;
    private String remark;
    private String status;
    private CreditorTypeVO adjCredType;
    //----------------Page Param-----------------
    private String[] selectValues;
    private String[] selectFirstGroupValues;
    private String[] selectSecondGroupValues;
}
