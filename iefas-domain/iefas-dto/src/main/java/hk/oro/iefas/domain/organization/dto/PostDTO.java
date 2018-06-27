package hk.oro.iefas.domain.organization.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import hk.oro.iefas.domain.core.dto.TxnDTO;
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
public class PostDTO extends TxnDTO {

    private Integer postId;

    @NotBlank
    @Size(max = 100)
    private String postTitle;

    @Size(max = 200)
    private String postDesc;

    private DivisionDTO division;

    private RankDTO rank;

    @Size(max = 3)
    private String status;

    private Boolean assigned;

}
