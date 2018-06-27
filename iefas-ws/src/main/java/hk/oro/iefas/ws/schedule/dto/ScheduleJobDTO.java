package hk.oro.iefas.ws.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleJobDTO {

    private String jobName;
    private String jobGroup;
    private String cronExpression;
    private String jobClassName;

}
