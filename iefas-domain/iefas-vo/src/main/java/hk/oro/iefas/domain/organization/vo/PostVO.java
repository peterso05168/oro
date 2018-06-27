package hk.oro.iefas.domain.organization.vo;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2495 $ $Date: 2018-05-19 13:24:40 +0800 (週六, 19 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PostVO extends TxnVO {

    private Integer postId;
    private String postTitle;
    private String postDesc;
    private DivisionVO division;
    private RankVO rank;
    private String status;
    private Boolean assigned;
}
