package hk.oro.iefas.domain.shroff.dto;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2866 $ $Date: 2018-06-04 16:03:03 +0800 (週一, 04 六月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SysAttachmentDTO extends TxnDTO {

    private Integer attachmentId;
    private byte[] content;
    private String status;
    private Boolean isUpdated;
}
