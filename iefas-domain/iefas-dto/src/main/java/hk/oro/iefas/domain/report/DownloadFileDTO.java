package hk.oro.iefas.domain.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadFileDTO {

    private String fileName;
    private String fileFormat;
    private byte[] fileResult;
}
