package hk.oro.iefas.domain.dividend.vo;

import java.util.ArrayList;
import java.util.List;

import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CaseDividendScheduleVO {

    private CaseNumberVO caseNumber;
    private List<DividendScheduleVO> dividendSchedules = new ArrayList<>();
}
