package hk.oro.iefas.domain.shroff.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2183 $ $Date: 2018-04-24 10:12:28 +0800 (週二, 24 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class VoucherTypeVO {

    private Integer voucherTypeId;
    private String voucherTypeName;
    private String voucherTypeDesc;
    private String voucherTypeCode;
}
