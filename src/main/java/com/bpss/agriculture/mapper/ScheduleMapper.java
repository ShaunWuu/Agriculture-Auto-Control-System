package com.bpss.agriculture.mapper;

import com.bpss.agriculture.entity.CronVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author Rocky Wu
 */
@Component
public interface ScheduleMapper {

    /**
     * 通过 id 获得对应的 CRON 表达式, 目的是以后希望加入多种策略的时候可以来回切换
     * @param id CRON 表达式 ID
     * @return CRON 表达式对象
     */
    @Select("SELECT * FROM tSchedule WHERE id = #{id}")
    CronVo getCron(int id);

}
