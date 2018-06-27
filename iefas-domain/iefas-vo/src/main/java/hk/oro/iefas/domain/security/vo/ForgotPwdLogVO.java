/**
 * 
 */
package hk.oro.iefas.domain.security.vo;

import java.util.Date;

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
public class ForgotPwdLogVO {
    private Integer forgotPwdLogId;
    private Integer userAcId;
    private String token;
    private Boolean used;
    private Date forgotPwdDate;
}
