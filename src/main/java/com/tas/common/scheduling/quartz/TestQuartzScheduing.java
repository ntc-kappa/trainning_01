package com.tas.common.scheduling.quartz;

import org.quartz.*;
import org.quartz.impl.RemoteScheduler;
import org.quartz.impl.StdSchedulerFactory;

public class TestQuartzScheduing {
    public static void main(String[] args) throws SchedulerException {
        try {
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("huongdanjavaTrigger", "group")
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInSeconds(5)
                                    .repeatForever()
                    )
                    .build();

            JobDetail job = JobBuilder.newJob(SimpleJob.class)
                    .withIdentity("huongdanjavaJob", "group")
                    .build();

            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

}

