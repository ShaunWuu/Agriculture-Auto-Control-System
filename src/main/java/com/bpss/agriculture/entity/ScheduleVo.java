package com.bpss.agriculture.entity;

import lombok.Data;

/**
 * 自动控制配置 Entity 对象, 除了 EC, PH 监测时间间隔是 float 外, 其他监测属性均为 int
 *
 * @author Rocky Wu
 */
@Data
public class ScheduleVo {

    private int lowAirTemp;
    private int highAirTemp;
    private int lowAirWater;
    private int highAirWater;
    private int lowEarthTemp;
    private int highEarthTemp;
    private int lowEarthWater;
    private int highEarthWater;
    private int lowCO2;
    private int highCO2;
    private float lowEC;
    private float highEC;
    private float lowPH;
    private float highPH;
    private int lowLight;
    private int highLight;
    private CronVo cronVo;

}
