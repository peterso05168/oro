package hk.oro.iefas.core.exceptions.support;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@RequiredArgsConstructor
public class ErrorMsg {

    private String code;
    private String[] fields;

    public ErrorMsg(String code, String... fields) {
        this.code = code;
        this.fields = fields;
    }

}
