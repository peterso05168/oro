package hk.oro.iefas.domain.dividend.vo;

import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SearchDividendScheduleVO {

    private CaseNumberVO caseNumber;
    private String scheduleType;
    private String status;
}
