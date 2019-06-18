package com.bpss.agriculture.task;

import com.bpss.agriculture.entity.Cron;
import com.bpss.agriculture.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Rocky Wu
 */
@Service
@EnableScheduling
public class MonitorTask implements SchedulingConfigurer {

    @Autowired
    ScheduleMapper scheduleMapper;

    private String cronStr;

    private Cron getCronSelect(){
        return scheduleMapper.getCronSelect();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        scheduledTaskRegistrar.addTriggerTask(() -> {
            // 查询数据库是否有超过范围值的业务代码
            System.out.println("查询数据库并进行业务处理");
        }, triggerContext -> {
            cronStr = MonitorTask.this.getCronSelect().getCron();
            // CronTrigger trigger = new CronTrigger(cronStr);
            // TODO 将数据库中 Cron 表达式修改为 period 形式
            PeriodicTrigger trigger = new PeriodicTrigger(3000);
            trigger.setFixedRate(true);
            return trigger.nextExecutionTime(triggerContext);
        });

    }
}
