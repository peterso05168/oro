package hk.oro.iefas.domain.system.vo;

import java.util.List;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ImportFileVO extends TxnVO {

    List<UploadedHolidayVO> uploadedHolidayList;

    // private InputStream inputStream;
    //
    // private String fileName;
    //
    // private long fileSize;
}
