package com.bpss.agriculture.config;

import com.bpss.agriculture.mapper.ScheduleMapper;
import com.bpss.agriculture.task.MonitorTask;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ScheduleMapper scheduleMapper;

    /**
     * 查询数据库并更新 Cron 表达式
     *
     * @param task
     * @return
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(MonitorTask task) {// ScheduleTask为需要执行的任务
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();

        /*
         *  是否并发执行
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(false);

        // 设置任务名和组
        jobDetail.setName("monitor-greenHouse");
        jobDetail.setGroup("monitor");

        /*
         * 执行的任务目标
         */
        jobDetail.setTargetObject(task);

        /*
         * 需要执行的方法
         */
        jobDetail.setTargetMethod("monitorGreenHouse");

        return jobDetail;
    }

    /**
     * 从数据库中获取 Cron 表达式字符串
     *
     * @param jobDetail 要执行的任务内容
     * @return 触发器
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {

        // 获得 Cron 表达式字符串
        String cronStr = scheduleMapper.getCronSelect().getCron();

        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();

        trigger.setJobDetail(jobDetail.getObject());
        // 初始 cron
        trigger.setCronExpression(cronStr);
        trigger.setName("monitor-greenHouseTrigger");

        return trigger;

    }

    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {

        SchedulerFactoryBean bean = new SchedulerFactoryBean();

        // 注册触发器
        bean.setTriggers(cronJobTrigger);
        return bean;
    }
}
