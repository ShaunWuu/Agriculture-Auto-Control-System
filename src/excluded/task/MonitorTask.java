package com.bpss.agriculture.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
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


    public void monitor(){

    }

}
