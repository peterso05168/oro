package hk.oro.iefas.domain.casemgt.vo;

import java.util.List;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CommonCreditorVO extends TxnVO {
    private Integer commonCreditorId;
    private String commonCreditorName;
    private String commonCreditorNameChinese;
    private String commonCreditorSeqNo;
    private AddressVO address;
    private String contactNo;
    private String contactPerson;
    private String payeeName;
    private String payeeNameChinese;
    private String reference;
    private List<CommonCreditorSectionVO> commonCreditorSections;
    private String status;
}
