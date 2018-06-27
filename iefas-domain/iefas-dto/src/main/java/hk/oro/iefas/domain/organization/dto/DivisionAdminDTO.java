package hk.oro.iefas.domain.organization.dto;

import javax.validation.constraints.Size;

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
public class DivisionAdminDTO extends TxnDTO {

    private Integer divisionAdminId;

    private DivisionDTO division;

    private PostDTO post;

    @Size(max = 300)
    private String remark;

    @Size(max = 3)
    private String status;

}
