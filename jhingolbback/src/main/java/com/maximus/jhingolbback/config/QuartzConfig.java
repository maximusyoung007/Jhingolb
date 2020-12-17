package com.maximus.jhingolbback.config;

import com.maximus.jhingolbback.job.RedisJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail myJobDetail() {
        JobDetail jobDetail = JobBuilder.newJob(RedisJob.class)
                .withIdentity("myJob1","RedisJobGroup")
                .usingJobData("msg","hello quartz")
                .storeDurably()
                .build();
        return jobDetail;
    }

    @Bean
    public Trigger myTrigger() {
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myTrigger1","RedisTriggerGroup")
                .usingJobData(new JobDataMap())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                .build();
        return trigger;
    }
}
