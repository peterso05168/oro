package hk.oro.iefas.domain.casemgt.dto;

import java.util.Date;

import hk.oro.iefas.domain.common.dto.OutsiderTypeDTO;
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
public class OutsiderDTO extends TxnDTO {

    private Integer outsiderId;
    private String outsiderName;
    private String outsiderNameShort;
    private String outsiderChiName;
    private String email;
    private String contactPerson;
    private String fax;
    private OutsiderTypeDTO outsiderType;
    private String contactNo;
    private String encryptedPassword;
    private Date lastPasswordChange;
    private AddressDTO address;
    private String status;
}
