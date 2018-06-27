package hk.oro.iefas.domain.casemgt.vo;

import hk.oro.iefas.domain.core.vo.TxnVO;
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
public class AddressVO extends TxnVO {

    private Integer addressId;
    private String address1;
    private String address2;
    private String address3;
    private String chiAddress1;
    private String chiAddress2;
    private String chiAddress3;
    private String status;
}
