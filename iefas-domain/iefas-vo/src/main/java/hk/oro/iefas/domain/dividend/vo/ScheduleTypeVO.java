package hk.oro.iefas.domain.dividend.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleTypeVO {

    private Integer scheduleTypeId;
    private String scheduleTypeName;
    private String status;
}
