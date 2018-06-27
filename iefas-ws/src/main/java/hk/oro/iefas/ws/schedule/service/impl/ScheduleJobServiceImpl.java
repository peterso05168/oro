package hk.oro.iefas.ws.schedule.service.impl;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.ws.schedule.ScheduleJobEnum;
import hk.oro.iefas.ws.schedule.dto.ScheduleJobDTO;
import hk.oro.iefas.ws.schedule.service.ScheduleJobService;
import hk.oro.iefas.ws.schedule.util.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    private final static String TRIGGER_STATE_PAUSED = "PAUSED";

    @Autowired
    private Scheduler scheduler;

    @Override
    public void create(ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("create() start" + CommonUtils.SPACE + scheduleJobDTO);
        ScheduleJobEnum jobClass = ScheduleJobEnum.valueOf(scheduleJobDTO.getJobClassName());

        if (jobClass != null) {
            JobDetail jobDetail = JobBuilder.newJob(jobClass.getJobClass())
                .withIdentity(scheduleJobDTO.getJobName(), scheduleJobDTO.getJobGroup()).build();

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobDTO.getCronExpression())
                .withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger
                = TriggerBuilder.newTrigger().withIdentity(scheduleJobDTO.getJobName(), scheduleJobDTO.getJobGroup())
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        }

        log.info("create() end");
    }

    @Override
    public void update(ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("update() start" + CommonUtils.SPACE + scheduleJobDTO);
        TriggerKey triggerKey = ScheduleUtils.getTriggerKey(scheduleJobDTO.getJobName(), scheduleJobDTO.getJobGroup());

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobDTO.getCronExpression());

        CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
        if (!TRIGGER_STATE_PAUSED.equalsIgnoreCase(triggerState.name())) {
            scheduler.rescheduleJob(triggerKey, trigger);
        }
        log.info("update() end");
    }

    @Override
    public void deleteUpdate(ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("deleteUpdate() start" + CommonUtils.SPACE + scheduleJobDTO);
        delete(scheduleJobDTO);
        create(scheduleJobDTO);
        log.info("deleteUpdate() end");
    }

    @Override
    public void delete(ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("delete() start" + CommonUtils.SPACE + scheduleJobDTO);
        scheduler.deleteJob(ScheduleUtils.getJobKey(scheduleJobDTO.getJobName(), scheduleJobDTO.getJobGroup()));
        log.info("delete() end");
    }

    @Override
    public void runOnce(ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("runOnce() start" + CommonUtils.SPACE + scheduleJobDTO);
        scheduler.triggerJob(ScheduleUtils.getJobKey(scheduleJobDTO.getJobName(), scheduleJobDTO.getJobGroup()));
        log.info("runOnce() end");
    }

    @Override
    public void pause(ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("pause() start" + CommonUtils.SPACE + scheduleJobDTO);
        scheduler.pauseJob(ScheduleUtils.getJobKey(scheduleJobDTO.getJobName(), scheduleJobDTO.getJobGroup()));
        log.info("pause() end");
    }

    @Override
    public void resume(ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("resume() start" + CommonUtils.SPACE + scheduleJobDTO);
        scheduler.resumeJob(ScheduleUtils.getJobKey(scheduleJobDTO.getJobName(), scheduleJobDTO.getJobGroup()));
        log.info("resume() end");
    }

}
