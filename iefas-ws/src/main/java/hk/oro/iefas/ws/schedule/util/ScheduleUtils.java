package hk.oro.iefas.ws.schedule.util;

import org.quartz.JobKey;
import org.quartz.TriggerKey;

import hk.oro.iefas.core.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
public class ScheduleUtils {

    public static TriggerKey getTriggerKey(String jobName, String jobGroup) {
        log.info("getTriggerKey start" + CommonUtils.SPACE + "Job Name:" + CommonUtils.SPACE + jobName + ", Job Group:"
            + CommonUtils.SPACE + jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        log.info("getTriggerKey end");
        return triggerKey;
    }

    public static JobKey getJobKey(String jobName, String jobGroup) {
        log.info("getJobKey start" + CommonUtils.SPACE + "Job Name:" + CommonUtils.SPACE + jobName + ", Job Group:"
            + CommonUtils.SPACE + jobGroup);
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        log.info("getJobKey end");
        return jobKey;
    }

}
