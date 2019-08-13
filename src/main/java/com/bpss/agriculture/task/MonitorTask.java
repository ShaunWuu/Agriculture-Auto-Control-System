package com.bpss.agriculture.task;

import com.bpss.agriculture.entity.GreenHouseInfo;
import com.bpss.agriculture.entity.MotoCmdInfo;
import com.bpss.agriculture.entity.Schedule;
import com.bpss.agriculture.mapper.AgricultureMapper;
import com.bpss.agriculture.mapper.ScheduleMapper;
import com.bpss.agriculture.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author Rocky Wu
 */
@Component
@EnableScheduling
public class MonitorTask {

    @Autowired
    AgricultureMapper agricultureMapper;

    @Autowired
    ScheduleMapper scheduleMapper;

    private static final int AIR_TEMP = 1;
    private static final int AIR_WATER = 2;
    private static final int EARTH_TEMP_1 = 3;
    private static final int EARTH_TEMP_2 = 4;
    private static final int EARTH_WATER_1 = 5;
    private static final int EARTH_WATER_2 = 6;
    private static final int LIGHT = 7;

    /**
     * 打开设备的操作代码
     */
    private static final int MOTO_OPEN = 0;

    /**
     * 关闭设备的操作代码
     */
    private static final int MOTO_CLOSE = 1;

    /**
     * 0 代表开， 1 代表关
     * 原数据库的设计是如此
     */
    private static final int CLOSE_OPEN = 0b10;
    private static final int OPEN_CLOSE = 0b01;
    private static final int OPEN_OPEN = 0b00;

    private static final int OPEN_OPEN_OPEN = 0b000;
    private static final int OPEN_OPEN_CLOSE = 0b001;
    private static final int OPEN_CLOSE_OPEN = 0b010;
    private static final int CLOSE_OPEN_OPEN = 0b100;
    private static final int OPEN_CLOSE_CLOSE = 0b011;
    private static final int CLOSE_CLOSE_OPEN = 0b110;
    private static final int CLOSE_OPEN_CLOSE = 0b101;
    private static final int CLOSE_CLOSE_CLOSE = 0b111;

    public void monitorGreenHouse() {

        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("* 自动控制系统此次检测和操作开始");

        airTempMotoCmd();
        airWaterMotoCmd();
        earthMotoCmd();
        lightMotoCmd();

        System.out.println("* 自动控制系统此次检测和操作完毕");
        System.out.println("----------------------------------------");
        System.out.println();

    }

    private void airTempMotoCmd() {

        System.out.println("    - 空气温度检测开始");

        GreenHouseInfo info = agricultureMapper.getGreenHouseInfo();

        int status = 0x00;

        Schedule schedule = scheduleMapper.getScheduleById(AIR_TEMP);

        float airTemp = (info.getFAirTemp1() + info.getFAirTemp2()) / 2;

        int iHotMotoStatus1 = agricultureMapper.getMotoInfo().getIHotMotoStatus1();
        int iHotMotoStatus2 = agricultureMapper.getMotoInfo().getIHotMotoStatus2();

        if (iHotMotoStatus1 == 0) {
            status += 0x10;
        }

        if (iHotMotoStatus2 == 0) {
            status += 0x01;
        }

        if (airTemp < schedule.getLow()) {

            switch (status) {
                case OPEN_OPEN: {
                    openMotoCmd("iHotMotoStatus1", "系统自动打开暖风机1");
                    openMotoCmd("iHotMotoStatus2", "系统自动打开暖风机2");
                    break;
                }
                case OPEN_CLOSE: {
                    openMotoCmd("iHotMotoStatus2", "系统自动打开暖风机2");
                    break;
                }
                case CLOSE_OPEN: {
                    openMotoCmd("iHotMotoStatus1", "系统自动打开暖风机1");
                    break;
                }
                default: {
                    System.out.println("所有暖风机均已打开，请考虑使用其他方法提高空气温度");
                }
            }


        }

        if (airTemp > ((schedule.getHigh() + schedule.getLow()) / 2)) {

            switch (status) {
                case OPEN_OPEN: {
                    closeMotoCmd("iHotMotoStatus1", "系统自动关闭暖风机1");
                    closeMotoCmd("iHotMotoStatus2", "系统自动关闭暖风机2");
                    break;
                }
                case OPEN_CLOSE: {
                    closeMotoCmd("iHotMotoStatus2", "系统自动关闭暖风机2");
                    break;
                }
                case CLOSE_OPEN: {
                    closeMotoCmd("iHotMotoStatus1", "系统自动关闭暖风机1");
                    break;
                }
                default: {
                    System.out.println("所有暖风机均已关闭，请考虑使用其他方法降低空气温度");
                }
            }

            System.out.println("    - 空气温度检测完毕");

        }

    }

