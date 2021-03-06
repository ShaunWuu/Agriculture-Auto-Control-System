package com.bpss.agriculture.task;

import com.bpss.agriculture.entity.GreenHouseInfo;
import com.bpss.agriculture.entity.MotoCmdInfo;
import com.bpss.agriculture.entity.Schedule;
import com.bpss.agriculture.mapper.AgricultureMapper;
import com.bpss.agriculture.mapper.ScheduleMapper;
import com.bpss.agriculture.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Rocky Wu
 */
@Component
@EnableScheduling
@Slf4j
public class MonitorTask {

    @Autowired
    AgricultureMapper agricultureMapper;

    @Autowired
    ScheduleMapper scheduleMapper;

    private static final int AIR_TEMP = 1;
    private static final int AIR_WATER = 2;
    // 土壤温度无法控制，此处不做监测
    private static final int EARTH_TEMP_1 = 3;
    private static final int EARTH_TEMP_2 = 4;
    private static final int EARTH_WATER_1 = 5;
    // 土壤水分2,4温度长期偏低，此处不做监测
    private static final int EARTH_WATER_2 = 6;
    private static final int LIGHT = 7;

    /**
     * 打开设备的操作代码
     */
    private static final int MOTO_OPEN = 1;

    /**
     * 关闭设备的操作代码
     */
    private static final int MOTO_CLOSE = 0;

    /**
     * 表示两个设备的开关状态
     */
    private static final int CLOSE_OPEN = 0b01;
    private static final int OPEN_CLOSE = 0b10;
    private static final int OPEN_OPEN = 0b11;
    private static final int CLOSE_CLOSE = 0b00;

