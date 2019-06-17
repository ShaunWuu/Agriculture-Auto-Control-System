package com.bpss.agriculture.task;

import com.bpss.agriculture.entity.CronVo;
import com.bpss.agriculture.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

/**
 * @author Rocky Wu
 */
@Service
@EnableScheduling
public class MonitorTask implements SchedulingConfigurer {

    @Autowired
    ScheduleMapper scheduleMapper;

    private String cron;

    private String getCronVo(){
        CronVo cronVo = scheduleMapper.getCron(2);
        return cronVo.getCron();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        scheduledTaskRegistrar.addTriggerTask(() -> {
            // 查询数据库是否有超过范围值的业务代码
            System.out.println("查询数据库并进行业务处理");
        }, triggerContext -> {
            cron = getCronVo();
            CronTrigger trigger = new CronTrigger(cron);
            return trigger.nextExecutionTime(triggerContext);
        });

    }
}
