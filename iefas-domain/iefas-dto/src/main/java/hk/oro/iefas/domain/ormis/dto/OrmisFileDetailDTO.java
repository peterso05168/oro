package hk.oro.iefas.domain.ormis.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrmisFileDetailDTO {

    private String caseType;
    
    private Integer caseNo;
    
    private Integer caseYear;
    
    private String caseName;
    
    private String statusUpdateDateStr;
    
    private String staffCode;
    
    private String postOfCaseOfficer;
    
    private String orderMadeDateStr;
    
    private String firmName;
    
    private String entryStartDateStr;
    
    private String entryEndDateStr;
    
    private String delimiter;
    
    private BigDecimal transactionAmount;
    
    private String status;
    
    private String caseStatus;
}
