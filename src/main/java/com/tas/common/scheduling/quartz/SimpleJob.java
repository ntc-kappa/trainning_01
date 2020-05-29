package com.tas.common.scheduling.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob  implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("hẹn giờ");
        System.out.println(jobExecutionContext.get("huongdanjavaJob"));
    }
}
