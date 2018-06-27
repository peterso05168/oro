/**
 * 
 */
package hk.oro.iefas.domain.system.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2545 $ $Date: 2018-05-23 17:41:26 +0800 (週三, 23 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplateDTO {

    private String moduleCode;
    private String subModuleCode;
    private String to;
    private Map<String, Object> params;
}
