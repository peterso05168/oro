/**
 * 
 */
package hk.oro.iefas.domain.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageParamVO {

    private Integer errorMessageParamId;
    private String paramCode;
    private String paramContent;
}
