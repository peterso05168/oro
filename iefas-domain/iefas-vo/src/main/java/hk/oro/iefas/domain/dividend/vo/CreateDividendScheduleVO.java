package hk.oro.iefas.domain.dividend.vo;

import java.util.Date;

import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CreateDividendScheduleVO {

    private CaseNumberVO caseNumber;
    private ScheduleTypeVO scheduleType;
    private Date paymentEffectiveDate;
}