    private void airWaterMotoCmd() {

        System.out.println("    - 空气温度检测开始");

        GreenHouseInfo info = agricultureMapper.getGreenHouseInfo();

        Schedule schedule = scheduleMapper.getScheduleById(AIR_WATER);

        float airWater = (info.getFAirWater1() + info.getFAirWater2()) / 2;

        int iWetMotoStatus = agricultureMapper.getMotoInfo().getIWetMotoStatus1();

        if (airWater < schedule.getLow()) {

            openMotoCmd("iWetMotoStatus1", "系统自动打开喷雾器");

        }

        if (airWater > ((schedule.getHigh() + schedule.getLow()) / 2)) {

            closeMotoCmd("iWetMotoStatus1", "系统自动关闭喷雾器");

        }

        System.out.println("    - 空气温度检测完毕");

    }

    private void earthMotoCmd(){

        System.out.println("    - 土壤水分检测开始");

        GreenHouseInfo info = agricultureMapper.getGreenHouseInfo();
        Schedule schedule = scheduleMapper.getScheduleById(EARTH_WATER_1);
        float earthWater = (info.getFEarthWater1() + info.getFEarthWater2() / 2);

        if (earthWater < schedule.getLow()) {

            openMotoCmd("iWaterMotoStatus1", "系统自动打开灌溉出水阀");

        }

        if (earthWater > ((schedule.getHigh() + schedule.getLow()) / 2)) {

            closeMotoCmd("iWaterMotoStatus1", "系统自动关闭灌溉出水阀");

        }

        System.out.println("    - 土壤水分检测完毕");

    }

    private void lightMotoCmd(){

        System.out.println("    - 光照强度监测开始");

        GreenHouseInfo info = agricultureMapper.getGreenHouseInfo();
        Schedule schedule = scheduleMapper.getScheduleById(LIGHT);

        float light = (info.getFLight1() + info.getFLight2()) / 2;

        if (light < schedule.getLow()) {

            openMotoCmd("iLightStatus1", "系统自动打开补光灯");

        }

        if (light > ((schedule.getHigh() + schedule.getLow()) / 1.8)) {

            openMotoCmd("iLightStatus1", "系统自动关闭补光灯");

        }

        System.out.println("    - 光照强度监测完毕");

    }

    private void openMotoCmd(String vcMotoName, String vcActName) {

        String vcTime = CommonUtil.getVcTime();

        agricultureMapper.writeMotoCmd(
                new MotoCmdInfo(vcMotoName, MOTO_OPEN, vcActName, 0, "此操作由系统自动处理", vcTime, 2)
        );

        System.out.println(vcActName);

    }

    private void closeMotoCmd(String vcMotoName, String vcActName) {

        String vcTime = CommonUtil.getVcTime();

        agricultureMapper.writeMotoCmd(
                new MotoCmdInfo(vcMotoName, MOTO_CLOSE, vcActName, 0, "此操作由系统自动处理", vcTime, 2)
        );

        System.out.println(vcActName);

    }
}