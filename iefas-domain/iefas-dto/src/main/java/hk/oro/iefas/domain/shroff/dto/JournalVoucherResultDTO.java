/**
 * 
 */
package hk.oro.iefas.domain.shroff.dto;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalVoucherResultDTO {

    private Page<JournalVoucherResultItemDTO> journalVoucherResultItems;

}
