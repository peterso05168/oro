package hk.oro.iefas.domain.dividend.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3112 $ $Date: 2018-06-13 15:48:09 +0800 (週三, 13 六月 2018) $
 * @author $Author: noah.liang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DividendScheduleDistDTO {

    private Integer scheduleDistId;
    private Integer divScheduleItemId;
    private ApprovedAdjucationResultItemDTO appAdjResultItem;
    private BigDecimal amountPaid;
    private BigDecimal percentPaid;
    private String status;
}
