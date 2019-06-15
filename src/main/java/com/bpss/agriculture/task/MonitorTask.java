package com.bpss.agriculture.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;


/**
 * @author Rocky Wu
 */
@Configuration
@Component
@EnableScheduling
public class MonitorTask {

    /**
     * 20190612163741
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMDDHHmmss");

    /**
     * 3000ms 执行一次
     *
     * tGreenHouseInfo 大棚环境信息
     */
    // @Scheduled(fixedRate = 3000)
    public void monitor(){
        System.out.println("查询数据库 - SELECT * FROM [dbo].[tGreenHouseInfo] WHERE iGreenHouseId=1;");
        // TODO 查看告警情况
        // TODO 不同告警情况对应不同设备的启动和关闭

    }

}
