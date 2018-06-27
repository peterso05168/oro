/**
 * 
 */
package hk.oro.iefas.domain.shroff.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2685 $ $Date: 2018-05-29 15:31:22 +0800 (週二, 29 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalVoucherSearchVO {

    private String voucherNumber;
    private Integer journalTypeId;
    private Date voucherDate;
    private String groupCode;
    private String caseType;
    private String accountType;
    private String caseNumber;
    private String caseYear;
    private String analysisCode;
    private Integer statusId;
    private String status;

}
