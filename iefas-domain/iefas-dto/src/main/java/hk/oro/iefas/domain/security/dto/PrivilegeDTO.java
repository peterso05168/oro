package hk.oro.iefas.domain.security.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeDTO {

    private Integer privilegeId;

    @NotBlank
    @Size(max = 100)
    private String privilegeName;

    @NotBlank
    @Size(max = 200)
    private String privilegeDesc;

    @NotBlank
    @Size(max = 3)
    private String privilegeType;

    private String privilegeCode;

}
