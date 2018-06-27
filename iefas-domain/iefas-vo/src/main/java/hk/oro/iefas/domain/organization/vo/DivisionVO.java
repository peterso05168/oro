package hk.oro.iefas.domain.organization.vo;

import java.util.List;

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
@NoArgsConstructor
@AllArgsConstructor
public class DivisionVO extends TxnVO {

    private Integer divisionId;
    private String divisionName;
    private Integer parentDivisionId;
    private String status;
    private List<DivisionVO> divisions;

}
