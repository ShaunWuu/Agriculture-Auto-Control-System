package com.bpss.agriculture.mapper;

import com.bpss.agriculture.entity.Cron;
import com.bpss.agriculture.entity.Schedule;
import com.bpss.agriculture.entity.ScheduleDto;
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
     * 通过 id 获取对应的自动控制策略
     * @param id 要获取的自动控制策略 id
     * @return 对应 id 的自动控制策略表
     */
    @Select("SELECT * FROM tSchedule WHERE id = #{id}")
    Schedule getScheduleById(int id);

    /**
     * 修改控制策略
     * @param scheduleDto 策略 DTO 对象
     */
    @Update("UPDATE tSchedule SET high=#{high}, low=#{low} WHERE id=#{id}")
    void changeSchedule(ScheduleDto scheduleDto);

    /**
     * 修改选择的 Cron 表达式
     * @param id Cron 表达式 id
     */
    @Update("UPDATE tCronSelect SET cid = #{id}")
    void changeCronSelect(Integer id);

}
