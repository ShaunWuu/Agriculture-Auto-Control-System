package com.bpss.agriculture.task;

import com.bpss.agriculture.mapper.ScheduleMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Rocky Wu
 */
@Configuration
@EnableScheduling
@Component
@Slf4j
public class RefreshCronTask {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Resource(name = "jobDetail")
    private JobDetail jobDetail;

    @Resource(name = "jobTrigger")
    private CronTrigger cronTrigger;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    /**
     * 每隔3s查库，并根据查询结果决定是否重新设置定时任务
     *
     * @throws SchedulerException 异常
     */
    @Scheduled(fixedRate = 10_000)
    public void scheduleUpdateCronTrigger() throws SchedulerException {
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        // 当前Trigger使用的
        String currentCron = trigger.getCronExpression();
        // 从数据库查询出来的
        String searchCron = scheduleMapper.getCronSelect().getCron();
        String currentCronFromDatabase = scheduleMapper.getCronSelect().getNote();
        // log.info("当前监测周期: " + currentCronFromDatabase);
        if (!currentCron.equals(searchCron)) {
            log.warn("修改监测周期表达式为: " + searchCron);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);
            // 按新的cronExpression表达式重新构建trigger
            trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
            trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey())
                    .withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(cronTrigger.getKey(), trigger);
            currentCron = searchCron;
        }
    }

}
