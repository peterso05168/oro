/**
 * 
 */
package hk.oro.iefas.core.constant;

import lombok.Getter;

/**
 * @version $Revision: 2919 $ $Date: 2018-06-05 20:15:27 +0800 (週二, 05 六月 2018) $
 * @author $Author: marvel.ma $
 */
public enum GroupCodeEnum {
    B("BANKRUPTCY"), L("LIQUIDATION"), E("BANKRUPTCY ESTATES (BEA)"), N("NHBANK(CI) US$"), W("NHBANK(CI)NY US$"),
    A("IVA"), Y("JAPANESE YEN"), U("LIQUIDATION US$");

    @Getter
    private String desc;

    private GroupCodeEnum(String desc) {
        this.desc = desc;
    }

}
