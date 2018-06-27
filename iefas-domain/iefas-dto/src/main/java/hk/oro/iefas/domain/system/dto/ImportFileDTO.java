package hk.oro.iefas.domain.system.dto;

import java.io.InputStream;
import java.util.List;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $ $Date $
 * @author $Author $
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ImportFileDTO extends TxnDTO {

    private List<UploadedHolidayDTO> uploadedHolidayList;

    // private InputStream inputStream;
    //
    // private String fileName;
    //
    // private long fileSize;
}
