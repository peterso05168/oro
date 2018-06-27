package hk.oro.iefas.ws.schedule.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Configurable;

import hk.oro.iefas.core.schedule.job.BaseJob;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.ws.batch.tasklet.SampleTasklet;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Configurable(preConstruction = true)
public class SampleJob extends BaseJob {

    private final static String STEP_NAME = "sample";
    private final static String JOB_NAME = "sample";

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("executeInternal() start");
        TaskletStep step = getSteps().get(STEP_NAME).tasklet(new SampleTasklet()).build();

        Job job = getJobs().get(JOB_NAME).start(step).build();

        JobParameters jobParameters
            = new JobParametersBuilder().addDate("date", DateUtils.getCurrentDate()).toJobParameters();
        try {
            getJobLauncher().run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
            | JobParametersInvalidException e) {
            log.error(e.getMessage(), e);
        }
        log.info("executeInternal() end");
    }
}
