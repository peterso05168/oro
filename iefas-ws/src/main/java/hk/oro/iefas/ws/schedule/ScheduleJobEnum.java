package hk.oro.iefas.ws.schedule;

import org.quartz.Job;

import hk.oro.iefas.ws.schedule.job.SampleJob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ScheduleJobEnum {

    /**
     * SampleJob
     */
    SampleJob(SampleJob.class);

    @Getter
    private Class<? extends Job> jobClass;

}
