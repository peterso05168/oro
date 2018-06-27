package hk.oro.iefas.domain.system.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import hk.oro.iefas.domain.organization.dto.DivisionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDTO extends TxnDTO {

    private Integer holidayId;

    private Date holidayDate;

    @Size(max = 200)
    private String holidayDesc;

    @Size(max = 3)
    private String status;

}
