package hk.oro.iefas.domain.dividend.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
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
public class WilonAndSeverancePayDTO extends TxnDTO {

    private Integer wilonAndSevrnPayId;
    private CaseDTO caseInfo;
    private CreditorDTO caseCred;
    private ClaimOfNatureTypeDTO claimOfNatureType;
    private String employeeType;
    private BigDecimal wageRate;
    private Date appointmentDate;
    private Date laidOffDate;
    private BigDecimal wilonPref;
    private BigDecimal wilonOrdi;
    private BigDecimal severPref;
    private BigDecimal severOrdi;
    private BigDecimal totalWilon;
    private BigDecimal totalSever;
    private String status;
}
