package hk.oro.iefas.domain.organization.dto;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import hk.oro.iefas.domain.core.dto.TxnDTO;
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
public class DivisionDTO extends TxnDTO {

    private Integer divisionId;

    @NotBlank
    @Size(max = 100)
    private String divisionName;

    private Integer parentDivisionId;

    @Size(max = 3)
    private String status;

    private List<DivisionDTO> divisions;
}
