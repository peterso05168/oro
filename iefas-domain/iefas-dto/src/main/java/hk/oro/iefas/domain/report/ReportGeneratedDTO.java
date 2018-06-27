package hk.oro.iefas.domain.report;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportGeneratedDTO {
    private Long reportRequestId;
    private String reportName;
    private String reportFormat;
    private String reportUnitUri;
    private Map<String, Object> parameters = new HashMap<>();

}
