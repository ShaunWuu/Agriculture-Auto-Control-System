package com.bpss.agriculture.mapper;

import com.bpss.agriculture.entity.CronVo;
import com.bpss.agriculture.entity.Schedule;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 计划任务相关 Mapper
 * @author Rocky Wu
 */
@Component
public interface ScheduleMapper {

    /**
     * 获得 Cron 列表
     * @return List<CronVo>
     */
    @Select("SELECT * FROM tCron")
    List<CronVo> getAllCron();

    /**
     * 通过 id 获得对应的 CRON 表达式, 目的是以后希望加入多种策略的时候可以来回切换
     * @param id CRON 表达式 ID
     * @return CRON 表达式对象
     */
    @Select("SELECT * FROM tCron WHERE id = #{id}")
    CronVo getCron(int id);

    /**
     * 通过id获取控制策略
     * @param id 策略 id
     * @return 控制策略Vo
     */
    @Select("SELECT * FROM tSchedule WHERE id = #{id}")
    Schedule getSchedule(int id);

    @Update({"UPDATE " +
                "tSchedule " +
            "SET " +
                "lowAirTemp=#{lowAirTemp}, highAirTemp=#{highAirTemp}, " +
                "lowAirWater=#{lowAirWater}, highAirWater=#{highAirWater}, " +
                "lowEarthTemp=#{lowEarthTemp}, highEarthTemp=#{highEarthTemp}, " +
                "lowEarthWater=#{lowEarthWater}, highEarthWater={highEarthWater}, " +
                "lowCO2=#{lowCO2}, highCO2=#{highCO2}, " +
                "lowEC=#{lowEC}, highEC=#{highEC}, " +
                "lowPH=#{lowPH}, highPH=#{highPH}, " +
                "lowLight=#{lowLight}, highLight=#{highLight}, " +
                "cid=#{CronVo.cid}"})
    void changeSchedule(Schedule schedule);

}
