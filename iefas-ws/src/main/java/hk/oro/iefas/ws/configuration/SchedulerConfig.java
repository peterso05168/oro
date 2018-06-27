package hk.oro.iefas.ws.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Configuration
public class SchedulerConfig {

    public final static String QUARTZ_SCHEDULER_INSTANCENAME = "org.quartz.scheduler.instanceName";
    public final static String QUARTZ_SCHEDULER_INSTANCEID = "org.quartz.scheduler.instanceId";
    public final static String QUARTZ_SCHEDULER_SKIPUPDATECHECK = "org.quartz.scheduler.skipUpdateCheck";
    public final static String QUARTZ_SCHEDULER_STARTUPDELAY = "org.quartz.scheduler.startupDelay";
    public final static String QUARTZ_THREADPOOL_CLASS = "org.quartz.threadPool.class";
    public final static String QUARTZ_THREADPOOL_THREADCOUNT = "org.quartz.threadPool.threadCount";
    public final static String QUARTZ_THREADPOOL_THREADPRIORITY = "org.quartz.threadPool.threadPriority";
    public final static String QUARTZ_JOBSTORE_CLASS = "org.quartz.jobStore.class";
    public final static String QUARTZ_JOBSTORE_MISFIRETHRESHOLD = "org.quartz.jobStore.misfireThreshold";
    public final static String QUARTZ_JOBSTORE_DRIVERDELEGATECLASS = "org.quartz.jobStore.driverDelegateClass";
    public final static String QUARTZ_JOBSTORE_MAXMISFIRESTOHANDLEATATIME
        = "org.quartz.jobStore.maxMisfiresToHandleAtATime";
    public final static String QUARTZ_JOBSTORE_TABLEPREFIX = "org.quartz.jobStore.tablePrefix";
    public final static String QUARTZ_JOBSTORE_ISCLUSTERED = "org.quartz.jobStore.isClustered";
    public final static String QUARTZ_JOBSTORE_CLUSTERCHECKININTERVAL = "org.quartz.jobStore.clusterCheckinInterval";
    public final static String QUARTZ_JOBSTORE_DATASOURCE = "org.quartz.jobStore.dataSource";
    public final static String QUARTZ_DATASOURCE_DS_JNDIURL = "org.quartz.dataSource.DS.jndiURL";

    @Value(value = "${" + QUARTZ_SCHEDULER_INSTANCENAME + "}")
    private String schedulerInstanceName;

    @Value(value = "${" + QUARTZ_SCHEDULER_INSTANCEID + "}")
    private String schedulerInstanceId;

    @Value(value = "${" + QUARTZ_SCHEDULER_SKIPUPDATECHECK + "}")
    private String schedulerSkipUpdateCheck;

    @Value(value = "${" + QUARTZ_SCHEDULER_STARTUPDELAY + "}")
    private String schedulerStartupDelay;

    @Value(value = "${" + QUARTZ_THREADPOOL_CLASS + "}")
    private String threadPoolClass;

    @Value(value = "${" + QUARTZ_THREADPOOL_THREADCOUNT + "}")
    private String threadPoolThreadCount;

    @Value(value = "${" + QUARTZ_THREADPOOL_THREADPRIORITY + "}")
    private String threadPoolThreadPriority;

    @Value(value = "${" + QUARTZ_JOBSTORE_CLASS + "}")
    private String jobStoreClass;

    @Value(value = "${" + QUARTZ_JOBSTORE_MISFIRETHRESHOLD + "}")
    private String jobStoreMisfireThreshold;

    @Value(value = "${" + QUARTZ_JOBSTORE_DRIVERDELEGATECLASS + "}")
    private String jobStoreDriverDelegateClass;

    @Value(value = "${" + QUARTZ_JOBSTORE_MAXMISFIRESTOHANDLEATATIME + "}")
    private String jobStoreMaxMisfiresToHandleAtATime;

    @Value(value = "${" + QUARTZ_JOBSTORE_TABLEPREFIX + "}")
    private String jobStoreTablePrefix;

    @Value(value = "${" + QUARTZ_JOBSTORE_ISCLUSTERED + "}")
    private String jobStoreIsClustered;

    @Value(value = "${" + QUARTZ_JOBSTORE_CLUSTERCHECKININTERVAL + "}")
    private String jobStoreClusterCheckinInterval;

    @Value(value = "${spring.datasource.jndi-name}")
    private String jndiURL;

    @Bean
    @Lazy
    public SchedulerFactoryBean scheduler() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        Properties quartzProperties = new Properties();

        quartzProperties.setProperty(QUARTZ_SCHEDULER_INSTANCENAME, schedulerInstanceName);
        quartzProperties.setProperty(QUARTZ_SCHEDULER_INSTANCEID, schedulerInstanceId);
        quartzProperties.setProperty(QUARTZ_SCHEDULER_SKIPUPDATECHECK, schedulerSkipUpdateCheck);
        quartzProperties.setProperty(QUARTZ_SCHEDULER_STARTUPDELAY, schedulerStartupDelay);

        quartzProperties.setProperty(QUARTZ_THREADPOOL_CLASS, threadPoolClass);
        quartzProperties.setProperty(QUARTZ_THREADPOOL_THREADCOUNT, threadPoolThreadCount);
        quartzProperties.setProperty(QUARTZ_THREADPOOL_THREADPRIORITY, threadPoolThreadPriority);

        quartzProperties.setProperty(QUARTZ_JOBSTORE_CLASS, jobStoreClass);
        quartzProperties.setProperty(QUARTZ_JOBSTORE_MISFIRETHRESHOLD, jobStoreMisfireThreshold);
        quartzProperties.setProperty(QUARTZ_JOBSTORE_MAXMISFIRESTOHANDLEATATIME, jobStoreMaxMisfiresToHandleAtATime);
        quartzProperties.setProperty(QUARTZ_JOBSTORE_TABLEPREFIX, jobStoreTablePrefix);
        quartzProperties.setProperty(QUARTZ_JOBSTORE_ISCLUSTERED, jobStoreIsClustered);
        quartzProperties.setProperty(QUARTZ_JOBSTORE_CLUSTERCHECKININTERVAL, jobStoreClusterCheckinInterval);
        quartzProperties.setProperty(QUARTZ_JOBSTORE_DATASOURCE, "DS");
        quartzProperties.setProperty(QUARTZ_DATASOURCE_DS_JNDIURL, jndiURL);

        schedulerFactoryBean.setQuartzProperties(quartzProperties);

        return schedulerFactoryBean;
    }
}
