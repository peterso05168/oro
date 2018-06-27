package hk.oro.iefas.domain.dividend.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (Thu, 24 May 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ValidateOrFeeComputationVO {
    private Integer caseId;
    private Integer dividendCalId;
    private List<Integer> credTypes;
    
    private boolean createValidation;
}