    public void monitorGreenHouse() {

        log.info("----------------------------------------");
        log.info("自动控制系统此次检测和操作开始");

        try {
            airTempMotoCmd();
            lightMotoCmd();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("自动控制系统此次检测和操作完毕");
        log.info("----------------------------------------");

    }

    /**
     * 空气温度监测模块
     */
    private void airTempMotoCmd() {

        log.info("空气温度检测开始");

        GreenHouseInfo info = null;

        try {
            info = agricultureMapper.getGreenHouseInfo();
        } catch (Exception e) {
            log.error("数据库连接失败，请检查数据库服务器是否运行正常");
        }

        if (info == null) {
            log.error("数据库连接失败，请检查数据库服务器是否运行正常");
        }

        // 记录两个设备的状态
        int status = 0b00;

        Schedule schedule = scheduleMapper.getScheduleById(AIR_TEMP);

        float airTemp = (info.getFAirTemp1() + info.getFAirTemp2()) / 2;

        // 暖风机是否开着
        int iHotMotoStatus1 = agricultureMapper.getMotoInfo().getIHotMotoStatus1();
        int iHotMotoStatus2 = agricultureMapper.getMotoInfo().getIHotMotoStatus2();

        if (iHotMotoStatus1 == 1) {
            status += 0b10;
        }

        if (iHotMotoStatus2 == 1) {
            status += 0b01;
        }

        if (airTemp < schedule.getLow()) {
            // 空气温度低于温度阈值
            if (airTemp <= schedule.getLow()) {
                switch (status) {
                    case CLOSE_CLOSE: {
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
                        log.info("所有暖风机均已打开，请考虑使用其他方法提高空气温度");
                    }
                }
            }

            if (airTemp > ((schedule.getHigh() + schedule.getLow()) / 2)) {
                // 空气受到系统控制达到范围内
                if (airTemp > schedule.getLow() && airTemp < schedule.getHigh()) {

                    // 空气温度已升到足够高
                    Boolean airTempHighEnough = airTemp >= (((schedule.getLow() + schedule.getHigh()) / 2) - 3);

                    if (airTempHighEnough) {
                        switch (status) {
                            case OPEN_OPEN: {
                                closeMotoCmd("iHotMotoStatus1", "系统自动关闭暖风机1");
                                closeMotoCmd("iHotMotoStatus2", "系统自动关闭暖风机2");
                                break;
                            }
                            case OPEN_CLOSE: {
                                closeMotoCmd("iHotMotoStatus1", "系统自动关闭暖风机1");
                                break;
                            }
                            case CLOSE_OPEN: {
                                closeMotoCmd("iHotMotoStatus2", "系统自动关闭暖风机2");
                                break;
                            }
                            default: {
                                // do nothing
                                break;
                            }
                        }
                    }

                }

                // 空气温度高于温度阈值
                if (airTemp >= schedule.getHigh()) {
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
                            log.info("所有暖风机均已关闭，请考虑使用其他方法降低空气温度");
                        }
                    }
                }
                log.info("空气温度检测完毕");
            }
        }
    }

    /**
     * 空气湿度监测模块
     */
    // private void airWaterMotoCmd() {
    //
    //     // log.info("空气湿度检测开始");
    //
    //     GreenHouseInfo info = null;
    //     try {
    //         info = agricultureMapper.getGreenHouseInfo();
    //     } catch (Exception e) {
    //         log.error("数据库连接失败，请检查数据库服务器是否运行正常");
    //     }
    //     Schedule schedule = scheduleMapper.getScheduleById(AIR_WATER);
    //     float airWater = (info.getFAirWater1() + info.getFAirWater2()) / 2;
    //
    //     int iWetMotoStatus = agricultureMapper.getMotoInfo().getIWetMotoStatus1();
    //
    //     /**
    //      * 喷雾器设备无效，所以此处不做处理
    //      */
    //
    //     // if (airWater <= schedule.getLow()) {
    //     //     if (iWetMotoStatus == 0) {
    //     //         openMotoCmd("iWetMotoStatus1", "系统自动打开喷雾器");
    //     //     }
    //     // }
    //     //
    //     // if (airWater >= (((schedule.getHigh() + schedule.getLow()) / 2)) - 3) {
    //     //     if (iWetMotoStatus == 1) {
    //     //         closeMotoCmd("iWetMotoStatus1", "系统自动关闭喷雾器");
    //     //     }
    //     // }
    //
    //     // log.info("空气湿度检测完毕");
    // }

    /**
     * 土壤水分检测模块
     * 此部分由大窗控制系统控制，此处不做检测和处理
     */
    // private void earthMotoCmd() {
    //
    //     log.info("土壤水分检测开始");
    //
    //     GreenHouseInfo info = null;
    //     try {
    //         info = agricultureMapper.getGreenHouseInfo();
    //     } catch (Exception e) {
    //         log.error("数据库连接失败，请检查数据库服务器是否运行正常");
    //     }
    //     Schedule schedule = scheduleMapper.getScheduleById(EARTH_WATER_1);
    //     float earthWater = (info.getFEarthWater1() + info.getFEarthWater2() / 2);
    //
    //     int iWaterMotoStatus1 = agricultureMapper.getMotoInfo().getIWaterMotoStatus1();
    //
    //     if (earthWater < schedule.getLow()) {
    //         if (iWaterMotoStatus1 == 0) {
    //             openMotoCmd("iWaterMotoStatus1", "系统自动打开灌溉出水阀");
    //         }
    //     }
    //
    //     if (earthWater >= (((schedule.getHigh() + schedule.getLow()) / 2))) {
    //         if (iWaterMotoStatus1 == 1) {
    //             closeMotoCmd("iWaterMotoStatus1", "系统自动关闭灌溉出水阀");
    //         }
    //     }
    //
    //     log.info("土壤水分检测完毕");
    //
    // }

    /**
     * 光照强度模块
     */
    private void lightMotoCmd() throws ParseException {

        log.info("光照强度监测开始");

        GreenHouseInfo info = null;

        try {
            info = agricultureMapper.getGreenHouseInfo();
        } catch (Exception e) {
            log.error("数据库连接失败，请检查数据库服务器是否运行正常");
        }

        Schedule schedule = scheduleMapper.getScheduleById(LIGHT);

        float light = (info.getFLight1() + info.getFLight2()) / 2;

        int iLightStatus1 = agricultureMapper.getMotoInfo().getILightStatus1();

        // 每天从 from 时间段到 to 时间段以外的时间段，无论如何都会关闭补光灯，而在这个时间段才会正常自动开关补光灯
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd ");
        String yearMonthDayStr = yearMonthDay.format(date);

        Date from = sdf.parse(yearMonthDayStr + "10:00");
        Date to = sdf.parse(yearMonthDayStr + "17:00");

        if (date.before(to) && date.after(from)) {
            if (light < schedule.getLow()) {
                if (iLightStatus1 == 0) {
                    openMotoCmd("iLightStatus1", "系统自动打开补光灯");
                }
            }

            if (light > schedule.getHigh()) {
                if (iLightStatus1 == 1) {
                    closeMotoCmd("iLightStatus1", "系统自动关闭补光灯");
                }
            }
        } else {
            if (iLightStatus1 == 1) {
                closeMotoCmd("iLightStatus1", "系统自动关闭补光灯");
            }
        }

        log.info("光照强度监测完毕");

    }

    private void openMotoCmd(String vcMotoName, String vcActName) {

        String vcTime = CommonUtil.getVcTime();

        try {
            agricultureMapper.writeMotoCmd(
                    new MotoCmdInfo(vcMotoName, MOTO_OPEN, vcActName, 0, "此操作由系统自动处理", vcTime, 2));
            log.info("[操作设备]" + vcActName);
        } catch (Exception e) {
            log.error("数据库写入失败，请检查 WEB 服务器，错误信息如下：");
            e.printStackTrace();
        }
    }

    private void closeMotoCmd(String vcMotoName, String vcActName) {

        String vcTime = CommonUtil.getVcTime();

        try {
            agricultureMapper.writeMotoCmd(
                    new MotoCmdInfo(vcMotoName, MOTO_CLOSE, vcActName, 0, "此操作由系统自动处理", vcTime, 2));
            log.info("[操作设备]" + vcActName);
        } catch (Exception e) {
            log.error("数据库写入失败，请检查 WEB 服务器，错误信息如下：");
            e.printStackTrace();
        }
    }
}