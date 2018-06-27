package hk.oro.iefas.core.schedule.job;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.Getter;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
public abstract class BaseJob extends QuartzJobBean {

    @Getter
    @Autowired
    private JobBuilderFactory jobs;

    @Getter
    @Autowired
    private StepBuilderFactory steps;

    @Getter
    @Autowired
    private JobLauncher jobLauncher;

}
