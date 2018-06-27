package hk.oro.iefas.domain.release.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (Mon, 09 Apr 2018) $
 * @author $Author: marvel.ma $
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RelHistListItemDTO extends TxnDTO {
	
    private Integer histCaseListItemId;

    private String caseName;

    private String caseNo;

    private String caseOfficer;

    private String caseOfficerTeam;

    private String caseType;

    private String caseYear;

    private BigDecimal cashPositionAmount;

    private BigDecimal cashPositionAmount2;

    private Date cashPositionDate;

    private Date cashPositionDate2;

    private Integer enclNo;

    private String fileName;

    private Integer fileNo;

    private String fromIo;

    private Integer histCaseListId;

    private Integer histCaseListItemNo;

    private Date lastTransactionDate;

    private Date memoDate;

    private BigDecimal momoAmount;

    private String olAgent;

    private Date processDate;

    private Date releaseOrderDate;

    private String remarks;

    private String remarks2;

    private String remarks3;

    private String status;

    private Date toGr;

    private Date windingUpOrderDate;

}
