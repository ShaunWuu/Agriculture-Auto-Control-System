package com.bpss.agriculture.config;

import com.bpss.agriculture.task.MonitorTask;
import lombok.Getter;
import lombok.Setter;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author Rocky Wu
 */
@Configuration
public class QuartzConfiguration {

    @Setter
    @Getter
    private static String cronExpression = "0/3 * * * * ? *";

    /**
     * 配置定时任务
     *
     * @param task 任务
     * @return jobDetail
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(MonitorTask task) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();

        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 任务名
        jobDetail.setName("auto-monitor");
        // 任务组
        jobDetail.setGroup("auto");
        // 执行的实体类对应的对象
        jobDetail.setTargetObject(task);
        // 需要执行的 monitor()
        jobDetail.setTargetMethod("monitor");
        return jobDetail;
    }

    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail.getObject());
        // 3 秒执行一次
        trigger.setCronExpression("0/3 * * * * ? *");
        // trigger的name
        trigger.setName("auto-monitor");
        return trigger;
    }

    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        // bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
        // 注册触发器
        bean.setTriggers(cronJobTrigger);
        return bean;
    }

}
