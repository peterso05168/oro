/**
 * 
 */
package hk.oro.iefas.core.constant;

import lombok.Getter;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public enum EmailTemplateEnum {
    SECURITY_FORGOTPWD("SECURITY", "FORGOTPWD");

    @Getter
    private String moduleCode;

    @Getter
    private String subModuleCode;

    private EmailTemplateEnum(String moduleCode, String subModuleCode) {
        this.moduleCode = moduleCode;
        this.subModuleCode = subModuleCode;
    }

}
