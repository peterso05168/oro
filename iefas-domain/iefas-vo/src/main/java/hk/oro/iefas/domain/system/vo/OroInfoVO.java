/**
 * 
 */
package hk.oro.iefas.domain.system.vo;

import java.util.Date;

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
@AllArgsConstructor
@NoArgsConstructor
public class OroInfoVO extends TxnVO {

    private Integer oroInfoId;

    private String nameOfInCharge;

    private String orgName;

    private Date periodFrom;

    private Date periodTo;

    private String titleOfInCharge;

    private Integer versionNo;

    private String status;

    // private CaseAddress caseAddress;
}
