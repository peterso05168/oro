package hk.oro.iefas.domain.dividend.dto;

import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision$ $Date$
 * @author $Author$
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrFeeComputationDTO {
    private CaseDTO vcase;
    private String paymentType;
}
