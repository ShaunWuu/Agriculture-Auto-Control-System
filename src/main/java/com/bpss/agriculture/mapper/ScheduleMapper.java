package com.bpss.agriculture.mapper;

import com.bpss.agriculture.entity.Cron;
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
     * @return List<Cron>
     */
    @Select("SELECT * FROM tCron ORDER BY id")
    List<Cron> getAllCron();

    /**
     * 获得选择的 Cron 对象
     * @return 选择的 Cron 对象
     */
    @Select("SELECT * FROM tCron WHERE id = (SELECT cid FROM tCronSelect)")
    Cron getCronSelect();

    /**
     * 获取当前自动控制策略表
     * @return 策略
     */
    @Select("SELECT * FROM tSchedule")
    List<Schedule> getSchedule();

    /**
     * 修改单一属性监测范围
     * @param schedule 某一样属性的监测范围
     */
    @Update({"UPDATE " +
                "tSchedule " +
            "SET " +
                "data=#{data}, note=#{note}, " +
                "high=#{high}, low=#{low}, " +
                "max=#{max}, min=#{min}, " +
                "step=#{step}, decimals={decimals}, " +
                "unit=#{unit} WHERE id=#{id}"})
    void changeSchedule(Schedule schedule);

}
