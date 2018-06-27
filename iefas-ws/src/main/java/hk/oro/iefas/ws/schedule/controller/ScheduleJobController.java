package hk.oro.iefas.ws.schedule.controller;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.ws.schedule.dto.ScheduleJobDTO;
import hk.oro.iefas.ws.schedule.service.ScheduleJobService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @ApiOperation(value = "ScheduleJob", notes = "Schedule Job")
    @ApiParam(name = "scheduleJobDTO", required = true, type = "ScheduleJobDTO")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody(required = true) ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("create() start" + CommonUtils.SPACE + scheduleJobDTO);
        scheduleJobService.create(scheduleJobDTO);
        log.info("create() end");
    }

    @ApiOperation(value = "ScheduleJob", notes = "Schedule Job")
    @ApiParam(name = "scheduleJobDTO", required = true, type = "ScheduleJobDTO")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody(required = true) ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("update() start" + CommonUtils.SPACE + scheduleJobDTO);
        scheduleJobService.update(scheduleJobDTO);
        log.info("update() end");
    }

    @ApiOperation(value = "ScheduleJob", notes = "Schedule Job")
    @ApiParam(name = "scheduleJobDTO", required = true, type = "ScheduleJobDTO")
    @RequestMapping(value = "/deleteUpdate", method = RequestMethod.POST)
    public void deleteUpdate(@RequestBody(required = true) ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("deleteUpdate() start" + CommonUtils.SPACE + scheduleJobDTO);
        scheduleJobService.delete(scheduleJobDTO);
        scheduleJobService.create(scheduleJobDTO);
        log.info("deleteUpdate() end");
    }

    @ApiOperation(value = "ScheduleJob", notes = "Schedule Job")
    @ApiParam(name = "scheduleJobDTO", required = true, type = "ScheduleJobDTO")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody(required = true) ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("delete() start" + CommonUtils.SPACE + scheduleJobDTO);
        scheduleJobService.delete(scheduleJobDTO);
        log.info("delete() end");
    }

    @ApiOperation(value = "ScheduleJob", notes = "Schedule Job")
    @ApiParam(name = "scheduleJobDTO", required = true, type = "ScheduleJobDTO")
    @RequestMapping(value = "/runOnce", method = RequestMethod.POST)
    public void runOnce(@RequestBody(required = true) ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("runOnce() start" + CommonUtils.SPACE + scheduleJobDTO);
        scheduleJobService.runOnce(scheduleJobDTO);
        log.info("runOnce() end");
    }

    @ApiOperation(value = "ScheduleJob", notes = "Schedule Job")
    @ApiParam(name = "scheduleJobDTO", required = true, type = "ScheduleJobDTO")
    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    public void pause(@RequestBody(required = true) ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("pause() start" + CommonUtils.SPACE + scheduleJobDTO);
        scheduleJobService.pause(scheduleJobDTO);
        log.info("pause() end");
    }

    @ApiOperation(value = "ScheduleJob", notes = "Schedule Job")
    @ApiParam(name = "scheduleJobDTO", required = true, type = "ScheduleJobDTO")
    @RequestMapping(value = "/resume", method = RequestMethod.POST)
    public void resume(@RequestBody(required = true) ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        log.info("resume() start" + CommonUtils.SPACE + scheduleJobDTO);
        scheduleJobService.resume(scheduleJobDTO);
        log.info("resume() end");
    }

}
