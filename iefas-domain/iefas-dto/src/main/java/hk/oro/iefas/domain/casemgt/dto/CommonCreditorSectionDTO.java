package hk.oro.iefas.domain.casemgt.dto;

import hk.oro.iefas.domain.core.dto.TxnDTO;
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
public class CommonCreditorSectionDTO extends TxnDTO {
    private Integer commonCreditorSectionId;
    private String sectionName;
    private String sectionNameChinese;
    private String sectionSeqNo;
    private AddressDTO address;
    private String contactNo;
    private String contactPerson;
    private String payeeName;
    private String payeeNameChinese;
    private String sectionFax;
    private String emailAddress;
    private String sectionAccountNumber;
    private String reference;
    private String status;
}