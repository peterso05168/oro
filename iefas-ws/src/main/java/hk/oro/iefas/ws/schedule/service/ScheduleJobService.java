package hk.oro.iefas.ws.schedule.service;

import org.quartz.SchedulerException;

import hk.oro.iefas.ws.schedule.dto.ScheduleJobDTO;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
public interface ScheduleJobService {

    void create(ScheduleJobDTO scheduleJobDTO) throws SchedulerException;

    void update(ScheduleJobDTO scheduleJobDTO) throws SchedulerException;

    void deleteUpdate(ScheduleJobDTO scheduleJobDTO) throws SchedulerException;

    void delete(ScheduleJobDTO scheduleJobDTO) throws SchedulerException;

    void runOnce(ScheduleJobDTO scheduleJobDTO) throws SchedulerException;

    void pause(ScheduleJobDTO scheduleJobDTO) throws SchedulerException;

    void resume(ScheduleJobDTO scheduleJobDTO) throws SchedulerException;

}
