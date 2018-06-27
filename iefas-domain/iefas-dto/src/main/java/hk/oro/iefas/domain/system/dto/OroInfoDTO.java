/**
 * 
 */
package hk.oro.iefas.domain.system.dto;

import java.util.Date;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2487 $ $Date: 2018-05-18 17:54:15 +0800 (週五, 18 五月 2018) $
 * @author $Author: cwen $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class OroInfoDTO extends TxnDTO {

    private Integer oroInfoId;

    private String nameOfInCharge;

    private String orgName;

    private Date periodFrom;

    private Date periodTo;

    private String titleOfInCharge;

    private String status;

    // private CaseAddress caseAddress;
}
