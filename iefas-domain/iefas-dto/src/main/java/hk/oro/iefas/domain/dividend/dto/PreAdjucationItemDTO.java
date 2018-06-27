package hk.oro.iefas.domain.dividend.dto;

import java.math.BigDecimal;

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
public class PreAdjucationItemDTO {
    private Integer preAdjRsltItemId;
    private Integer preOrdAdjRsltItemId;
    private Integer preRejAdjRsltItemId;
    private String pajTypeCode;
    private String pajTypeDesc;
    private BigDecimal preferential;
    private BigDecimal ordinary;
    private BigDecimal reject;
    private String[] ordGroundNos;
    private String[] rejGroundNos;
    private String[] ordGroundCodes;
    private String[] rejGroundCodes;
}
